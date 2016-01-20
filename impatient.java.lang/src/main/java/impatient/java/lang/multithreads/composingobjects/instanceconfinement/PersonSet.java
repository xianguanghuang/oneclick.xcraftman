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

import java.util.HashSet;
import java.util.Set;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/*
 * 使用 Instance confinement 去保证线程安全，将集合/数组的成员变量confine 在类里面。
 * 制定访问这些状态变量的访问策略
 * */
@ThreadSafe
public class PersonSet {

    @GuardedBy("this")
    private final Set<Person> mySet = new HashSet<Person>();

    public synchronized void addPerson(Person p) {
        mySet.add(p);
    }

    public synchronized boolean containsPerson(Person p) {
        return mySet.contains(p);
    }

    public static class Person {
    }
}
