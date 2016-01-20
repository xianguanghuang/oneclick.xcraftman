package impatient.java.lang.multithreads.cancellation_and_shutdown.handle_abnormal_thread_termination;

import java.util.logging.Level;
import java.util.logging.Logger;

public class UEHLogger implements Thread.UncaughtExceptionHandler{

	
	/* In long-running applications, always use uncaught exception handler for all thread
	 * threads that at least log the exception
	 * 
	 * 只要实现了Thread.UncaughtExceptionHandler接口，所有未被捕捉的线程异常都会被捕捉
	 * 
	 * TODO: 试一下这个类看能捕捉到什么异常
	 * 
	 * */
	@Override
	public void uncaughtException(Thread t, Throwable e) {
		Logger logger = Logger.getAnonymousLogger();
		logger.log(Level.SEVERE,
				"Thread terminated iwth exception: " + t.getName(),e);
		
		
	}

}
