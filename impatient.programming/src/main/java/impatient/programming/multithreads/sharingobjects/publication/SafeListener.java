/*------------------------------------------------------------------------------
 * COPYRIGHT Ericsson 2014
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/
package impatient.programming.multithreads.sharingobjects.publication;

/*
 * 使用工厂方法，将对象构造完成后再发布
 * */
public class SafeListener {
    private final EventListener listener;

    public static class EventSource {

        public void registerListener(EventListener eventListener) {
            // TODO Auto-generated method stub

        }
    }

    public static interface EventListener {
        public void onEvent(Event e);
    }

    public static class Event {

    }

    private SafeListener() {
        listener = new EventListener() {
            public void onEvent(Event e) {
                doSomething(e);
            }
        };
    }

    private void doSomething(Event e) {
        // TODO Auto-generated method stub

    }

    public static SafeListener newInstance(EventSource source) {
        SafeListener safe = new SafeListener();
        source.registerListener(safe.listener);
        return safe;
    }

}
