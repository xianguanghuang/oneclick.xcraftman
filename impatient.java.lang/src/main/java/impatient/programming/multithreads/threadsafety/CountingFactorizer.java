/*------------------------------------------------------------------------------
 * COPYRIGHT Ericsson 2014
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/
package impatient.programming.multithreads.threadsafety;

import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public abstract class CountingFactorizer implements Servlet {

    /*
     * 成员变量使用线程安全的类 AtomicLong，因此即便不是用同步，也可以保证 线程安全，这也叫做Delegating
     * Thread Safety
     */
    private AtomicLong count = new AtomicLong(0);

    /*
     * 读取成员变量也没有同步，无法保证内存可见
     */
    public long getCount() {
        return count.get();
    }

    @Override
    public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
        BigInteger i = extractFromRequest(req);
        BigInteger[] factors = factor(i);
        count.incrementAndGet();
        encodeIntoResponse(resp, factors);

    }

    protected abstract void encodeIntoResponse(ServletResponse resp, BigInteger[] factors);

    protected abstract BigInteger[] factor(BigInteger i);

    protected abstract BigInteger extractFromRequest(ServletRequest req);

}
