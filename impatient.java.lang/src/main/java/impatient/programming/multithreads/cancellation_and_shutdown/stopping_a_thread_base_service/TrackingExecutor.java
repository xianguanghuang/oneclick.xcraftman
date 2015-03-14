package impatient.programming.multithreads.cancellation_and_shutdown.stopping_a_thread_base_service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;


/*
 * 使用Executor 来保存没有正常结束的任务列表。但是，需要保存线程的interrupt状态，否则，这个类无法收集被cancel的task
 * 
 * TODO: 模拟十个线程，四个被中断，看能不能拿到cancelTask
 * 
 * 
 * */
public class TrackingExecutor extends AbstractExecutorService {
	
	private final ExecutorService exec ;
	private final Set<Runnable> tasksCancelledAtShutdown = Collections.synchronizedSet(new HashSet<Runnable>());

	
	
	public TrackingExecutor(ExecutorService exec) {
		super();
		this.exec = exec;
	}
	
	public List<Runnable> getCancelledTasks(){
		if(!exec.isTerminated())
			throw new IllegalStateException();
		return new ArrayList<Runnable>(tasksCancelledAtShutdown);
	}

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Runnable> shutdownNow() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isShutdown() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isTerminated() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean awaitTermination(long timeout, TimeUnit unit)
			throws InterruptedException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void execute(final Runnable command) {
		exec.execute(new Runnable() {
			
			@Override
			public void run() {
				try{
					command.run();
				}finally{
					if(isShutdown() && Thread.currentThread().isInterrupted())
						tasksCancelledAtShutdown.add(command);
				}
				
			}
		});

	}

}
