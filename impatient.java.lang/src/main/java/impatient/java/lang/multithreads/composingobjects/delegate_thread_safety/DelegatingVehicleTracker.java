/*------------------------------------------------------------------------------
 * COPYRIGHT Ericsson 2014
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/
package impatient.java.lang.multithreads.composingobjects.delegate_thread_safety;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import net.jcip.annotations.Immutable;

/*
 * Delegating Thread Saftey 的意思是Tracker 本身并没有任何的同步语句，所有的线程安全问题都留给(委托)
 * 他们的成员变量去解决(location, 和 location 的unmodifiableMap)
 * 
 * 对于以下的例子，需要满足几个条件:
 * .成员变量是集合/数组类型
 * .对于集合/数组类型里面的每一个元素都是不可变的，这可以确保client 读取元素出来后也无法改变其状态
 * .当需要读取所有location 信息的时候， 直接返回locations 的unmodifiable 版本
 * .当需要更新location 信息的时候，直接调用locations的replace方法 (因为location 是concurrenthashMap). 
 * 
 * */
public class DelegatingVehicleTracker {

    private final ConcurrentMap<String, Point> locations;
    private final Map<String, Point> unmodifiableMap;

    /* 由于point 对象是线程安全的，因此传进来的Map<String, Point> points 无需要做deepCopy */
    public DelegatingVehicleTracker(Map<String, Point> points) {
        this.locations = new ConcurrentHashMap<String, Point>(points);
        this.unmodifiableMap = Collections.unmodifiableMap(locations);
    }

    public Map<String, Point> getLocations() {
        return unmodifiableMap;
    }

    public Point getLocation(String id) {
        return locations.get(id);
    }

    public void setLocation(String id, int x, int y) {
        if (locations.replace(id, new Point(x, y)) == null) {
            throw new IllegalArgumentException("invalid vehicle name: " + id);
        }
    }

}

@Immutable
class Point {
    public final int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
