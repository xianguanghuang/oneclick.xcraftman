/*------------------------------------------------------------------------------
 * COPYRIGHT Ericsson 2014
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/
package impatient.java.lang.multithreads.sharingobjects.threadconfinement;

import java.util.Collection;
import java.util.SortedSet;
import java.util.TreeSet;

public class StackConfinement {

    private Ark ark = new Ark();

    /*
     * 将传进来的集合对象复制一份，保存在栈空间里面. 避免线程问题
     */
    public int loadTheArk(Collection<Animal> candidates) {
        SortedSet<Animal> animals;
        int numPairs = 0;
        Animal candidate = null;

        //animals confined to method, don't let them escape!
        animals = new TreeSet<Animal>();
        animals.addAll(candidates);

        for (Animal a : animals) {
            if ((candidate == null) || !candidate.isPotentialMate(a)) {
                candidate = a;
            } else {
                ark.load(new AnimalPair(candidate, a));
                ++numPairs;
                candidate = null;
            }
        }
        return numPairs;
    }

    public static class Animal {

        public boolean isPotentialMate(Animal a) {
            // TODO Auto-generated method stub
            return false;
        }

    }

    public static class Ark {
        public void load(AnimalPair animalPair) {

        }
    }

    public static class AnimalPair {

        public AnimalPair(Animal candidate, Animal a) {
            // TODO Auto-generated constructor stub
        }

    }
}
