package impatient.java.lang.multithreads.cancellation_and_shutdown.task_cancellation;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;

/*
 * Using interruption for cancellation
 * */
public class PrimeProducer extends Thread {
	private final BlockingQueue<BigInteger> queue;

	PrimeProducer(BlockingQueue<BigInteger> queue) {
		this.queue = queue;
	}

	/*
	 * 这段代码有两个cancel point：一个是在while循环的判断条件，显式判断线程的中断标志 这一个显式判断是用来提高响应时间
	 * 另外一个就是put这个阻塞方法 ，会抛出InterruptedException
	 */
	public void run() {
		try {
			BigInteger p = BigInteger.ONE;
			while (!Thread.currentThread().isInterrupted())
				queue.put(p = p.nextProbablePrime());
		} catch (InterruptedException consumed) {
			/* Allow thread to exit */
		}
	}

	/*
	 * 不再使用cancel标志位，使用中断方法来中断线程
	 */
	public void cancel() {
		interrupt();
	}

	/*实现了一个不可中断的任务，但是要在退出之前恢复线程的中断标志位*/
	public Task getNextTask(BlockingQueue<Task> queue) {
		boolean interrupted = false;
		try {
			while (true) {
				try {
					return queue.take();
				} catch (InterruptedException e) {
					interrupted = true;
					// fall through and retry
				}
			}
		} finally {
			if (interrupted)
				Thread.currentThread().interrupt();
		}
	}

	class Task {
	};

}
