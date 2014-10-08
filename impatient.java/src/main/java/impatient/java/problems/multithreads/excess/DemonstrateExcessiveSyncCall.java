/*------------------------------------------------------------------------------
 * COPYRIGHT Ericsson 2014
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/
package impatient.java.problems.multithreads.excess;


public class DemonstrateExcessiveSyncCall {

    private static final int _SYNC_THREAD_NO = 30;

    public static class Monitor {

        private static final int _SLEEP_TIME = 150000;

        public synchronized void synCall() {

            try {
                Thread.currentThread();
                Thread.sleep(_SLEEP_TIME);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public static class SyncCallThread extends Thread {

        private final Monitor monitor;

        public SyncCallThread(Monitor monitor) {
            this.monitor = monitor;
        }

        @Override
        public void run() {
            monitor.synCall();
        }

    }

    public static void main(String[] args) throws Exception {
        Monitor monitor = new Monitor();
        for (int i = 0; i < _SYNC_THREAD_NO; i++) {
            Thread syncCallThread = new SyncCallThread(monitor);
            syncCallThread.start();
        }

        System.out.println("Please, Take the Thread Dump now, you should see " + _SYNC_THREAD_NO
                + " threads wait for Lock");

        System.out.println("After ThreadDump, Just Kill this Process.");
        Thread.currentThread().join();
    }
}
