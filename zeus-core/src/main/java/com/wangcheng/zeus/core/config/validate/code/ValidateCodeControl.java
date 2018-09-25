package com.wangcheng.zeus.core.config.validate.code;

import com.wangcheng.zeus.core.config.validate.code.manager.ValidateCodeManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Administrator
 * @Date: 2018/9/20 21:17
 * @Description:
 */
@RestController
public class ValidateCodeControl {

    public static final String SESSION_KEY = "SESSION_KEY_IMAGE_KEY";

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Autowired
    private ValidateCodeManager codeManager;

    @GetMapping("code/image")
    public void createImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ImageCode imageCode = codeManager.generateValidateCode();
        sessionStrategy.setAttribute(new ServletWebRequest(request),SESSION_KEY,imageCode);
        ImageIO.write(imageCode.getBufferedImage(),"jpeg",response.getOutputStream());
    }
}
