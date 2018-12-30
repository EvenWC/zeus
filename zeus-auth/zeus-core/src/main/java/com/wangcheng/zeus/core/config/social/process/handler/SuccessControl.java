package com.wangcheng.zeus.core.config.social.process.handler;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class SuccessControl {


    @RequestMapping("success")
    public String success(HttpServletRequest request, HttpServletResponse response, Model model){
        Object auth = request.getAttribute("auth");
        System.out.println(auth);
        model.addAttribute("auth",auth);
        return "success";
    }
}
