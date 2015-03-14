package impatient.programming.multithreads.cancellation_and_shutdown.task_cancellation;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class InterruptedInBorrowedThread {
	
	private static final ScheduledExecutorService cancelExec =  Executors.newScheduledThreadPool(1);
	
	/*
	 * Bad Example.
	 * 
	 * 这段程序有几个缺点：
	 * 1.中断任务的线程（borrowed Thread），在没有 了解／实现interrupt policy 的情况下都可以中断另一个线程
	 * 2.如果任务在timeout 之前完成，那么，调用 TaskThread 的 interrupt 方法的行为就很难被确定
	 * 3.如果任务（r.run 方法）对线程中断的处理不够及时，那么，这个timerun 可能要在任务完全完成后才能返回，时间限制的需求就没办法完成
	 * */
	public static void timedRun(Runnable r, long timeout, TimeUnit unit){
		
		final Thread taskThread = Thread.currentThread();
		cancelExec.schedule(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				taskThread.interrupt();
			}
			
		}, timeout, unit);
		
		r.run();
		
	}
	
	
	
	

}
