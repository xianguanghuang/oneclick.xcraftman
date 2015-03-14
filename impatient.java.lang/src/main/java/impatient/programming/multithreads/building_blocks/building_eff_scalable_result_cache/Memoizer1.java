/*------------------------------------------------------------------------------
 * COPYRIGHT Ericsson 2014
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/
package impatient.programming.multithreads.building_blocks.building_eff_scalable_result_cache;

import java.util.HashMap;
import java.util.Map;

import net.jcip.annotations.GuardedBy;

/*
 * Memoizer1 虽然是线程安全的，但是并发性很差
 * 
 * 
 * 线程A:
 * ---->|L|compute f(1)|U|
 * 
 * B:
 * ---------------------->|L|compute f(2)|U|
 * 
 * C:
 * ---------------------------------------->|L|return cached f(1)|U|
 * */
public class Memoizer1<A, V> implements Computable<A, V> {

    @GuardedBy("this")
    private final Map<A, V> cache = new HashMap<A, V>();
    private final Computable<A, V> c;

    public Memoizer1(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public synchronized V compute(A arg) throws InterruptedException {
        V result = cache.get(arg);
        if (result == null) {
            result = c.compute(arg);
            cache.put(arg, result);
        }
        return result;
    }

}
