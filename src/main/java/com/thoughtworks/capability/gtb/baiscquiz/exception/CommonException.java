package com.thoughtworks.capability.gtb.baiscquiz.exception;

// GTB: - 通常自定义异常体系会从 RuntimeException 开始继承
public class CommonException extends Exception {
    private String message;
    public CommonException(){};
    public CommonException(String message){
        super(message);
        this.message = message;
    }
    public String getError() {
        return message;
    }
}
