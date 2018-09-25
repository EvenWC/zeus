package com.wangcheng.zeus.common.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.wangcheng.zeus.common.callback.ResponseCallBack;
import com.wangcheng.zeus.common.constant.ResponseConstant;
import com.wangcheng.zeus.common.utils.CommonUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

/**
 * @Auther: Administrator
 * @Date: 2018/8/28 20:49
 * @Description: 提供给control 统一返回的模型对象
 */
public class ResponseModel<T> {

    public interface SimpleInfo{}

    public interface SimpleDetailInfo extends SimpleInfo{}

    private String message = ResponseConstant.SUCCESS_MESSAGE;

    private int code = ResponseConstant.SUCCESS_CODE;

    private T data;

    private LocalDateTime timestamp = LocalDateTime.now();

    private BindingResult bindingResult;

    private ResponseModel(){}

    public static <T> ResponseModel<T>  init(){return new ResponseModel();}

    public static <T> ResponseModel<T> init(BindingResult bindingResult){
      return  ResponseModel.<T>init().setBindingResult(bindingResult);
    }

    public  ResponseModel setBindingResult(BindingResult bindingResult) {
        this.bindingResult = bindingResult;
        return this;
    }

    /**
     * 处理业务
     * @param callBack 处理业务方法的回调
     * @return 处理的结果
     */
    public ResponseModel<T> handle(ResponseCallBack<T> callBack){
        this.doValidate();
        if(this.code == ResponseConstant.VALIDATE_ERROR_CODE){
           return callBack.fail(this);
        }else {
           return callBack.success(this);
        }
    }

    /**
     * 提供提供设置message和data的SUCCESS
     * @param message
     * @param <T>
     * @return
     */
    public static <T>ResponseModel<T> SUCCESS(String message,T t){
        return ResponseModel.<T>init().setMessage(message).setData(t);
    }

    /**
     * 直接提供设置data的SUCCESS
     * @param t
     * @param <T>
     * @return
     */
    public static <T>ResponseModel<T> SUCCESS(T t){
        return ResponseModel.<T>init().setData(t);
    }

    /**
     * 提供设置code和编码错误的方法
     * @param code
     * @param message
     * @param <T>
     * @return
     */
    public static <T>ResponseModel<T> FAIL(int code,String message){
        return ResponseModel.<T>init().setMessage(CommonUtils.format(ResponseConstant.FAIL_MESSAGE,message)).setCode(code);
    }

    /**
     * 直接提供设置失败信息的方法
     * @param message
     * @param <T>
     * @return
     */
    public static <T>ResponseModel<T> FAIL(String message){
        return ResponseModel.<T>init().setMessage(CommonUtils.format(ResponseConstant.FAIL_MESSAGE,message)).setCode(ResponseConstant.FAIL_CODE);
    }

    /**
     * 使用默认的操作失败的方法
     * @param <T>
     * @return
     */
    public static  <T>ResponseModel<T> FAIL(){
        return ResponseModel.FAIL("操作失败");
    }


    /**
     * 和init(BindingResult bindingResult)方法绑定
     * 如果传入了bindingResult ,那么对请求参数进行验证
     */
    private void doValidate(){
        if( this.bindingResult != null && this.bindingResult.getErrorCount() > 0){
            this.setCode(ResponseConstant.VALIDATE_ERROR_CODE);
            StringBuffer buffer = new StringBuffer("[");
            List<FieldError> fieldErrors = this.bindingResult.getFieldErrors();
            if(!CollectionUtils.isEmpty(fieldErrors)){
                fieldErrors.forEach(
                    (error)->{
                        String field = error.getField();
                        Object message = error.getDefaultMessage();
                        buffer.append("{");
                        buffer.append("\"key\":\""+ field +"\"");
                        buffer.append("\"value\":\""+ message +"\"");
                        buffer.append("},");
                    }
                );
            }
            List<ObjectError> globalErrors = this.bindingResult.getGlobalErrors();
            if(!CollectionUtils.isEmpty(globalErrors)){
                globalErrors.forEach((error->{
                    buffer.append("{");
                    buffer.append("\"key\":\"提示\"");
                    buffer.append("\"value\":\""+error.getDefaultMessage()+"\"");
                    buffer.append("},");
                }));
            }
            String substring = buffer.substring(0, buffer.length() - 1) + "]";
            this.setMessage(CommonUtils.format(ResponseConstant.VALIDATE_ERROR_MESSAGE,substring));
        }
    }
    @JsonView(value = SimpleInfo.class)
    public String getMessage() {
        return message;
    }

    public ResponseModel<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    @JsonView(value = SimpleInfo.class)
    public int getCode() {
        return code;
    }

    public ResponseModel<T> setCode(int code) {
        this.code = code;
        return this;
    }
    @JsonView(value = SimpleInfo.class)
    public T getData() {
        return data;
    }

    public ResponseModel<T> setData(T data) {
        this.data = data;
        return this;
    }
    @JsonView(value = SimpleInfo.class)
    public String getTimestamp() {
        return timestamp.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
    @Override
    public String toString() {
        return "ResponseModel{" +
                "message='" + message + '\'' +
                ", code=" + code +
                ", data=" + data +
                '}';
    }
}
