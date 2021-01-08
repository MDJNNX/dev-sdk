package com.satan.menu.bjm.algHandler;

import tdh.security.cert.KEY;
import tdh.security.encryption.DESHelper;

/**
 * @Description: DesHandler
 * @Date: 2021/1/5 23:23:20下午
 * @Author: MDJ
 */
public class DesHandler implements IEncodeAndDecodeHandler {

    /**
     * 默认编码
     */
    private static final String CHARSET = "utf-8";

    /**
     * 加密/解密算法-工作模式-填充模式
     */
    private static final String CIPHER_ALGORITHM = "DESede/ECB/PKCS5Padding";

    /**
     * 偏移变量，固定占8位字节
     */
    private final static String IV_PARAMETER = "12345678";

    /**
     * 密钥算法
     */
    private static final String ALGORITHM = "DES";

    /**
     * 加密秘钥
     */
    private static final String SECRETE = "EncodeAndDecode_DESC";


    /**
     * 加密
     *
     * @param rawStr 原始字符串
     * @return 加密结果
     */
    @Override
    public String encode(String rawStr) {
        String retStr;
        try {
            retStr = DESHelper.encryptDESEDE(KEY.DESEDE_PUB_KEY, rawStr);
        } catch (Exception e) {
            retStr = "DES加密异常...";
        }
        return retStr;
    }

    /**
     * 解密
     *
     * @param rawStr 原始字符串
     * @return 解密结果
     */
    @Override
    public String decode(String rawStr) {
        String retStr;
        try {
            retStr = DESHelper.decryptDESEDE(KEY.DESEDE_PUB_KEY, rawStr);
        } catch (Exception e) {
            e.printStackTrace();
            retStr = "DES解密异常...";
        }
        return retStr;
    }
}
