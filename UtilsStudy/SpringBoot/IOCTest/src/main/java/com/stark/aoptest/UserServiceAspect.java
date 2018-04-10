package com.stark.aoptest;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

/**
 * Created by Stark on 2018/1/16.
 */
@Aspect
@Service
public class UserServiceAspect {

    @Pointcut("execution(* com.stark.aoptest.UserService.*(..))&&args(id)")
    public void pointCut(String id) {

    }

    @Before("pointCut(id)")
    public void beforeHandle(String id) {
        System.out.println("before handle :" + id);
    }


}
