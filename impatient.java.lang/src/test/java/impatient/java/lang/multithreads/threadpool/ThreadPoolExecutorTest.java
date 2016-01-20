package impatient.java.lang.multithreads.threadpool;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class ThreadPoolExecutorTest {

    class OneMinuteTask implements Runnable {

        private static final int ONE_MINUTE = 1000 * 6;

        @Override
        public void run() {
            try {
                Thread.currentThread();
                Thread.sleep(ONE_MINUTE);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

    }

    @Test
    public void testTasksNumberUnderCoreSize() {

        ArrayBlockingQueue arrayblockingQueue = new ArrayBlockingQueue(20);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 10, 3, TimeUnit.SECONDS, arrayblockingQueue);
        List<Future> futerList = new ArrayList<Future>();

        for (int i = 0; i < 3; i++) {
            futerList.add(threadPoolExecutor.submit(new OneMinuteTask()));
        }

        Object[] objects = arrayblockingQueue.toArray();

        for (int i = 0; i < objects.length; i++) {

            if (i < 3) {
                assertNotNull(objects[i]);
            }else{
            	assertNull(objects[i]);
            }

        }

        assertEquals(3, threadPoolExecutor.getTaskCount());
        long startNanoTime = System.nanoTime();
        for (Future future : futerList) {
            try {
                future.get();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.println("Time Elapse : " + (System.nanoTime() - startNanoTime));

        threadPoolExecutor.shutdown();

    }
}
