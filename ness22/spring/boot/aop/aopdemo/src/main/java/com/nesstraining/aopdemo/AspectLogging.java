package com.nesstraining.aopdemo;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Aspect
@Component
public class AspectLogging {

    private static final Logger Log = LoggerFactory.getLogger(AspectLogging.class);

        @Before(value = "execution(*  Calculator.*(..)) ")
        public void advice1(JoinPoint point) {
            Log.info("*** inside advice1");
            Signature signature = point.getSignature();
            Object[] args = point.getArgs();
            List argsList = Arrays.asList(args);
            Log.info("args=" + argsList);
            Log.info("signature=" + signature);
        }


        @AfterReturning(value = "execution(*  Calculator.*(..)) ", returning = "result")
        public void advice2(JoinPoint point, Object result) {
            Log.info("*** inside advice2");
            Signature signature = point.getSignature();
            Object[] args = point.getArgs();
            List argsList = Arrays.asList(args);
            Log.info("args=" + argsList);
            Log.info("signature=" + signature);
            Log.info("result=" + result);
        }

    @Around(value = "execution(*  Calculator.*(..)) ")
    public Object advice3(ProceedingJoinPoint point) throws Throwable {
        Log.info("*** inside advice1");
        Signature signature = point.getSignature();
        Object[] args = point.getArgs();
        List argsList = Arrays.asList(args);
        Log.info("args=" + argsList);
        Log.info("signature=" + signature);

        Object result = point.proceed(args);
        Object target = point.getTarget();
        Log.info("object whose method in execution =" + target);
        Log.info("result=" + result);
        return result;
    }


    @AfterThrowing(value ="execution(*  Calculator.*(..))", throwing = "ex")
    public void advice4(JoinPoint point, Exception ex){
        Log.info("*** inside advice1");
        Signature signature = point.getSignature();
        Object[] args = point.getArgs();
        List argsList = Arrays.asList(args);
        Log.info("args=" + argsList);
        Log.info("signature=" + signature);
        Log.info("*****"+ex.getMessage());
    }


}
