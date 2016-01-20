/*------------------------------------------------------------------------------
 * COPYRIGHT Ericsson 2014
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/
package impatient.java.lang.multithreads.threadsafety.locking;

/*
 * 
 * 当同一个线程获得一个锁时候，对于其他使用同一个锁的其他方法也可以访问(不用再次等待自己已经获得的锁)。
 * 这就可以保证不会出现死锁。 这种特性叫做reentrance.
 *
 * */
public class DemostrateReentrance {

    public static class Widget {
        public synchronized void doSomething() {

            // nothing here
        }
    }

    public static class LoggingWidget extends Widget {
        @Override
        public synchronized void doSomething() {
            super.doSomething();

        }
    }

}
