/*------------------------------------------------------------------------------
 * COPYRIGHT Ericsson 2014
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/
package impatient.java.lang.multithreads.task_execution.executor_framework;

import java.util.concurrent.Executor;

/*
 * 只要将Executor 稍微修改，就可以变成之前的Thread Per request 版本
 * 但是不推荐这样做
 * */
public class ThreadPerTaskExecutor implements Executor {

    @Override
    public void execute(Runnable command) {

        new Thread(command).start();

    }

}
