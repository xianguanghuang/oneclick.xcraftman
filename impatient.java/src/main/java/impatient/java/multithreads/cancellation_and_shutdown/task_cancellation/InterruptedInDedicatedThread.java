package impatient.java.multithreads.cancellation_and_shutdown.task_cancellation;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class InterruptedInDedicatedThread {

	private static final ScheduledExecutorService cancelExec = Executors
			.newScheduledThreadPool(1);

	/*
	 * Best Example.
	 * 
	 * 这个例子解决了之前的几个问题
	 * 1.中断任务的线程（borrowed Thread），在没有 了解／实现interrupt policy 的情况下就另一个线程
	 * 2.如果任务在timeout 之前完成，那么，调用 TaskThread 的 interrupt 方法的行为就很难被确定
	 * 3.如果任务（run 方法）对线程中断的处理不够及时，那么，这个timerun 可能要在任务完全完成后才能返回，时间限制的需求就没办法完成
	 * 
	 * 首先：
	 * 1. 使用了一个特定的线程来发起中断，并且没有把任务的interrupted exception 吞掉
	 * 2. 处理任务的线程也是一个特定的线程，即便任务在timeout之前完成，调用 interrupt 方法也不会对调用层产生影响
	 * 
	 * 但这种方式还是有一个缺点，就是无从判断join方法的返回状态究竟是正常返回还是异常返回
	 */
	public static void timedRun(final Runnable r, long timeout, TimeUnit unit) throws InterruptedException {

		class RethrowableTask implements Runnable {

			private volatile Throwable t;

			@Override
			public void run() {

				try {
					r.run();
				} catch (Throwable t) {
					this.t = t;
				}
			}
			
			void rethrow(){
				if(t != null)
					throw launderThrowable(t);
			}
			
			private RuntimeException launderThrowable(Throwable t) {
		        if (t instanceof RuntimeException) {
		            return (RuntimeException) t;
		        } else if (t instanceof Error) {
		            throw (Error) t;
		        } else {
		            throw new IllegalStateException("Not unchecked", t);
		        }
		    }
		}
		
		RethrowableTask task = new RethrowableTask();
		final Thread taskThread = new Thread(task);
		taskThread.start();
		cancelExec.schedule(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				taskThread.interrupt();
			}
		}, timeout, unit);
		
		
		taskThread.join(unit.toMillis(timeout));
		task.rethrow();
		

	}
	
	

}
