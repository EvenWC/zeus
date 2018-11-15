package com.wangcheng.zeus.core.config.validate.code.manager;

import com.wangcheng.zeus.core.config.validate.code.ValidateCode;

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
    ValidateCode generateValidateCode();

    /**
     * 检查验证码
     * @param validateCode
     * @param requestValidateCode
     * @return
     */
    Boolean checkValidateCode(ValidateCode validateCode,String requestValidateCode);

}
