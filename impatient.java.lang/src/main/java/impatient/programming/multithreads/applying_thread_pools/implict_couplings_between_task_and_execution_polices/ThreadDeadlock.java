package impatient.programming.multithreads.applying_thread_pools.implict_couplings_between_task_and_execution_polices;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


/*
 * Bad Example
 * */
public class ThreadDeadlock {
	ExecutorService exec = Executors.newSingleThreadExecutor();
	
	public class RenderPageTask implements Callable<String>{

		@Override
		public String call() throws Exception {
			Future<String> header, footer;
			header = exec.submit(new LoadFileTask("header.html"));
			footer = exec.submit(new LoadFileTask("footer.html"));
			String page = renderBody();
			//Will deadlock -- task waiting for result of subtask
			return header.get() + page + footer.get();
		}

		private String renderBody() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	
	public class RenderPageTaskClient{
		public void start(){
			/*
			 * 由于RenderPageTask依赖于子任务LoadFileTask，而且使用同一个线程池，
			 * 因此，他们之间有隐含的依赖关系。
			 * 另外，线程池的大小不够（只有1），因此会出现死锁
			 * 
			 * */
			exec.submit(new RenderPageTask());
			
		}
	}
	
	private static class LoadFileTask implements Callable<String>{
		public LoadFileTask(String fileName){
			
		}

		@Override
		public String call() throws Exception {
			// TODO Auto-generated method stub
			return null;
		}

		
	}

}
