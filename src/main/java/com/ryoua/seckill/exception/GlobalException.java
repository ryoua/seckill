package com.ryoua.seckill.exception;

import com.ryoua.seckill.result.CodeMsg;

/**
 * @Author: ryoua
 * @Create: 2019-07-27 16:19
 */
public class GlobalException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    private CodeMsg cm;

    public GlobalException(CodeMsg cm) {
        super(cm.toString());
        this.cm = cm;
    }

    public CodeMsg getCm() {
        return cm;
    }
}
