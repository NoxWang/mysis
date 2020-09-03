package com.mysis.common.base.exception;

public class BaseException extends RuntimeException {
    private static final long serialVersionUID = -3907250379571086438L;

    /**
     * 所属模块
     */
    private String module;

    /**
     * 错误码
     */
    private String code;

    /**
     * 错误码对应的参数
     */
    private Object[] args;

    /**
     * 错误消息
     */
    private String errorMessage;

    public BaseException(String module, String code, Object[] args, String errorMessage)
    {
        this.module = module;
        this.code = code;
        this.args = args;
        this.errorMessage = errorMessage;
    }
    
    public BaseException(String errorMessage) {
        this(null, null, null, errorMessage);
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public String geterrorMessage() {
        return errorMessage;
    }

    public void seterrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
