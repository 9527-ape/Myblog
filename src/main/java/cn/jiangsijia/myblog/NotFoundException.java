package cn.jiangsijia.myblog;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author 蒋思佳
 * @date 2020/5/19 0019.
 * 异常类NOT_FOUND
 */
@ResponseStatus(HttpStatus.NOT_FOUND)//添加状态码
public class NotFoundException extends RuntimeException {
    public NotFoundException() { }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundException(Throwable cause) {
        super(cause);
    }

    public NotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
