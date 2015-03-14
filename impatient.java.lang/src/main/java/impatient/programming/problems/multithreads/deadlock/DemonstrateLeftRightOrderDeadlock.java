/*------------------------------------------------------------------------------
 * COPYRIGHT Ericsson 2014
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/
package impatient.programming.problems.multithreads.deadlock;

/**
 * DemonstrateDeadlock
 * <p/>
 * Driver loop that induces deadlock under typical conditions
 * 
 * @author Brian Goetz and Tim Peierls
 */
public class DemonstrateLeftRightOrderDeadlock {
    private static final int NUM_THREADS = 20;
    private static final int NUM_ACCOUNTS = 5;
    private static final int NUM_ITERATIONS = 1000;

    public static void main(String[] args) {
        final LeftRightDeadLock leftRightDeadLock = new LeftRightDeadLock();

        class LeftRightSticklyThread extends Thread {
            @Override
            public void run() {
                for (int i = 0; i < NUM_ITERATIONS; i++) {
                    leftRightDeadLock.leftRight();
                }
            }
        }

        class RightLeftSticklyThread extends Thread {
            @Override
            public void run() {
                for (int i = 0; i < NUM_ITERATIONS; i++) {
                    leftRightDeadLock.rightLeft();
                }
            }
        }

        for (int i = 0; i < NUM_THREADS; i++) {
            new LeftRightSticklyThread().start();
            new RightLeftSticklyThread().start();
        }
    }
}
