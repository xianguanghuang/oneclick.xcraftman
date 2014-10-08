/*------------------------------------------------------------------------------
 * COPYRIGHT Ericsson 2014
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/
package impatient.java.multithreads.threadsafety;

import net.jcip.annotations.NotThreadSafe;

@NotThreadSafe
public class LazyInitRace {

    public static class ExpensiveObject {
    };

    /*
     * 因为当多线程访问getInstance的时候并没有对其进行同步加锁，所以不是
     * 线程安全的，很可能造成ExpensiveObjecg 重复创建
     */
    private ExpensiveObject instance = null;

    public ExpensiveObject getInstance() {
        if (instance == null) {
            instance = new ExpensiveObject();
        }
        return instance;
    }

}
