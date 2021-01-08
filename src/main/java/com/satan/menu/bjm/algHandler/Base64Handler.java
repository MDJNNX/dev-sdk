/*
 * Copyright (c) 2019. TDH All Rights Reserved
 * Author: 马定健
 * Date: 2020/12/31
 */

package com.satan.menu.bjm.algHandler;

import java.util.Base64;

/**
 * @Description: base64处理器
 * @Authore: 马定健
 * @Date: 2020/12/31
 */
public class Base64Handler implements IEncodeAndDecodeHandler {

    public Base64Handler() {
    }

    /**
     * 加密
     *
     * @param rawStr 原始字符串
     * @return 加密结果
     */
    @Override
    public String encode(String rawStr) {
        return Base64.getEncoder().encodeToString(rawStr.getBytes());
    }

    /**
     * 解密
     *
     * @param rawStr 原始字符串
     * @return 解密结果
     */
    @Override
    public String decode(String rawStr) {
        return new String(Base64.getDecoder().decode(rawStr));
    }
}
