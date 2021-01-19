/*
 * Copyright (c) 2019. TDH All Rights Reserved
 * Author: 马定健
 * Date: 2021/1/19
 */

package com.satan.util;

import static com.satan.menu.common.Constant.XML_HEADER;
import static com.satan.menu.common.Constant.XML_TAIL;

/**
 * @Description: xml相关
 * @Authore: 马定健
 * @Date: 2021/1/19
 */
public class XmlBaseUtil {

    /**
     * 添加xml头
     *
     * @param rawStr 原始字符串
     * @return 结果
     */
    public static String addHeader(String rawStr) {
        return XML_HEADER + rawStr + XML_TAIL;
    }

    /**
     * 添加xml头
     *
     * @param sb 原始字符串
     * @return 结果
     */
    public static StringBuilder addHeader(StringBuilder sb) {
        return new StringBuilder(addHeader(sb.toString()));
    }

    /**
     * 删除xml根节点
     *
     * @param rawStr 原始字符串
     * @return 结果
     */
    public static String removeTail(String rawStr) {
        return rawStr.substring(XML_HEADER.length() + 1, rawStr.length() - XML_TAIL.length() - 1);
    }

    /**
     * 删除xml根节点
     *
     * @param rawStr 原始字符串
     * @return 结果
     */
    public static StringBuilder removeTail(StringBuilder sb) {
        return new StringBuilder(removeTail(sb.toString()));
    }
}
