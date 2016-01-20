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

import java.util.concurrent.ThreadFactory;

/*
 * 自定义线程池名称
 * */
public class MyThreadFactory implements ThreadFactory {

    private final String poolName;

    public MyThreadFactory(String poolName) {
        this.poolName = poolName;
    }

    @Override
    public Thread newThread(Runnable r) {
        // TODO Auto-generated method stub
        return new MyAppThread(r, poolName);
    }

}
