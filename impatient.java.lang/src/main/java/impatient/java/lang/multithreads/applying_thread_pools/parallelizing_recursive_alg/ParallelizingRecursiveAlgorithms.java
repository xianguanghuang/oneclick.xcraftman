/*------------------------------------------------------------------------------
 * COPYRIGHT Ericsson 2014
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/
package impatient.java.lang.multithreads.applying_thread_pools.parallelizing_recursive_alg;

import java.util.Collection;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ParallelizingRecursiveAlgorithms {

    void processSequentially(List<Element<String>> elements) {
        for (Element<String> e : elements) {
            process(e);
        }

    }

    /*
     * 使用线程池，提高并发性能， 这种情况只有在process e 是cpu 密集任务是 才可以体现优势
     */
    void processInParallel(Executor exec, List<Element<String>> elements) {
        for (final Element<String> e : elements) {
            exec.execute(new Runnable() {

                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    process(e);
                }

            });
        }
    }

    public <T> void sequentialRecursive(List<Element<T>> nodes, Collection<T> results) {
        for (Element<T> n : nodes) {
            results.add(n.compute());
            sequentialRecursive(n.getChildren(), results);
        }
    }

    /*
     * 同样道理，使用并行递归算法也可以提高性能
     */
    public <T> void parallelRecursive(final Executor exec, List<Element<T>> nodes, final Collection<T> results) {
        for (final Element<T> n : nodes) {
            exec.execute(new Runnable() {

                @Override
                public void run() {
                    results.add(n.compute());

                }

            });
            parallelRecursive(exec, n.getChildren(), results);

        }

    }

    /*
     * 使用ParallelRecursive的例子 1. 创建executor 2.
     * 创建一个ConcurrentlinkedQueue
     * 用于保存并发运算产生的结果，使用ConcurrentLinkedQueue是为了保证线程安全 3. shutdown +
     * awaitTermination 就可以得到运算结果了
     */
    public <T> Collection<T> getParallelResults(List<Element<T>> nodes) throws InterruptedException {

        ExecutorService exec = Executors.newCachedThreadPool();
        Queue<T> reusltQueue = new ConcurrentLinkedQueue<T>();
        parallelRecursive(exec, nodes, reusltQueue);
        exec.shutdown();
        exec.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
        return reusltQueue;

    }

    private void process(Element<?> e) {
        // TODO Auto-generated method stub

    }

}

class Element<T> {

    public T compute() {
        // TODO Auto-generated method stub
        return null;
    }

    public List<Element<T>> getChildren() {
        // TODO Auto-generated method stub
        return null;
    }

}