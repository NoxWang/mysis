package com.mysis.common.base.vo;

import com.mysis.common.contants.HttpStatus;

import java.util.HashMap;

public class ResultVO extends HashMap<String, Object> {

    private static final long serialVersionUID = -7733536888326402017L;

    /** 状态码 */
    public static final String KEY_CODE = "code";

    /** 返回内容 */
    public static final String KEY_MSG = "msg";

    /** 数据对象 */
    public static final String KEY_DATA = "data";

    public ResultVO() {
    }

    public ResultVO(int code, String msg) {
        super.put(KEY_CODE, code);
        super.put(KEY_MSG, code);
    }

    public ResultVO(int code, String msg, Object data) {
        super.put(KEY_CODE, code);
        super.put(KEY_MSG, code);
        super.put(KEY_DATA, data);
    }

    public static ResultVO success() {
        return ResultVO.success("操作成功");
    }

    public static ResultVO success(Object data) {
        return ResultVO.success("操作成功", data);
    }

    public static ResultVO success(String msg) {
        return ResultVO.success(msg, null);
    }

    public static ResultVO success(String msg, Object data) {
        return new ResultVO(HttpStatus.SUCCESS, msg, data);
    }

    public static ResultVO error() {
        return ResultVO.error("操作失败");
    }

    public static ResultVO error(String msg) {
        return ResultVO.error(msg, null);
    }

    public static ResultVO error(String msg, Object data) {
        return new ResultVO(HttpStatus.ERROR, msg, data);
    }

    public static ResultVO error(int code, String msg) {
        return new ResultVO(code, msg, null);
    }
}
