/*------------------------------------------------------------------------------
 * COPYRIGHT Ericsson 2014
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/
package impatient.java.multithreads.task_execution.executor_framework;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

/*
 * 当调用Executor 的shutdown 方法后，Executor 的状态会在所有Task 结束后转为terminated 的
 * 状态。
 * 
 * 通常会在调用shutdown方法后调用awaitTermination来等待整个线程池关闭
 * 
 * */
public class LifecycleWebServer {

    private static final int NTHREAD = 100;
    private final ExecutorService exec = Executors.newFixedThreadPool(NTHREAD);

    public void start() throws IOException {
        ServerSocket socket = new ServerSocket(80);
        while (!exec.isShutdown()) {
            try {
                final Socket conn = socket.accept();
                exec.execute(new Runnable() {

                    @Override
                    public void run() {
                        handleRequest(conn);
                    }

                });
            } catch (RejectedExecutionException e) {
                if (!exec.isShutdown()) {
                    System.out.println("task submission rejected");
                }
            }
        }
    }

    public void stop() {
        exec.shutdown();
    }

    protected void handleRequest(Socket conn) {
        // TODO Auto-generated method stub

    }

}
