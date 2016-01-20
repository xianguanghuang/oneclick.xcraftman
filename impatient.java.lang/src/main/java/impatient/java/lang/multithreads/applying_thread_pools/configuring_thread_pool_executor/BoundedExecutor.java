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

import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.Semaphore;

public class BoundedExecutor {
    private final Executor exec;
    private final Semaphore semaphore;

    public BoundedExecutor(Executor exec, int bound) {

        this.exec = exec;
        this.semaphore = new Semaphore(bound);
    }

    /*
     * 使用Semapore 来限制任务的提交量，而不是直接将任务提交给线程池..
     */
    public void sumbitTask(final Runnable command) throws InterruptedException {
        semaphore.acquire();
        try {
            exec.execute(new Runnable() {

                @Override
                public void run() {
                    command.run();

                }

            });
        } catch (RejectedExecutionException e) {
            semaphore.release();
        }

    }

}
