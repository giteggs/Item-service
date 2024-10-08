package com.org.Item.loggingAspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component

public class LoggingAspect {
	
	  private static final org.slf4j.Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

	    @Around("execution(* com.example.demo..*(..))")
	    public Object logRequestsAndResponses(ProceedingJoinPoint joinPoint) throws Throwable {
	        logger.info("Request: {}", joinPoint.getArgs());

	        Object result = joinPoint.proceed();

	        logger.info("Response: {}", result);
	        return result;
	    }	

}
