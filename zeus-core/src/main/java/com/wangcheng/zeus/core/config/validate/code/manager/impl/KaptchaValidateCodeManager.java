package com.wangcheng.zeus.core.config.validate.code.manager.impl;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import com.wangcheng.zeus.core.config.properties.ImageCodeProperties;
import com.wangcheng.zeus.core.config.properties.ZeusProperties;
import com.wangcheng.zeus.core.config.validate.code.ImageCode;
import com.wangcheng.zeus.core.config.validate.code.ValidateCode;
import com.wangcheng.zeus.core.config.validate.code.manager.ValidateCodeManager;

import java.awt.image.BufferedImage;
import java.util.Properties;

/**
 * @author : Administrator
 * @Date: 2018/9/21 20:23
 * @Description: 基于kaptcha框架
 */
public class KaptchaValidateCodeManager implements ValidateCodeManager {

    private ZeusProperties zeusProperties;

    private DefaultKaptcha kaptcha;

    public KaptchaValidateCodeManager(ZeusProperties zeusProperties, DefaultKaptcha kaptcha) {
        this.zeusProperties = zeusProperties;
        this.kaptcha = kaptcha;
    }

    @Override
    public ValidateCode generateValidateCode() {
        ImageCodeProperties imageCodeProperties = zeusProperties.getValidateCode().getImageCode();
        this.setKaptchaConfig(imageCodeProperties);
        String code = kaptcha.createText();
        BufferedImage image = kaptcha.createImage(code);
        return new ImageCode(code,imageCodeProperties.getExpireIn(),image);
    }

    private void setKaptchaConfig(ImageCodeProperties imageCodeProperties){
        Properties properties =new Properties();
        properties.setProperty("kaptcha.border",imageCodeProperties.getBorder()?"yes":"no");
        properties.setProperty("kaptcha.border.color",imageCodeProperties.getBorderColor());
        properties.setProperty("kaptcha.textproducer.font.color",imageCodeProperties.getFontColor());
        properties.setProperty("kaptcha.image.width",imageCodeProperties.getImageWidth());
        properties.setProperty("kaptcha.image.height",imageCodeProperties.getImageHeight());
        properties.setProperty("kaptcha.textproducer.font.size",imageCodeProperties.getFontSize());
        properties.setProperty("kaptcha.session.key",imageCodeProperties.getSessionKey());
        properties.setProperty("kaptcha.textproducer.char.length",imageCodeProperties.getCharLength());
        properties.setProperty("kaptcha.textproducer.font.names",imageCodeProperties.getFontsName());
        Config config=new Config(properties);
        kaptcha.setConfig(config);
    }
    @Override
    public Boolean checkValidateCode(ValidateCode imageCode, String requestValidateCode) {
        //todo: 验证验证码逻辑
        
        return null;
    }
    public ZeusProperties getZeusProperties() {
        return zeusProperties;
    }

    public void setZeusProperties(ZeusProperties zeusProperties) {
        this.zeusProperties = zeusProperties;
    }
}
