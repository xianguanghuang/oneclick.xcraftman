package impatient.java.multithreads.cancellation_and_shutdown.stopping_a_thread_base_service;

import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public abstract class WebCrawler {

	private static final long TIMEOUT = 0;

	private static final TimeUnit UNIT = TimeUnit.SECONDS;

	private volatile TrackingExecutor exec;

	private final Set<URL> urlsToCrawl = new HashSet<URL>();

	public synchronized void start() {
		exec = new TrackingExecutor(Executors.newCachedThreadPool());

		for (URL url : urlsToCrawl)
			submitCrawlTask(url);

		urlsToCrawl.clear();
	}

	
	/*
	 * 保存被cancel的和 uncrawl 的任务，做后续处理
	 * */
	public synchronized void stop() throws InterruptedException {
		try {
			saveUncrawled(exec.shutdownNow());
			if (exec.awaitTermination(TIMEOUT, UNIT))
				saveUncrawled(exec.getCancelledTasks());
			

		} finally {
			exec = null;
		}
	}
	
	protected abstract List<URL> processPage(URL url);

	private void saveUncrawled(List<Runnable> uncrawled) {
		
		for(Runnable task : uncrawled)
			urlsToCrawl.add(((CrawlTask)task).getPage());

	}

	private void submitCrawlTask(URL url) {

		exec.execute(new CrawlTask(url));
	}
	
	private class CrawlTask implements Runnable{
		private final URL url;
		
		public CrawlTask(URL url) {
			this.url = url;
		}

		public URL getPage(){
			return url;
		}

		@Override
		public void run() {
			for (URL link : processPage(url)){
				if(Thread.currentThread().isInterrupted())
					return;
				submitCrawlTask(link);
			}
			
		}
		
	}
}
