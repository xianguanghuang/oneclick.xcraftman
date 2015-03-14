/*------------------------------------------------------------------------------
 * COPYRIGHT Ericsson 2014
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/
package impatient.programming.multithreads.cancellation_and_shutdown.stopping_a_thread_base_service;

import java.io.PrintWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import net.jcip.annotations.GuardedBy;

/*
 * 
 * 使用同步保证log的cancel
 * */
public class LogServiceExecutorService {

    private static final long TIMEOUT = 0;
    private static final TimeUnit UNIT = TimeUnit.SECONDS;
    private final ExecutorService exec = Executors.newSingleThreadExecutor();
    private final PrintWriter writer;

    @GuardedBy("this")
    private boolean isShutdown;

    @GuardedBy("this")
    private int reservations;

    public LogServiceExecutorService(PrintWriter writer) {
        this.writer = writer;
    }

    public void start() {

    }

    /*
     * 使用Thread Pool executor 来关闭线程池这照哦给你方式更加优雅
     */
    public void stop() throws InterruptedException {
        try {
            exec.shutdown();
            exec.awaitTermination(TIMEOUT, UNIT);
        } finally {
            writer.close();
        }
    }

    public void log(String msg) throws InterruptedException {
        exec.execute(new WriterTask(msg));
    }

    private class WriterTask implements Runnable {

        private final String message;

        WriterTask(String message) {
            this.message = message;
        }

        @Override
        public void run() {
            try {
                writer.println(message);
            } finally {
                writer.close();

            }
        }
    }

}
