package com.wangcheng.zeus.core.config.social.qq.api;

import java.io.IOException;

/**
 * @author: Administrator
 * @date: 2018/10/24 21:07
 * @description:
 */
public interface QQ {
    /**
     * 获取qq用户信息
     * @return
     * @throws IOException
     */
    QQUserInfo getQQUserInfo();
}
