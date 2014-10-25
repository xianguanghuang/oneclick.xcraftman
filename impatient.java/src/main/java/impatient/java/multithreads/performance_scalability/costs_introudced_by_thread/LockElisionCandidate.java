/*------------------------------------------------------------------------------
 * COPYRIGHT Ericsson 2014
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/
package impatient.java.multithreads.performance_scalability.costs_introudced_by_thread;

import java.util.List;
import java.util.Vector;

public class LockElisionCandidate {

    /*
     * 虽然Vector 是通过synchroized 保证线程安全， 但是stooges 是在一个方法内创建， 所以很多JVM
     * 会把synchroized 产生的锁去掉
     */
    public String getStoogeNames() {
        List<String> stooges = new Vector<String>();

        stooges.add("Mod");
        stooges.add("Larry");
        stooges.add("Curly");

        return stooges.toString();

    }

}
