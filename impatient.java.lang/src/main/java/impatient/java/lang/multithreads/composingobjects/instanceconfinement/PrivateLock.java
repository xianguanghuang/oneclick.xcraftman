/*------------------------------------------------------------------------------
 * COPYRIGHT Ericsson 2014
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/
package impatient.java.lang.multithreads.composingobjects.instanceconfinement;

import net.jcip.annotations.GuardedBy;

/*
 * 使用Private lock 去实现更复杂的锁策略
 * */
public class PrivateLock {

    private final Object myLock = new Object();
    @GuardedBy("myLock")
    private Widget widget;

    void someMetod() {
        synchronized (myLock) {
            //Access or modify the state of widget
        }
    }

}

class Widget {
};