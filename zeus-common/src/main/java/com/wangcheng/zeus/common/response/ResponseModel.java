package com.wangcheng.zeus.common.response;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * @author : evan
 * @Date: 2018/8/28 20:49
 * @Description: 提供给control 统一返回的模型对象
 */
public class ResponseModel<T> {

    public interface SimpleInfo{}

    public interface SimpleDetailInfo extends SimpleInfo{}

    private ModelStatus status = ModelStatus.OK;

    private Integer code;

    private String message;

    private T data;

    private LocalDateTime timestamp = LocalDateTime.now();

    private ResponseModel(){}
    private ResponseModel(Integer code,String message){
        this.code = code;
        this.message = message;
    }
    private static <T> ResponseModel<T>  init(){
        return new ResponseModel<>();
    }
    private static <T> ResponseModel<T>  init(Integer code,String message){
        return new ResponseModel<>(code,message);
    }
    public static ResponseModel SUCCESS(){
        return ResponseModel.init().setStatus(ModelStatus.NO_CONTENT);
    }
    public static <T>ResponseModel<T> SUCCESS(T t){
        return ResponseModel.<T>init().setData(t);
    }
    public static ResponseModel NOT_MODIFIED(){
        return ResponseModel.init().setStatus(ModelStatus.NOT_MODIFIED);
    }
    public static ResponseModel ERROR(Integer code,String message){
        //错误码必须在500段
        Assert.isTrue(code >= 500 && code < 600,"错误码必须在500段");
        return ResponseModel.init(code,message).setStatus(ModelStatus.ERROR);
    }
    public static ResponseModel ERROR(String message){
        return ResponseModel.init().setMessage(message).setStatus(ModelStatus.ERROR);
    }
    public static  ResponseModel ERROR(){
        return ResponseModel.init().setStatus(ModelStatus.ERROR);
    }
    public static ResponseModel UNAUTHORIZED(){
        return ResponseModel.init().setStatus(ModelStatus.UNAUTHORIZED);
    }
    public static ResponseModel FORBIDDEN(){
        return ResponseModel.init().setStatus(ModelStatus.FORBIDDEN);
    }
    public static ResponseModel BAD_REQUEST(){
        return ResponseModel.init().setStatus(ModelStatus.BAD_REQUEST);
    }
    public static ResponseModel NOT_FOUND(){
        return ResponseModel.init().setStatus(ModelStatus.NOT_FOUND);
    }
    @JsonView(value = SimpleInfo.class)
    public String getMessage() {
        if(StringUtils.isEmpty(message)){
            return status.getMessage();
        }
        return message;
    }

    public ResponseModel<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    @JsonView(value = SimpleInfo.class)
    public int getCode() {
        if(Objects.isNull(code)){
            return status.getCode();
        }
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }

    @JsonView(value = SimpleInfo.class)
    public T getData() {
        return data;
    }

    private ResponseModel<T> setData(T data) {
        this.data = data;
        return this;
    }
    @JsonView(value = SimpleInfo.class)
    public String getTimestamp() {
        return timestamp.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
    private ResponseModel<T> setStatus(ModelStatus status) {
        this.status = status;
        return this;
    }

    @Override
    public String toString() {
        return "ResponseModel{" +
                "message='" + message + '\'' +
                ", code=" + code +
                ", data=" + data +
                '}';
    }

    enum  ModelStatus{
        /**
         * 表示从客户端发来的请求在服务器端被正确处理
         */
        OK(200,"success"),
        /**
         *  服务器发生预料之外的错误
         */
        ERROR(500,"internal.sever.error"),
        /**
         * 请求成功，但响应报文不含实体的主体部分
         */
        NO_CONTENT(204,"no content"),
        /**
         * 表示服务器允许访问资源，但因发生请求未满足条件的情况
         */
        NOT_MODIFIED(304,"not modified"),
        /**
         * 语义有误，当前请求无法被服务器理解。
         */
        BAD_REQUEST(400,"bad request"),
        /**
         * 未登陆
         */
        UNAUTHORIZED(401,"unauthorized"),
        /**
         * 访问被拒绝
         */
        FORBIDDEN(403,"forbidden"),
        /**
         * 404
         */
        NOT_FOUND(404,"not found");
        private int code;
        private String message;
        ModelStatus(int code,String message){
            this.code = code;
            this.message = message;
        }
        public int getCode() {
            return code;
        }
        public String getMessage() {
            return message;
        }
    }
}
