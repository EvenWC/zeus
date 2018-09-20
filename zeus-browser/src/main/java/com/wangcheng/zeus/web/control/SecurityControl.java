package com.wangcheng.zeus.web.control;

import com.wangcheng.zeus.common.response.ResponseModel;
import com.wangcheng.zeus.core.config.properties.ZeusProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther: Administrator
 * @Date: 2018/9/18 21:42
 * @Description:
 */
@RestController
public class SecurityControl {

    private RequestCache requestCache = new HttpSessionRequestCache();

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private ZeusProperties zeusProperties;

    @RequestMapping("/authentication/strategy")
    public ResponseModel signIn(HttpServletRequest request, HttpServletResponse response) throws IOException {

        SavedRequest savedRequest = requestCache.getRequest(request, response);
        String redirectUrl = savedRequest.getRedirectUrl();

        if(redirectUrl != null){
            if(StringUtils.endsWithIgnoreCase(redirectUrl,".html")){
                redirectStrategy.sendRedirect(request,response, zeusProperties.getBrowser().getLoginPage());
            }
        }
        return ResponseModel.FAIL(401,"未登陆");
    }
}
