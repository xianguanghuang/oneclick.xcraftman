/*------------------------------------------------------------------------------
 * COPYRIGHT Ericsson 2014
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/
package impatient.java.multithreads.building_blocks.blocking_and_interruptible_methods;

import java.util.concurrent.BlockingQueue;

public class TaskRunnable implements Runnable {

    BlockingQueue<Task> queue;

    @Override
    public void run() {
        try {
            processTask(queue.take());
        } catch (InterruptedException e) {

            //restore interrupted status

            /*
             * 当自己不知道Interrupt Policy 的时候，不要swallow interrupt
             * Exception 要将它rethrow.
             */
            Thread.currentThread().interrupt();
        }

    }

    private void processTask(Task task) {
        // TODO Auto-generated method stub

    }

}

class Task {

}
