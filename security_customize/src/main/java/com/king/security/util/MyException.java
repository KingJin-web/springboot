package com.king.security.util;

/**
 * @program: mooc
 * @description: 自定义业务异常
 * @author: King
 * @create: 2021-10-02 20:00
 */
public class MyException extends Exception {


    private static final long serialVersionUID = 1L;

    public MyException() {
        super();
    }

    public MyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public MyException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyException(String message) {
        super(message);
    }

    public MyException(Throwable cause) {
        super(cause);
    }

}
