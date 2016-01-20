/*------------------------------------------------------------------------------
 * COPYRIGHT Ericsson 2014
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/
package impatient.java.lang.multithreads.composingobjects.instanceconfinement;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.NotThreadSafe;

/*
 * 使用deepcopy 保证非线程安全对象MutablePoint 禁锢在对象以内.  
 * 每次获取Location 的时候(getLocation 和 getLocations)，都会返回MutablePoint 的复制，
 * 使得其他线程无法直接访问， 从而保证其线程安全
 * */
public class MonitorVehicleTracker {

    @GuardedBy("this")
    private final Map<String, MutablePoint> locations;

    public MonitorVehicleTracker(Map<String, MutablePoint> locations) {
        this.locations = deepCopy(locations);
    }

    public synchronized Map<String, MutablePoint> getLocations() {
        return deepCopy(locations);
    }

    public synchronized MutablePoint getLocation(String id) {
        MutablePoint loc = locations.get(id);
        return loc == null ? null : new MutablePoint(loc);
    }

    public synchronized void setLocation(String id, int x, int y) {
        MutablePoint loc = locations.get(id);
        if (loc == null) {
            throw new IllegalArgumentException("No such ID: " + id);
        }
        loc.x = x;
        loc.y = y;
    }

    private Map<String, MutablePoint> deepCopy(Map<String, MutablePoint> m) {

        Map<String, MutablePoint> result = new HashMap<String, MutablePoint>();

        for (String id : m.keySet()) {
            result.put(id, new MutablePoint(m.get(id)));
        }

        /*
         * unmodifiableMap 并不能保证同步，只能保证put 等对map 进行的写操作是不允许的
         */
        return Collections.unmodifiableMap(result);
    }

}

class TrackerClient {

    MonitorVehicleTracker vehicleTracker = new MonitorVehicleTracker(new HashMap<String, MutablePoint>());
    Map<String, MutablePoint> locations = vehicleTracker.getLocations();

    public void render() {

        for (String key : locations.keySet()) {
            renderVehicle(key, locations.get(key));
        }
    }

    public void vehicleMoved(VehicleMovedEvent evt) {
        MutablePoint loc = evt.getNewLocation();
        vehicleTracker.setLocation(evt.getVehicleId(), loc.x, loc.y);
    }

    private void renderVehicle(String key, MutablePoint mutablePoint) {
        // TODO Auto-generated method stub

    }

}

@NotThreadSafe
class MutablePoint {
    public int x, y;

    public MutablePoint() {
        x = 0;
        y = 0;
    }

    public MutablePoint(MutablePoint p) {
        this.x = p.x;
        this.y = p.y;
    }

}

class VehicleMovedEvent {

    public MutablePoint getNewLocation() {
        // TODO Auto-generated method stub
        return null;
    }

    public String getVehicleId() {
        // TODO Auto-generated method stub
        return null;
    }

}