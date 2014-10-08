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

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import net.jcip.annotations.GuardedBy;

/*
 * Memoizer 是最终的实现版本
 * 
 * 使用putIfAbsent 去做最后一道屏障组织重复运算同一个参数
 * 
 * TODO: 验证While True 的作用， 我感觉没有必要
 *       AP: check jcip 源代码
 *       DIFF: 100
 * */

public class Memoizer<A, V> implements Computable<A, V> {

    @GuardedBy("this")
    private final ConcurrentMap<A, Future<V>> cache = new ConcurrentHashMap<A, Future<V>>();
    private final Computable<A, V> c;

    public Memoizer(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(final A arg) throws InterruptedException {
        while (true) {// while true 其实没有任何作用
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
                f = cache.putIfAbsent(arg, ft); //使用putIfAbsent不仅可以保证线程安全，还可以保证不重复运算
                if (f == null) {//如果这个时候，其他线程已经把同一个值得FuterTask 放到cache 里面，则不需要再次进行运算
                    f = ft;
                    ft.run(); //call to c.compute happens here & it will return non-blocking
                }
            }

            try {
                return f.get();
            } catch (ExecutionException e) {
                throw launderThrowable(e.getCause());
            }
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
