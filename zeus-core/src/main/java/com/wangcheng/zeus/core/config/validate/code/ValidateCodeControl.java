package com.wangcheng.zeus.core.config.validate.code;

import com.wangcheng.zeus.core.config.validate.code.processor.ValidateCodeProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author Administrator
 * @Date: 2018/9/20 21:17
 * @Description:
 */
@RestController
public class ValidateCodeControl {

    @Autowired
    private Map<String,ValidateCodeProcessor> validateCodeProcessors;


    @GetMapping("code/{type}")
    public void createImage(@PathVariable("type")String type , HttpServletRequest request, HttpServletResponse response) throws IOException {
        validateCodeProcessors.get(type+"CodeProcessor").createValidateCode(new ServletWebRequest(request,response));
    }
}
