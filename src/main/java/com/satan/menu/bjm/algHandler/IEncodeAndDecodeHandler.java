package com.satan.menu.bjm.algHandler;

/**
 * @Description: 解码接口
 * @Date: 2021/1/5 22:17:17下午
 * @Author: MDJ
 */
public interface IEncodeAndDecodeHandler {

    /**
     * 加密
     *
     * @param rawStr 原始字符串
     * @return 加密结果
     */
    String encode(String rawStr);

    /**
     * 解密
     *
     * @param rawStr 原始字符串
     * @return 解密结果
     */
    String decode(String rawStr);
}
