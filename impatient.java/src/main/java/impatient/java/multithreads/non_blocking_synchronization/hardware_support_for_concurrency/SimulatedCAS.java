/*------------------------------------------------------------------------------
 * COPYRIGHT Ericsson 2014
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/
package impatient.java.multithreads.non_blocking_synchronization.hardware_support_for_concurrency;

import net.jcip.annotations.GuardedBy;

/*
 * 这是一个模拟CAS 操作的例子 (并不是模拟其性能)
 * 整个CAS 是一个原子的操作， 
 * 1. 锁定旧的数据
 * 2. 如果旧的数据被其他线程改了，就放弃赋值新的数据
 * 3. 如果旧的数据没有被改，就赋值新的数据
 * 
 * */
public class SimulatedCAS {
    @GuardedBy("this")
    private int value;

    public synchronized int get() {
        return value;
    }

    public synchronized int compareAndSwap(int expectedValue, int newValue) {
        int oldValue = value;
        if (oldValue == expectedValue) {
            value = newValue;
        }
        return oldValue;
    }

    public synchronized boolean compareAndSet(int expectedValue, int newValue) {
        return (expectedValue == compareAndSwap(expectedValue, newValue));
    }

}
