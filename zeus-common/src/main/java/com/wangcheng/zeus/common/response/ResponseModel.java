package com.wangcheng.zeus.common.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author : evan
 * @Date: 2018/8/28 20:49
 * @Description: 提供给control 统一返回的模型对象
 */
public class ResponseModel<T> implements Serializable {

    private static final long serialVersionUID                = 1L;
    private static final int  STATUS_SUCCESS                  = 200;
    private static final int  STATUS_ERROR                    = 500;
    private static final int  STATUS_NOT_MODIFY               = 304;
    private static final int  STATUS_ACCESS_FORBIDDEN         = 403;
    private static final int  STATUS_NOT_FOUND                = 404;

    public interface SimpleInfo{}

    public interface SimpleDetailInfo extends SimpleInfo{}


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp                          = LocalDateTime.now();
    private int status;
    private String message;
    private T data;

    private ResponseModel(){
    }
    private ResponseModel(int status,String message,T data){
        this.status = status;
        this.message = message;
        this.data = data;
    }
    public static <T> ResponseModel<T> SUCCESS(){
        return SUCCESS(null);
    }
    public static <T>ResponseModel<T> SUCCESS(T t){
        return SUCCESS("message.success.default",t);
    }
    public static <T>ResponseModel<T> SUCCESS(String message,T t){
        return new ResponseModel<>(STATUS_SUCCESS,message,t);
    }

    public static <T>ResponseModel<T> ERROR(int status,String message){
        return new ResponseModel<>(status,message,null);
    }
    public static <T>ResponseModel<T> ERROR(String message){
        return ERROR(STATUS_ERROR,message);
    }
    public static  <T>ResponseModel<T> ERROR(){
        return ERROR(STATUS_ERROR,"error.internal.sever.error");
    }
    public static <T>ResponseModel<T> NOT_MODIFY(){
        return NOT_MODIFY("message.object.not.modify");
    }
    public static <T>ResponseModel<T> NOT_MODIFY(String message){
        return NOT_MODIFY(message,null);
    }
    public static <T>ResponseModel<T> NOT_MODIFY(String message,T data){
        return new ResponseModel<>(STATUS_NOT_MODIFY,message,data);
    }

    public static <T>ResponseModel<T> NOT_FOUND(String message){
        return ERROR(STATUS_NOT_FOUND,message);
    }
    public static <T>ResponseModel<T> NOT_FOUND(){
        return NOT_FOUND("error.object.not.found");
    }
    public static <T>ResponseModel<T> ACCESS_FORBIDDEN(){
        return ACCESS_FORBIDDEN("error.access.forbidden");
    }
    public static <T>ResponseModel<T> ACCESS_FORBIDDEN(String message){
        return ERROR(STATUS_ACCESS_FORBIDDEN ,message);
    }
    @JsonView(value = SimpleInfo.class)
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
    @JsonView(value = SimpleInfo.class)
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    @JsonView(value = SimpleInfo.class)
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    @JsonView(value = SimpleInfo.class)
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
    @Override
    public String toString() {
        return "ResponseModel{" +
                "message='" + message + '\'' +
                ", status=" + status +
                ", data=" + data +
                ", timestamp=" + timestamp +
                '}';
    }
}
