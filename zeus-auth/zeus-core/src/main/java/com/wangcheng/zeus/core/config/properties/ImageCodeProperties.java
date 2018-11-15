package com.wangcheng.zeus.core.config.properties;

/**
 * @author: Administrator
 * @Date: 2018/9/21 20:02
 * @Description: 图片验证码属性配置
 */
public class ImageCodeProperties {
    /**需要验证码的url*/
    private String urls         =        "/authentication/logIn";
    /**过期时间*/
    private long expireIn       =         30;
    /**图片高度*/
    private String imageHeight  =         "50";
    /**图片宽度*/
    private String imageWidth   =         "160";
    /**字体颜色*/
    private String fontColor    =         "blue";
    /**字体大小*/
    private String fontSize     =         "40";
    /**验证码长度*/
    private String charLength   =         "4";
    /**字体名称*/
    private String fontsName    =         "Arial, Courier";
    /**sessionKey*/
    private String sessionKey   =         "session_key";
    /**是否有边框*/
    private boolean border      =         false;
    /**边框颜色*/
    private String borderColor  =         "black";

    public String getUrls() {
        return urls;
    }

    public void setUrls(String urls) {
        this.urls = urls;
    }

    public long getExpireIn() {
        return expireIn;
    }

    public void setExpireIn(long expireIn) {
        this.expireIn = expireIn;
    }

    public String getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(String imageHeight) {
        this.imageHeight = imageHeight;
    }

    public String getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(String imageWidth) {
        this.imageWidth = imageWidth;
    }

    public String getFontColor() {
        return fontColor;
    }

    public void setFontColor(String fontColor) {
        this.fontColor = fontColor;
    }

    public String getFontSize() {
        return fontSize;
    }

    public void setFontSize(String fontSize) {
        this.fontSize = fontSize;
    }

    public String getCharLength() {
        return charLength;
    }

    public void setCharLength(String charLength) {
        this.charLength = charLength;
    }

    public String getFontsName() {
        return fontsName;
    }

    public void setFontsName(String fontsName) {
        this.fontsName = fontsName;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public boolean getBorder() {
        return border;
    }

    public void setBorder(boolean border) {
        this.border = border;
    }

    public String getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(String borderColor) {
        this.borderColor = borderColor;
    }
}
