/*------------------------------------------------------------------------------
 * COPYRIGHT Ericsson 2014
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/
package impatient.java.problems.multithreads.waitleak;

public class DemonstrateWaitLeak {

    private static final int _WAIT_THREAD_NO = 30;
    private static final Object monitor = new Object();

    public static class WaitThread extends Thread {

        private static final int _WAIT_TIMEOUT = 1800000;

        @Override
        public void run() {
            synchronized (monitor) {
                try {
                    monitor.wait(_WAIT_TIMEOUT);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

    }

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < _WAIT_THREAD_NO; i++) {
            Thread waitThread = new WaitThread();
            waitThread.start();
        }

        System.out.println("Please, Take the Thread Dump now, you should see " + _WAIT_THREAD_NO + " threads waitting");

        System.out.println("After ThreadDump, Just Kill this Process.");
        Thread.currentThread().join();
    }
}
