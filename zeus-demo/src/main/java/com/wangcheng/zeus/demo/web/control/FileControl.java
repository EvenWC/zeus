package com.wangcheng.zeus.demo.web.control;

import com.google.common.base.Joiner;
import com.google.common.io.Files;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.List;

/**
 * @Auther: Administrator
 * @Date: 2018/9/16 10:05
 * @Description: 处理文件上传下载
 */
//@RestController
//@RequestMapping(value = "file")
public class FileControl {

    private String path = "H://test";

    @PostMapping
    public String upload(MultipartFile file) throws IOException {
        File test = new File(path,System.currentTimeMillis() +".txt");
        file.transferTo(test);
        List<String> strings = Files.readLines(test, Charset.forName("UTF-8"));
        String join = Joiner.on("\n").join(strings);
        return join;
    }

    @GetMapping("/{id}")
    public void download(@PathVariable long id, HttpServletResponse httpServletResponse) throws IOException {
        File file = new File(path, id + ".txt");
        try(
            OutputStream os  = httpServletResponse.getOutputStream()
                ){
            httpServletResponse.setContentType("application/x-download");
            httpServletResponse.addHeader("Content-Disposition","attachment;filename=test.txt");
            Files.copy(file,os);
            os.flush();
        }
    }

}
