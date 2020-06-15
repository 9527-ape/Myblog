package cn.jiangsijia.myblog.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 蒋思佳
 * @date 2020/5/19 0019.
 *处理异常
 */
@ControllerAdvice
public class ExceptionHandler {

    //获得日志记录器
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    //拦截所有异常信息
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public Object exceptionHandler(HttpServletRequest request, Exception e) throws Exception {
        //将异常记录到日志
        logger.error("Requst URL : {}, Exception : {}", request.getRequestURL() , e);

        //判断异常是否有指定，是500或者4XX,如果不为空就是存在，到NotFoundException类
        if(AnnotationUtils.findAnnotation(e.getClass(),ResponseStatus.class)!=null){
                throw e;
        }

        //add错误页面路径和异常到前端
        ModelAndView mv = new ModelAndView();
        mv.addObject("url",request.getRequestURL());
        mv.addObject("exception",e);
        //返回到错误页面
        mv.setViewName("error/error");
        return mv;
    }
}
