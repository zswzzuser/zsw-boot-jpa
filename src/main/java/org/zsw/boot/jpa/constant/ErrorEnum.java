package org.zsw.boot.jpa.constant;

public enum ErrorEnum implements Enum {
    USER_AUTH_FAILED(401001, "用户授权失败"),
    SIGN_VERIFI_ERROR(401002,"token解析错误"),
    SIGN_VERIFI_EXPIRE(401003,"token已过期")
    ;


    private Integer code;
    private String message;

    ErrorEnum(Integer code,String message){
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }
    @Override
    public String getMessage() {
        return message;
    }
}
