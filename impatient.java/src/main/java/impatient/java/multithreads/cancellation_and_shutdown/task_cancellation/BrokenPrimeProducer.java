package impatient.java.multithreads.cancellation_and_shutdown.task_cancellation;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/*
 * Don't do this： 
 * Unreliable cancellation that can leave producers stuck in a blocking operation.
 * 
 * 如果在轮询 cancel 变量的过程中有阻塞的方法 ，如 queue.put
 * 就有可能导致无法中止任务
 * 
 * */
@ThreadSafe
public class BrokenPrimeProducer extends Thread {

	private final BlockingQueue<BigInteger> queue;

	private volatile boolean cancelled = false;;

	BrokenPrimeProducer(BlockingQueue<BigInteger> queue) {
		this.queue = queue;
	}

	public void run() {

		try {
			BigInteger p = BigInteger.ONE;
			while (!cancelled) {
				queue.put(p = p.nextProbablePrime());
			}
		} catch (InterruptedException consumed) {

		}

	}

	public void cancel() {
		cancelled = true;
	}

	void consumePrimes() throws InterruptedException {
		BlockingQueue<BigInteger> primes = new ArrayBlockingQueue<BigInteger>(
				100);
		BrokenPrimeProducer producer = new BrokenPrimeProducer(primes);
		producer.start();
		try {
			while (needMorePrimes())
				comsume(primes.take());
		} finally {
			producer.cancel();
		}
	}

	private void comsume(BigInteger take) {
		// TODO Auto-generated method stub
		
	}

	private boolean needMorePrimes() {
		// TODO Auto-generated method stub
		return false;
	}
}
