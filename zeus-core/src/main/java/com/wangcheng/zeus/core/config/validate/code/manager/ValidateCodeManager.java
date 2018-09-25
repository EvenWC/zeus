package com.wangcheng.zeus.core.config.validate.code.manager;

import com.wangcheng.zeus.core.config.validate.code.ImageCode;

/**
 * @author Administrator
 * @Date: 2018/9/21 20:17
 * @Description:
 */
public interface ValidateCodeManager {
    /**
     * 生成验证码
     * @return
     */
    ImageCode  generateValidateCode();

    /**
     * 检查验证码
     * @param imageCode
     * @param requestValidateCode
     * @return
     */
    Boolean checkValidateCode(ImageCode imageCode,String requestValidateCode);

}
