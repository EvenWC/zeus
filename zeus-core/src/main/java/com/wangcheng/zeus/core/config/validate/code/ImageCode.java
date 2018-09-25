package com.wangcheng.zeus.core.config.validate.code;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;
/**
 * @author : Administrator
 * @Date: 2018/9/20 21:11
 * @Description: 图片验证码
 */
public class ImageCode {

    /**验证图片*/
    private BufferedImage bufferedImage;
    /**验证码*/
    private String code;
    /**过期时间*/
    private LocalDateTime expireTime;
    /**需要验证码的url*/
    private String validateUrl;

    public ImageCode(BufferedImage bufferedImage, String code, Long expireIn) {
        this.bufferedImage = bufferedImage;
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public ImageCode(BufferedImage bufferedImage, String code, LocalDateTime expireTime) {
        this.bufferedImage = bufferedImage;
        this.code = code;
        this.expireTime = expireTime;
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public void setBufferedImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }

    public String getValidateUrl() {
        return validateUrl;
    }

    public void setValidateUrl(String validateUrl) {
        this.validateUrl = validateUrl;
    }

    public boolean isExpire() {
      return   LocalDateTime.now().isAfter(expireTime);
    }
}
