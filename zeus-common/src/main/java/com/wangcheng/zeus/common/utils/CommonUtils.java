package com.wangcheng.zeus.common.utils;

import com.google.common.io.Files;
import org.slf4j.helpers.MessageFormatter;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

/**
 * @author : evan
 * @Date: 2018/8/29 21:00
 * @Description：工具类
 */
public class CommonUtils {

    private CommonUtils() throws IllegalAccessException {
        throw new IllegalAccessException("不能被实例化");
    }

    /**
     * 格式化一个带有占位符的字符串
     * @param message 原始字符串
     * @param args 占位符替换值
     * @return 格式化之后的结果
     */
    public static String format(String message,Object... args){
        return MessageFormatter.arrayFormat(message,args).getMessage();
    }

    public static void main(String[] args) throws IOException {
        modifyPackage("com.wangcheng.zeus","site.evan.zeus");
    }


    public static void modifyPackage(String oldPackage, String newPackage) throws IOException {
        String parentPath = "H:\\workspace\\JAVA\\zeus";
        File file = new File(parentPath);
        if(file.exists() && file.isDirectory()){
            File[] modules = file.listFiles();
            for (int i = 0; i < modules.length; i++) {
                File module = modules[i];
                if(!module.isDirectory()){
                    continue;
                }
                String oldPath = module.getAbsolutePath() +"\\src\\main\\java\\"+ oldPackage.replace(".","\\");
                System.out.println(oldPath);
                //先处理所有的.java文件，把所有的文件中带有oldPackage的文字替换成newPackage
                File p = new File(oldPath);
                createNewJava(p,oldPackage,newPackage);
                //删除久的历史
                p.delete();
            }
        }
    }

    private static void createNewJava(File file,String oldPackage,String newPackage) throws IOException {
        if(file.isDirectory()){
            File[] files = file.listFiles();
            for (File child : files) {
                createNewJava(child,oldPackage,newPackage);
            }
        }else{
            if(!file.getName().endsWith(".java") || file.getName().equals("CommonUtils")){
                return;
            }
            //老的文件路径
            String path = file.getAbsolutePath();
            //新的存放路径
            String newPath = path.replace(oldPackage.replace(".", "\\"), newPackage.replace(".", "\\"));
            File nFile = new File(newPath);
            if(nFile.exists()){
                nFile.delete();
            }
            Files.createParentDirs(nFile);
            nFile.createNewFile();
            List<String> lines = Files.readLines(file, Charset.defaultCharset());
            String separator = System.lineSeparator();
            lines.stream().map(line->line.replace(oldPackage,newPackage)).forEach(nline-> {
                try {
                    Files.append(nline+separator,nFile,Charset.forName("UTF-8"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
