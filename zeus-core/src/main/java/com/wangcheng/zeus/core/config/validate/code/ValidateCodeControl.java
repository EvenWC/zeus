package com.wangcheng.zeus.core.config.validate.code;

import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @Auther: Administrator
 * @Date: 2018/9/20 21:17
 * @Description:
 */
@RestController
public class ValidateCodeControl {

    public static final String SESSION_KEY = "SESSION_KEY_IMAGE_KEY";

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Autowired
    private Producer producer;

    @GetMapping("code/image")
    public void createImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ImageCode imageCode = createImageCode();
        sessionStrategy.setAttribute(new ServletWebRequest(request),SESSION_KEY,imageCode);
        ImageIO.write(imageCode.getBufferedImage(),"jpeg",response.getOutputStream());
    }

    private ImageCode createImageCode() {
        String text = producer.createText();
        BufferedImage image = producer.createImage(text);
        ImageCode imageCode = new ImageCode(image, text, 30L);
        return imageCode;
    }


}
