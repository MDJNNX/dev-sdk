/*
 * Copyright (c) 2019. TDH All Rights Reserved
 * Author: 马定健
 * Date: 2021/1/4
 */

package com.satan.util;

import java.net.URL;
import java.util.Objects;

/**
 * @Description: 文件工具类
 * @Authore: 马定健
 * @Date: 2021/1/4
 */
public class FileUtil {

    /**
     * 获取classpath
     *
     * @return classpath
     */
    public static String getClassPath() {
        URL url = Thread.currentThread().getContextClassLoader().getResource("");
        if (Objects.isNull(url)) {
            throw new RuntimeException("未获取到CLASSPATH...");
        } else {
            return url.getPath();
        }
    }
}
