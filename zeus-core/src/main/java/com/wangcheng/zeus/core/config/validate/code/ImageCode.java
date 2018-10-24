package com.wangcheng.zeus.core.config.validate.code;

import sun.security.pkcs11.P11Util;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;
/**
 * @author : Administrator
 * @Date: 2018/9/20 21:11
 * @Description: 图片验证码
 */
public class ImageCode extends ValidateCode{

    /**验证图片*/
    private BufferedImage bufferedImage;

    public ImageCode(String code, Long expireIn, BufferedImage bufferedImage) {
        super(code, expireIn);
        this.bufferedImage = bufferedImage;
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public void setBufferedImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }

}
