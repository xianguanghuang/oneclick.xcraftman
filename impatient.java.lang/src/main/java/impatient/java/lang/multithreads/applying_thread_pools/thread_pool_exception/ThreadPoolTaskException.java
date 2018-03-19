package impatient.java.lang.multithreads.applying_thread_pools.thread_pool_exception;

import java.util.concurrent.*;

/**
 * Created by Administrator on 2016/2/17.
 */
public class ThreadPoolTaskException {

    public static void main(String[] args){

        BlockingQueue<Runnable> linkedBlockingDeque = new LinkedBlockingDeque<Runnable>(
                10);
        ExecutorService executorService = new ThreadPoolExecutor(
                1, 1, 30,
                TimeUnit.SECONDS, linkedBlockingDeque,
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        System.out.println("Failed to execute runnable");
                    }
                });

        ExceptionRunnable exceptionRunnable = new ExceptionRunnable();
        while (true){
            executorService.submit(exceptionRunnable);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }



    }
}

class ExceptionRunnable implements Runnable{

    @Override
    public void run() {
        throw new UnsupportedOperationException("Always");
    }
}
