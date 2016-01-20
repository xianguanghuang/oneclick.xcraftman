package impatient.java.lang.multithreads.performance_and_scalability.reducing_lock_contension;

import java.util.Set;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;


/*
 * 
 * 
 * 
 * 将synchronized 的锁的粒度拆成两个更小的锁 
 * 这中spliting lock 的做法只有在lock contention 不高和不低的情况下比较有用
 * 如果太低，性能提升得不是很明显
 * 如果太高，反而会增加 lock contention 的激烈程度
 * */
@ThreadSafe
public class ServerStatus {
	
	@GuardedBy("this")
	public final Set<String> users;
	
	@GuardedBy("this")
	public final Set<String> queries;

	public ServerStatus(Set<String> users,
			Set<String> queries) {
		this.users = users;
		this.queries = queries;
	}
	
	public void addUser(String u){
		synchronized(users){
			users.add(u);
		}
	}
	
	public synchronized void addQuery(String q){
		synchronized(queries){
			queries.add(q);
		}
	}
	
	public synchronized void removeUser(String u){
		synchronized(users){
			users.remove(u);
		}
	}
	
	public synchronized void removeQuery(String q){
		synchronized(queries){
			queries.remove(q);
		}
	}
	
	
	

}
