package com.mysis.framework.exception;

import com.mysis.common.base.exception.BaseException;

/**
 * 用户信息异常类
 */
public class UserException extends BaseException {

    private static final long serialVersionUID = 1349619216311314634L;

    public UserException(String code) {
        super("user", code);
    }
}
