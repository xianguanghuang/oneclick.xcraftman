/*------------------------------------------------------------------------------
 * COPYRIGHT Ericsson 2014
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/
package impatient.programming.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class ServiceExecutionMonitor {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /*
     * Point Cut 定义: 只需要定义接口即可以捕捉到实现类
     */
    @After("execution(* impatient.java.spring.aop.ServiceExecution.*(..))")
    public void monitorExecution(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();

        logger.info("Monitor Call with in param : {}", args[0]);

    }

}
