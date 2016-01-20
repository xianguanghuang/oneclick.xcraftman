/*------------------------------------------------------------------------------
 * COPYRIGHT Ericsson 2014
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/
package impatient.java.lang.multithreads.sharingobjects.visibility;

/*当一个线程更新了共享变量，对另外的一个线程来说是不可见的，因此需要使用同步(包括读和写)来保证内存可见性
 * */
public class NoVisibility {

    private static boolean ready;
    private static int number;

    private static class ReaderThread extends Thread {
        @Override
        public void run() {
            while (!ready) {
                Thread.yield();
            }
            System.out.print(number);

        }
    }

    public static void main(String[] args) {
        new ReaderThread().start();
        number = 42;
        ready = true;
    }

}
