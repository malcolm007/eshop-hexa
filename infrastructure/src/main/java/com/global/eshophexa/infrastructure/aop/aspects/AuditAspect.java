package com.global.eshophexa.infrastructure.aop.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AuditAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuditAspect.class);

    @Pointcut("@annotation(com.global.eshophexa.infrastructure.aop.annotations.Audit)")
    public void auditPointCut(){
    }

    @Before("auditPointCut()")
    public void audit(JoinPoint joinPoint) {
        LOGGER.info("{} called !", joinPoint.getSignature().getName());
    }
}
