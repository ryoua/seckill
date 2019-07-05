package com.ryoua.seckill.exception;

/**
 * @Author ryoua Created on 2019-06-29
 */
public class RepeatKillException extends SeckillException {
    public RepeatKillException(String message) {
        super(message);
    }

    public RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }

}
