package impatient.java.multithreads.performance_and_scalability.reducing_lock_contension;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class AttributeStore {
	
	
	/*
	 * Bad Example
	 * 这不是一个好的例子，因为只有attribute的 get方法才需要线程安全，其他部分可以离开synchronized的部分 
	 * */
	@GuardedBy("this")
	private final Map<String, String> attributes = new HashMap<String,String>();
	
	public synchronized boolean userLocationMatches(String name, String regexp){
		
		String key = "users." + name + ".location";
		String location = attributes.get(key);
		if(location == null)
			return false;
		else
			return Pattern.matches(regexp, location);
	}
	

}
