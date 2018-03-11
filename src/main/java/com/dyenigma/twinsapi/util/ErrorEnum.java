package com.dyenigma.twinsapi.util;

/**
 * twins/com.dyenigma.twinsapi.util
 *
 * @Description :
 * @Author : dingdongliang
 * @Date : 2018/3/11 19:22
 */
public enum ErrorEnum {
    ERR_404("404", "找不到页面"), ERR_1001("1001", "缺少必填参数");

    private String errorCode;

    private String errorMsg;

    ErrorEnum() {
    }

    ErrorEnum(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
