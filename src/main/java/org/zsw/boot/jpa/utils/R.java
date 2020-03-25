package org.zsw.boot.jpa.utils;

import lombok.Data;
import lombok.experimental.Accessors;
import org.zsw.boot.jpa.constant.Enum;

import java.io.Serializable;

@Accessors(chain = true)
@Data
public class R<T> implements Serializable {

    private Integer code;
    private String message;
    private T data;

    private R(Integer code,String message,T data){
        this.code = code;
        this.message = message;
        this.data = data;
    }
    private R(T data){
        this.code = 200;
        this.message = "请求成功";
        this.data = data;
    }

    private R(){
        this.code = 500;
        this.message = "未知异常,请联系管理员";
    }

    private R(Integer code,String message){
        this.code = code;
        this.message = message;
    }
    public static <T> R<T> ok(T data){
        return new R(data);
    }

    public static <T> R<T> error(Integer code,String message){
        return new R<>(code,message);
    }

    public static <T> R<T> error(Enum errorEnum){
        return new R<>(errorEnum.getCode(),errorEnum.getMessage());
    }

}
