package com.satan.menu.json.handler;

/**
 * @Description: 压缩处理器类
 * @Date: 2021/1/18 22:58:53下午
 * @Author: MDJ
 */
public class CompressHandler {

    /**
     * 原文本
     *
     * @return 结果
     */
    public String compress(String rawStr) {
        return rawStr.replaceAll("\\s+", "");
    }
}
