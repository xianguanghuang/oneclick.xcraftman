package impatient.java.multithreads.performance_and_scalability.reducing_lock_contension;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class BetterAttributeStore {
	
	
	/*
	 * 
	 * 这是一个相对比较好的示范，因为只同步attribute的那个部分 
	 * */
	@GuardedBy("this")
	private final Map<String, String> attributes = new HashMap<String,String>();
	
	public boolean userLocationMatches(String name, String regexp){
		
		String key = "users." + name + ".location";
		String location;
		synchronized(this){
			location = attributes.get(key);;
		}
		
		if(location == null)
			return false;
		else
			return Pattern.matches(regexp, location);
	}
	

}
