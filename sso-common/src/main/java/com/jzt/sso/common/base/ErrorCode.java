package com.jzt.sso.common.base;

public enum ErrorCode {

    /**
     * 令牌无效
     */
    USER_ACCESS_TOKEN_ERROR_NOTEXISTS(101, "令牌无效"), /**
     * 令牌已过期
     */
    USER_ACCESS_TOKEN_ERROR_OVERDUE(102, "令牌已过期"), /**
     * 令牌异常，未知错误
     */
    USER_ACCESS_TOKEN_ERROR_EXCEPTION(103, "令牌异常，未知错误"), /**
     * refreshToken 不能为空
     */
    USER_ACCESS_TOKEN_ERROR_REFRESH_IS_NULL(104, "refreshToken 不能为空"), /**
     * refreshToken 错误或不存在
     */
    USER_ACCESS_TOKEN_ERROR_REFRESH_ERROR(105, "refreshToken 错误或不存在"),

    /**
     * 主键ID为空或格式错误
     */
    PRIMARY_KEY_IS_NULL_OR_DATA_ERROR(210, "主键ID为空或格式错误"),

    /**
     * 文件上传所属类型为空
     */
    UPLOAD_TYPE_IS_NULL(300, "文件上传所属类型为空"), /**
     * 文件上传所属未知
     */
    UPLOAD_UNKNOW_FILE_TYPE(301, "文件上传所属未知"), /**
     * 上传文件为空
     */
    UPLOAD_EMPTY_FILE(302, "上传文件为空"),

    /**
     * 出生日期格式错误
     */
    BIRTHDAY_DATA_ERROR(401, "出生日期格式错误"), /**
     * 请求数据格式异常
     */
    PARAMS_ERROR(402, "请求数据格式异常"),

    /**
     * 未登录
     */
    UNDOWN_LOGIN(902, "未登录"), /**
     * 用户名不能为空
     */
    LOGIN_NAME_NULL_OR_EMPTY(903, "用户名不能为空"), /**
     * 用户密码不能为空
     */
    LOGIN_PASSWORD_NULL_OR_EMPTY(904, "用户密码不能为空"), /**
     * 外部系统 ID或密码错误
     */
    PASSWORD_ERROR(905, "用户名或密码错误"), /**
     * 登录失败
     */
    LOGIN_ACCOUNT_FAIL(906, "登录失败"), /**
     * 登录失败，系统异常
     */
    LOGIN_ACCOUNT_FAIL_EXCEPTION(907, "登录失败，系统异常"),

    /**
     * 未知错误
     */
    UNKNOWN(9999, "未知错误"), /**
     * 成功
     */
    SUCCESS(200, "操作成功");

    private int code;
    private String description;

    ErrorCode(String description) {
        this.description = description;
    }

    ErrorCode(int code, String description) {
        this.code = code;
        this.description = description;
    }

    @Override public String toString() {
        return this.description;
    }

    /**
     * @return the code
     */
    public int getCode() {
        return this.code;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * 自定义错误提示, 错误代码为9999
     *
     * @param desc
     * @return
     */
    public static ErrorCode customDesc(String desc) {
        UNKNOWN.description = desc;
        return UNKNOWN;
    }

    public static ErrorCode valueOf(int code) {
        for (ErrorCode errorCode : values()) {
            if (errorCode.code == code) {
                return errorCode;
            }
        }
        return null;
    }
}
