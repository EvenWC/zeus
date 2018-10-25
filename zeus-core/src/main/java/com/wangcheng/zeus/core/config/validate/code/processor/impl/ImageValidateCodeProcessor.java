package com.wangcheng.zeus.core.config.validate.code.processor.impl;

import com.wangcheng.zeus.core.config.validate.code.ImageCode;
import com.wangcheng.zeus.core.config.validate.code.ValidateCode;
import com.wangcheng.zeus.core.config.validate.code.processor.AbstractValidateCodeProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * @author : Administrator
 * @Date: 2018/9/27 21:22
 * @Description:
 */
@Component("imageCodeProcessor")
public class ImageValidateCodeProcessor extends AbstractValidateCodeProcessor {
    @Override
    public void sendValidateCode(ValidateCode validateCode, ServletWebRequest servletWebRequest) throws IOException {
        ImageCode imageCode = (ImageCode)validateCode;
        ImageIO.write(imageCode.getBufferedImage(),"jpeg",servletWebRequest.getResponse().getOutputStream());
    }

    @Override
    public String getValidateCodeType() {
        return "image";
    }

}
