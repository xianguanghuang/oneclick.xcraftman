/*------------------------------------------------------------------------------
 * COPYRIGHT Ericsson 2014
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/
package impatient.programming.multithreads.task_execution.executor_framework;

import java.util.Timer;
import java.util.TimerTask;

public class OutOfTime {

    public static void main(String[] args) throws Exception {
        Timer timer = new Timer();
        timer.schedule(new ThrowTask(), 1);

        Thread.sleep(1000);

        /*
         * 这段代码演示了尽量不要再使用Timer ， 因为如果第一个timerTask 抛了Exception 第二个timer
         * 开始的时候就会出现 java.lang.IllegalStateException: Timer already
         * cancelled. 的错误
         */
        timer.schedule(new ThrowTask(), 1);
        Thread.sleep(5000);

    }

}

class ThrowTask extends TimerTask {

    @Override
    public void run() {
        throw new RuntimeException();

    }

}