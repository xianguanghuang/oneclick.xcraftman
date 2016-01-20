/*------------------------------------------------------------------------------
 * COPYRIGHT Ericsson 2014
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/
package impatient.java.lang.multithreads.sharingobjects.publication;

/*
 * EventListener 可以在ThisEscape 还没有初始化完成就访问ThisEscape 里面的成员变量和方法
 * 因此会造成Unsafe publication
 * 
 * */
public class ThisEscape {

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

    public ThisEscape(EventSource source) {
        source.registerListener(new EventListener() {
            public void onEvent(Event e) {
                doSomething(e);
            }

            private void doSomething(Event e) {
                // TODO Auto-generated method stub

            }
        });
    }

}
