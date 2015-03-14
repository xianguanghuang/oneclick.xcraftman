/*------------------------------------------------------------------------------
 * COPYRIGHT Ericsson 2014
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/
package impatient.programming.multithreads.sharingobjects.immutability;

import java.util.HashSet;
import java.util.Set;

/*
 * 不可变的对象永远是线程安全的
 * 
 * 一个对象是线程安全的，当满足一下条件:
 * .Its state cannnot be modified after construction
 * .All its fields are final: and
 * .It is properly contructed (the this reference does not escape during construction);
 * 
 * */
public final class ThreeStooges {
    private final Set<String> stooges = new HashSet<String>();

    public ThreeStooges() {
        stooges.add("Moe");
        stooges.add("Larry");
        stooges.add("Curly");
    }

    public boolean isStooge(String name) {
        return stooges.contains(name);

    }

}
