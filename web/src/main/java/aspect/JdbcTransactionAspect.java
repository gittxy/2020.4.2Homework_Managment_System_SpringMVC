package aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import utils.TransactionUtil;

@Aspect
public class ProgramTransactionAspect {
    @Autowired(required = false)
    private TransactionUtil transactionUtil;

    @Pointcut("execution(* com.roger.biz.service.impl..*.*(..))")
    public void addTransaction(){
           
    }

    //异常通知：给添加事务的方法回滚事务，当方法抛出异常时
    @AfterThrowing("addTransaction()")
    public void rollbackTransaction(){
        //获取当前事务，然后回滚
        transactionUtil.rollback();
    }

    //环绕通知：给需要添加事务的方法，手动开启事务和提交事务
    @Around("addTransaction()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable{
        transactionUtil.begin();
        joinPoint.proceed();
        transactionUtil.commit();
    }
}
