/*------------------------------------------------------------------------------
 * COPYRIGHT Ericsson 2014
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/
package impatient.programming.multithreads.sharingobjects.safepublication;

/*
 * 由于并没有对需要被publication的对象holder进行同步，导致可见性问题，其他线程很可能只能看到
 * partial constructed holder.
 * 
 *  To publish an object safely, both the reference to the object and the object's state
 *  must be made visible to other threads at the same time. A properly constructed object can be safely published by:
 *  
 *  .Initialzing an object reference form a static initializer;
 *  .Storing a reference to it into a volatile field or AtomicReference;
 *  .Storing a reference to it into a final field of a properly constructed object; or
 *  .Storing a reference to it into a field that is properly guarded y a lock
 * */
public class UnSafePublication {
    public Holder holder;

    public void initialize() {
        holder = new Holder(42);
    }

    public static class Holder {
        Holder(int i) {
        };
    }

}
