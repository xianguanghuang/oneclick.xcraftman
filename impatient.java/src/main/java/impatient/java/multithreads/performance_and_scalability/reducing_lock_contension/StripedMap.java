package impatient.java.multithreads.performance_and_scalability.reducing_lock_contension;

import net.jcip.annotations.ThreadSafe;


/*
 * 这是一个lock strip 的例子，lockstrip 主要针对集合数据类型
 * 原来是一个同步锁去保证整个数据类型，现在是使用分段锁，提高scalability
 * */
@ThreadSafe
public class StripedMap {

	// Synchronizatinon policy : bucket[n] guarded by locks[n%N_LOCKS]
	private static final int N_LOCKS = 16;
	private final Node[] buckets;
	private final Object[] locks;

	public static class Node{

		public Node next;
		public Object key;
		public Object value;
		
	}

	public StripedMap(int numBuckets) {
		this.buckets = new Node[numBuckets];
		this.locks = new Object[N_LOCKS];
		
		for (int i =0; i < N_LOCKS; i++){
			locks[i] = new Object();
		}
	}
	
	private final int hash(Object key){
		return Math.abs(key.hashCode() % buckets.length);
	}
	
	public Object get(Object key){
		int hash = hash(key);
		synchronized(locks[hash % N_LOCKS]){
			for (Node m = buckets[hash]; m !=null; m = m.next)
				if(m.key.equals(key))
					return m.value;
		}
		return null;
	}
	
	public void clear(){
		for(int i = 0; i < buckets.length; i++){
			synchronized(locks[i % N_LOCKS]){
				buckets[i] = null;
			}
		}
	}
	
	
}


