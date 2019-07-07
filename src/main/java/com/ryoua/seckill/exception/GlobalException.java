package com.ryoua.seckill.exception;

import com.ryoua.seckill.result.CodeMsg;

/**
 * 全局异常处理类
 *
 * @Author ryoua Created on 2019-07-07
 */
public class GlobalException extends RuntimeException {
    private static final long servialVersionUID = 1L;

    private CodeMsg codeMsg;

    public GlobalException(CodeMsg codeMsg) {
        super(codeMsg.toString());
        this.codeMsg = codeMsg;
    }

    public CodeMsg getCodeMsg() {
        return codeMsg;
    }
}
