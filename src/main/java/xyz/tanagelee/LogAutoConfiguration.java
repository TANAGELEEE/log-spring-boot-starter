
package xyz.tanagelee;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.PriorityOrdered;
import org.springframework.util.StopWatch;

/**
 * LogAutoConfiguration
 *
 * @author liyunjun
 * @date 2021/4/15 11:23
 */
@Aspect
@EnableAspectJAutoProxy(exposeProxy = true, proxyTargetClass = true)
@Configuration
@ConditionalOnProperty(prefix = "log", name = "enable",
        havingValue = "true", matchIfMissing = true)
public class LogAutoConfiguration implements PriorityOrdered {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Around("@annotation(xyz.tanagelee.log) ")
    public Object isOpen(ProceedingJoinPoint thisJoinPoint)
            throws Throwable {
        //执行方法名称
        String taskName = thisJoinPoint.getSignature()
                .toString().substring(
                        thisJoinPoint.getSignature()
                                .toString().indexOf(" "),
                        thisJoinPoint.getSignature().toString().indexOf("("));
        taskName = taskName.trim();
        StopWatch sw = new StopWatch(taskName + "-" + System.currentTimeMillis());
        sw.start(taskName);
        Object result = thisJoinPoint.proceed();
        sw.stop();
        logger.info(sw.prettyPrint());
        return result;
    }

    @Override
    public int getOrder() {
        return Integer.MAX_VALUE;
    }
}