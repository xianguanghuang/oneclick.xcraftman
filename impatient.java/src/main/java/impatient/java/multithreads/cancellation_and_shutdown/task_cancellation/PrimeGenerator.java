package impatient.java.multithreads.cancellation_and_shutdown.task_cancellation;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;


/*
 * 最简单的Cancel Police 定义：
 * 
 * PrimeGenerator uses a simple cancellation policy: client code requests cancellation by calling cancel, PrimeGenerator
 * checkes for cancellation once per prime found and exits when it detects cancellation has been request
 * 
 * 记得要使用 volatile 来保证内存可见性， 这种 cancel policy 仅仅适用于任务是while 循环型的
 * 并没有任何线程中断行为
 *
 * 
 * 
 * */
@ThreadSafe
public class PrimeGenerator implements Runnable {

	@GuardedBy("this")
	private final List<BigInteger> primes = new ArrayList<BigInteger>();
	
	private volatile boolean cancelled;
	
	
	@Override
	public void run() {

		BigInteger p = BigInteger.ONE;
		while(!cancelled){
			p = p.nextProbablePrime();
			synchronized(this){
				primes.add(p);
			}
		}
	}
	
	public void cancel(){ cancelled = true;}
	
	public synchronized List<BigInteger> get(){
		return new ArrayList<BigInteger>(primes);
	}
	
	
	class PrimeGeneratorClient{
		List<BigInteger> aSecondOfPrimes() throws InterruptedException{
			PrimeGenerator generator = new PrimeGenerator();
			new Thread(generator).start();
			try{
				Thread.sleep(1000);
			}finally{
				generator.cancel();
			}
			
			return generator.get();
			
		}
	}

}

