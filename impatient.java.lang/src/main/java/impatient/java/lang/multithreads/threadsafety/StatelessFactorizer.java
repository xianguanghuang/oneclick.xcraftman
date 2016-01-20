/*------------------------------------------------------------------------------
 * COPYRIGHT Ericsson 2014
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/
package impatient.java.lang.multithreads.threadsafety;

import java.io.IOException;
import java.math.BigInteger;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/*
 * 无状态的对象永远是线程安全的
 * 在service 方法中，所有的变量都是临时变量(不是成员变量),不会与其他线程共享。
 * 因此都是线程安全的
 * */
public abstract class StatelessFactorizer implements Servlet {

    @Override
    public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
        BigInteger i = extractFromRequest(req);
        BigInteger[] factors = factor(i);
        encodeIntoResponse(resp, factors);

    }

    protected abstract BigInteger[] factor(BigInteger i);

    protected abstract void encodeIntoResponse(ServletResponse resp, BigInteger[] factors);

    protected abstract BigInteger extractFromRequest(ServletRequest req);

}
