package com.satan.menu.bjm.algandler;

import com.satan.menu.bjm.algHandler.Base64Handler;
import com.satan.menu.bjm.algHandler.DesHandler;
import com.satan.menu.bjm.algHandler.IEncodeAndDecodeHandler;
import com.satan.menu.bjm.algHandler.MD5Handler;

/**
 * @Description: 编码工厂
 * @Date: 2021/1/5 22:15:03下午
 * @Author: MDJ
 */
public class EncodeAndDecodeHandlerFactory {

    /**
     * 根据算法获取对应的加密解密处理类
     *
     * @param alg 算法
     * @return 处理类
     */
    public static IEncodeAndDecodeHandler getHandler(String alg) {
        IEncodeAndDecodeHandler handler;
        switch (alg) {
            case "MD5":
                handler = new MD5Handler();
                break;
            case "DES":
                handler = new DesHandler();
                break;
            case "BASE64":
            default:
                handler = new Base64Handler();
                break;
        }
        return handler;
    }
}
