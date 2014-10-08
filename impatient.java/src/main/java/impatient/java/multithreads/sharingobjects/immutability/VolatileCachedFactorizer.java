/*------------------------------------------------------------------------------
 * COPYRIGHT Ericsson 2014
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/
package impatient.java.multithreads.sharingobjects.immutability;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import net.jcip.annotations.Immutable;
import net.jcip.annotations.ThreadSafe;

/*
 * 使用不可变的对象OneValueCache 作为缓存。再使用volatile 保证内存可见性
 * 是CachingFactorizer的另外一种实现的方式
 * */
@ThreadSafe
public abstract class VolatileCachedFactorizer implements Servlet {

    private volatile OneValueCache cache = new OneValueCache(null, null);

    @Override
    public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
        BigInteger i = extractFromRequest(req);
        BigInteger[] factors = cache.getFactors(i);
        if (factors == null) {
            factors = factor(i);
            cache = new OneValueCache(i, factors);
        }
        encodeIntoResponse(resp, factors);

    }

    protected abstract void encodeIntoResponse(ServletResponse resp, BigInteger[] factors);

    protected abstract BigInteger[] factor(BigInteger i);

    protected abstract BigInteger extractFromRequest(ServletRequest req);

    @Immutable
    public static class OneValueCache {
        private final BigInteger lastNumber;
        private final BigInteger[] lastFactors;

        public OneValueCache(BigInteger lastNumber, BigInteger[] factors) {
            this.lastNumber = lastNumber;
            this.lastFactors = Arrays.copyOf(factors, factors.length);
        }

        public BigInteger[] getFactors(BigInteger i) {
            if ((lastNumber == null) || !lastNumber.equals(i)) {
                return null;
            } else {
                return Arrays.copyOf(lastFactors, lastFactors.length);
            }
        }

    }

}
