package com.ryoua.seckill.exception;

/**
 * @Author ryoua Created on 2019-06-29
 */
public class SeckillException extends RuntimeException {

    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }

}