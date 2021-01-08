package com.satan.menu.bjm.algHandler;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Description: MD5Handler
 * @Date: 2021/1/5 22:29:03下午
 * @Author: MDJ
 */
public class MD5Handler implements IEncodeAndDecodeHandler {

    /**
     * 加密
     *
     * @param rawStr 原始字符串
     * @return 加密结果
     */
    @Override
    public String encode(String rawStr) {
        byte[] secretBytes;
        try {
            secretBytes = MessageDigest.getInstance("md5").digest(rawStr.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有这个md5算法！");
        }
        StringBuilder zero = new StringBuilder("0");
        StringBuilder md5code = new StringBuilder(new BigInteger(1, secretBytes).toString(16));
        int len = 32;
        for (int i = 0; i < len - md5code.length(); i++) {
            md5code = zero.append(md5code);
        }
        return md5code.toString();
    }

    /**
     * 解密
     *
     * @param rawStr 原始字符串
     * @return 解密结果
     */
    @Override
    public String decode(String rawStr) {
        return "暂时不支持MD5解密";
    }
}
