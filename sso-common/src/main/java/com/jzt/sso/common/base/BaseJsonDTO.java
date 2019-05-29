package com.jzt.sso.common.base;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "BaseJsonDTO")
public class BaseJsonDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 6169666971803986733L;
    private boolean success = false;
    private String errorCode;
    private String msg;
    private int totalCount;
    private String returnId;
    private String otherProperty;

    public BaseJsonDTO() {
        this.success = false;
    }

    public void setError(ErrorCode code) {
        this.errorCode = "" + code.getCode();
        this.msg = code.getDescription();
    }

    public BaseJsonDTO(String msg, boolean isSuccess) {
        this.msg = msg;
        this.success = isSuccess;
    }

    public static BaseJsonDTO ok(String msg) {
        return new BaseJsonDTO(msg, true);
    }

    public static BaseJsonDTO fail(String msg) {
        return new BaseJsonDTO(msg, false);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public String getReturnId() {
        return returnId;
    }

    public void setReturnId(String returnId) {
        this.returnId = returnId;
    }

    public String getOtherProperty() {
        return otherProperty;
    }

    public void setOtherProperty(String otherProperty) {
        this.otherProperty = otherProperty;
    }

}
