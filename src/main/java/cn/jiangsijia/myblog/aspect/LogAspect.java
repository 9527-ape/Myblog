package cn.jiangsijia.myblog.aspect;

import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author 蒋思佳
 * @date 2020/5/19 0019.
 * 日志处理
 */

@Aspect//切面
@Component//开启组件扫描
public class LogAspect {

    //获得日志记录器
    private  final Logger logger = LoggerFactory.getLogger(this.getClass());

    //定义切面,拦截所有cn.jiangsijia.myblog.controller.下的类
    @Pointcut("execution(* cn.jiangsijia.myblog.controller.*.*(..))")
    public void log(){}

    //切面前(请求前).1
    @Before("log()")
    public  void doBefore(){
            logger.info("==========请求前==========");
    }

    //切面后(请求后).2
    @After("log()")
    public  void doAftre(){
        logger.info("==========请求后==========");
    }

    //(请求执行完成返回过程中).3
    //获取返回内容result
    @AfterReturning(returning = "result",pointcut = "log()")
    public  void doAftreRuturn(Object result){
        logger.info("Result : {}",result);
    }
}
