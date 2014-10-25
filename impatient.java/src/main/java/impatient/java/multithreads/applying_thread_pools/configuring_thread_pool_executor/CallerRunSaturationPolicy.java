/*------------------------------------------------------------------------------
 * COPYRIGHT Ericsson 2014
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/
package impatient.java.multithreads.applying_thread_pools.configuring_thread_pool_executor;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CallerRunSaturationPolicy {

    private static final int N_THREADS = 0;
    private static final int CAPACITY = 0;

    /*
     * CallerRunsPolicy
     * 的作用是当线程池队列满了，新提交的任务就会在主线程(或者说是调用ThreadPoolExecutor 的线程)去运行。
     * 而不是直接丢弃任务
     */
    public static void main(String[] args) {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(N_THREADS, N_THREADS, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(CAPACITY));

        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

    }
}
