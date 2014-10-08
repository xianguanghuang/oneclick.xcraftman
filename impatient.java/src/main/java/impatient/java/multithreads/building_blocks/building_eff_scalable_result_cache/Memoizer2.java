/*------------------------------------------------------------------------------
 * COPYRIGHT Ericsson 2014
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/
package impatient.java.multithreads.building_blocks.building_eff_scalable_result_cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.jcip.annotations.GuardedBy;

/*
 * Memoizer2 使用了ConcurrentHashMap 作为替代，并发性能提高了不少(由于使用了分段式的hashmap 数据结构)
 * 
 * 
 * 线程A:
 * ---->|L|compute f(1)|U|
 * 
 * B:
 * ---->|L|compute f(2)|U|
 * 
 * C:
 * ---->|L|return cached f(1)|U|
 * 
 * 
 * 但是,重更细的情况来看，始终 有些unlucky timing(正在"compute f(1)" 的时候另外一个线程用同样的参数进来)
 *  会影响程序的并发性
 * 
 * 当还在"compute f(1)" 的时候，刚好有另外一个线程用同样的参数请求，会导致重复运算
 * 
 * A:
 * ---->|f(1) not in cache|---->|    compute f(1)     |---->|add f(1) to chache|
 * 
 * ------------------------------->|f(1) not in cache|---->|compute f(1)|---->|add f(1) to chache|
 * */

public class Memoizer2<A, V> implements Computable<A, V> {

    @GuardedBy("this")
    private final Map<A, V> cache = new ConcurrentHashMap<A, V>();
    private final Computable<A, V> c;

    public Memoizer2(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(A arg) throws InterruptedException {
        V result = cache.get(arg);
        if (result == null) {
            result = c.compute(arg);
            cache.put(arg, result);
        }
        return result;
    }

}
