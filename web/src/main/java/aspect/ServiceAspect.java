package aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author: txy
 * @Date: 2020/4/20 22:30
 */
@Aspect
@Component
public class ServiceAspect {

    @Pointcut("execution(* service.*.*(..))")
    public void service(){}


    @Before("service()")
    public void beforeService(JoinPoint joinPoint){
        System.out.println("-------------before-------------");
        System.out.print(new Date()+":");
        System.out.println("执行的方法：" + joinPoint.getSignature());
    }


    @After("service()")
    public void afterService(){
        System.out.print(new Date()+":");
        System.out.println("方法执行完毕！");
        System.out.println("-------------after-------------");
    }

    @Around("service()")
    public void aroundService(ProceedingJoinPoint joinPoint){

    }


}
