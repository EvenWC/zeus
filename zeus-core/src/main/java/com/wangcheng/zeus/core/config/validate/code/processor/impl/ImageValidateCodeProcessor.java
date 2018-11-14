package com.wangcheng.zeus.core.config.validate.code.processor.impl;

import com.google.common.collect.Maps;
import com.wangcheng.zeus.common.response.ResponseModel;
import com.wangcheng.zeus.core.config.utils.HttpResponseUtils;
import com.wangcheng.zeus.core.config.validate.code.ImageCode;
import com.wangcheng.zeus.core.config.validate.code.ValidateCode;
import com.wangcheng.zeus.core.config.validate.code.processor.AbstractValidateCodeProcessor;
import org.bouncycastle.util.encoders.Base64Encoder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;

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
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(imageCode.getBufferedImage(),"jpeg",outputStream);
        byte[] bytes = outputStream.toByteArray();
        BASE64Encoder encoder = new BASE64Encoder();
        String base64String = encoder.encodeBuffer(bytes).trim();
        base64String = base64String.replaceAll("\r", "").replaceAll("\n", "");
        HashMap<String, String> map = Maps.newHashMap();
        map.put("valiateToken",validateCode.getToken());
        map.put("image","data:image/jpg;base64," + base64String);
        HttpResponseUtils.printJson(servletWebRequest.getResponse(),ResponseModel.SUCCESS(map));
    }
    @Override
    public String getValidateCodeType() {
        return "image";
    }

}
