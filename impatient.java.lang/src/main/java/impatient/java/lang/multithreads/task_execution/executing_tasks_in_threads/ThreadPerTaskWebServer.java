/*------------------------------------------------------------------------------
 * COPYRIGHT Ericsson 2014
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/
package impatient.java.lang.multithreads.task_execution.executing_tasks_in_threads;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * 每一个请求就创建一个线程去处理。
 * 虽然可以提高并发量
 * 但是创建线程是一件比较消耗资源的过程，过高的并发量会导致JVM 崩溃
 * */
public class ThreadPerTaskWebServer {

    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(80);
        while (true) {
            final Socket connection = socket.accept();
            Runnable task = new Runnable() {
                public void run() {
                    handleRequest(connection);
                }
            };
            new Thread(task).start();
        }
    }

    protected static void handleRequest(Socket connection) {
        // TODO Auto-generated method stub

    }

}
