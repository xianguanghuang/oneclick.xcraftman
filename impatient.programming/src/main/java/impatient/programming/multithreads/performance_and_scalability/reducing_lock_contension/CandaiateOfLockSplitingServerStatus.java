package impatient.programming.multithreads.performance_and_scalability.reducing_lock_contension;

import java.util.Set;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;


/*
 * 
 * Bad Example
 * 由于users 和 queries 是独立的状态变量，
 * 因此可以将synchronized 的锁的粒度拆成两个更小的锁 
 * */
@ThreadSafe
public class CandaiateOfLockSplitingServerStatus {
	
	@GuardedBy("this")
	public final Set<String> users;
	
	@GuardedBy("this")
	public final Set<String> queries;

	public CandaiateOfLockSplitingServerStatus(Set<String> users,
			Set<String> queries) {
		this.users = users;
		this.queries = queries;
	}
	
	public synchronized void addUser(String u){
		users.add(u);
	}
	
	public synchronized void addQuery(String q){
		queries.add(q);
	}
	
	public synchronized void removeUser(String u){
		users.remove(u);
	}
	
	public synchronized void removeQuery(String q){
		queries.remove(q);
	}
	
	
	

}
