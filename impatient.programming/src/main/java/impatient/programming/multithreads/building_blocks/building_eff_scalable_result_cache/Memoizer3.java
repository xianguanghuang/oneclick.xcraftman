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

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import net.jcip.annotations.GuardedBy;

/*
 * Memoizer3 将CPU 密集型的任务封装到一个Future 里面让其异步执行，
 * 避免了Memoizer2 的unlucky timing(compute的时候)
 * 每次相同的参数都可以拿到同一个future, 极大提高了并发性.
 * 
 * 但是，Memorizer3 依然会有一丝unlucky timing 会导致重复运算.
 * 
 * 当还在"f(1) not in cache" 的时候，如果刚好有另外一个线程用同样的参数请求，会导致重复运算
 * 
 * A:
 * ---->|f(1) not in cache|---->|put Future for f(1) in cache|---->|    compute f(1)     |---->|set result|
 * 
 * B:
 * ------->|f(1) not in cache|---->|put Future for f(1) in cache|---->|    compute f(1)     |---->|set result|
 * */

public class Memoizer3<A, V> implements Computable<A, V> {

    @GuardedBy("this")
    private final Map<A, Future<V>> cache = new ConcurrentHashMap<A, Future<V>>();
    private final Computable<A, V> c;

    public Memoizer3(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(final A arg) throws InterruptedException {
        Future<V> f = cache.get(arg);
        if (f == null) {
            Callable<V> eval = new Callable<V>() {

                @Override
                public V call() throws Exception {
                    // TODO Auto-generated method stub
                    return c.compute(arg);
                }
            };

            FutureTask<V> ft = new FutureTask<V>(eval);
            f = ft;
            cache.put(arg, ft);
            ft.run(); //call to c.compute happens here & it will return non-blocking
        }

        try {
            return f.get();
        } catch (ExecutionException e) {
            throw launderThrowable(e.getCause());
        }
    }

    private RuntimeException launderThrowable(Throwable t) {
        if (t instanceof RuntimeException) {
            return (RuntimeException) t;
        } else if (t instanceof Error) {
            throw (Error) t;
        } else {
            throw new IllegalStateException("Not unchecked", t);
        }
    }

}
