package impatient.programming.multithreads.cancellation_and_shutdown.task_cancellation;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class InterruptedInFuture {

	private static final ExecutorService taskExec = Executors
			.newFixedThreadPool(1);
	
	
	/*
	 * 使用 Future，便可比较好地解决问题
	 * */
	public static void timedRun(Runnable task, long timeout, TimeUnit unit) throws InterruptedException{
		
		Future<?> future = taskExec.submit(task);
		try{
			future.get(timeout,unit);
			
		}catch(TimeoutException e){
			//task will be cancelled below
		}catch(ExecutionException e){
			//exception thrown in task; rethrow
			throw launderThrowable(e.getCause());
		}finally{
			//Harmless if task already complete
			future.cancel(true);//interrupt if running
		}
		
		
	}
	
	private static RuntimeException launderThrowable(Throwable t) {
        if (t instanceof RuntimeException) {
            return (RuntimeException) t;
        } else if (t instanceof Error) {
            throw (Error) t;
        } else {
            throw new IllegalStateException("Not unchecked", t);
        }
    }

	
	
	

}
