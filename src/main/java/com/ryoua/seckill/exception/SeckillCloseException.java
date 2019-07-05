package com.ryoua.seckill.exception;

/**
 * @Author ryoua Created on 2019-06-29
 */
public class SeckillCloseException extends SeckillException {

    public SeckillCloseException(String message) {
        super(message);
    }

    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }

}
