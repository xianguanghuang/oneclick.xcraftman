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
 * 将集合/数组类型暴露给其他线程，那么其他线程就有可能更改其内部状态。
 * 这种情况会造成Unsafe publication
 * 
 * 
 * 
 * */
public class UnsafeStates {
    private String[] states = new String[] { "AK", "AL" };

    public String[] getStates() {
        return states;
    }

}
