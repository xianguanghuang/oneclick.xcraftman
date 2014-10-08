/*------------------------------------------------------------------------------
 * COPYRIGHT Ericsson 2014
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/
package impatient.java.multithreads.composingobjects.delegate_thread_safety;

import java.util.concurrent.atomic.AtomicInteger;

/*
 * 这是一个反例子
 * 当类依赖两个状态成员变量以上，而且这两个成员变量是互相依赖的，
 * 即便这两个成员变量是线程安全的，也无法简单做Thread safety delegation
 * 需要进行同步
 * */
public class NumberRange {

    //INVARIANT: lower <= upper
    private final AtomicInteger lower = new AtomicInteger(0);
    private final AtomicInteger upper = new AtomicInteger(0);

    public void setLower(int i) {
        //Warning -- unsafe check-then-act
        if (i > upper.get()) {
            throw new IllegalArgumentException("can't set lower to " + i + " > upper");
        }
        lower.set(i);
    }

    public void setUpper(int i) {
        //Warning -- unsafe check-then-act
        if (i < lower.get()) {
            throw new IllegalArgumentException("can't set upper to " + i + " < lower");
        }
        upper.set(i);

    }

    public boolean isInRange(int i) {
        //Warning -- not atomic operation 
        return ((i >= lower.get()) && (i <= upper.get()));
    }
}
