/*------------------------------------------------------------------------------
 * COPYRIGHT Ericsson 2014
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/
package impatient.java.lang.multithreads.applying_thread_pools.configuring_thread_pool_executor;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * 使用自定义的线程，可以定制debug 信息
 * */
public class MyAppThread extends Thread {

    public static final String DEFAULT_NAME = "MyAppThread";
    private static volatile boolean debugLifecycle = false;
    private static final AtomicInteger created = new AtomicInteger();
    private static final AtomicInteger alive = new AtomicInteger();
    private static final Logger log = Logger.getAnonymousLogger();

    public MyAppThread(Runnable r) {
        this(r, DEFAULT_NAME);
    }

    public MyAppThread(Runnable runnable, String name) {

        super(runnable, name + "-" + created.incrementAndGet());
        setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {

            @Override
            public void uncaughtException(Thread t, Throwable e) {

                log.log(Level.SEVERE, "UNCAUGHT in thread " + t.getName(), e);

            }
        });
    }

    @Override
    public void run() {
        //Copy debug flag to ensure consistent value throughout.
        boolean debug = debugLifecycle;
        if (debug) {
            log.log(Level.FINE, "Created " + getName());
        }

        try {
            alive.incrementAndGet();
            super.run();
        } finally {
            alive.decrementAndGet();
            if (debug) {
                log.log(Level.FINE, "Exiting " + getName());
            }
        }
    }

    public static synchronized boolean isDebugLifecycle() {
        return debugLifecycle;
    }

    public static synchronized void setDebugLifecycle(boolean debugLifecycle) {
        MyAppThread.debugLifecycle = debugLifecycle;
    }

    public static synchronized AtomicInteger getCreated() {
        return created;
    }

    public static synchronized AtomicInteger getAlive() {
        return alive;
    }

}
