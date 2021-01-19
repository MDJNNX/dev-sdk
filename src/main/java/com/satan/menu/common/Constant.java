/*
 * Copyright (c) 2019. TDH All Rights Reserved
 * Author: 马定健
 * Date: 2021/1/19
 */

package com.satan.menu.common;


import java.util.HashMap;
import java.util.Map;

import static com.satan.menu.common.Constant.MenuIds.*;

/**
 * @Description: 常量
 * @Authore: 马定健
 * @Date: 2021/1/19
 */
public class Constant {

    //主界面宽度
    public static final int WIDTH = 800;

    //主界面高度
    public static final int HEIGHT = 600;

    public static final String EMPTY_STR = "";

    public static String XML_HEADER = "<?xml version=\"1.0\" encoding=\"utf-8\"?><root>";

    public static String XML_TAIL = "</root>";

    //所有菜单和视图映射
    public static Map<String, String> MENU_FXML_MAP = new HashMap<>();

    static {
        MENU_FXML_MAP.put(MAIN, "/static/fxml/MainPane.fxml");
        MENU_FXML_MAP.put(BJM, "/static/fxml/EncodeAndDecodePane.fxml");
        MENU_FXML_MAP.put(ONLINE, "/static/fxml/OnlineWebsitesPane.fxml");
        MENU_FXML_MAP.put(SETTING, "/static/fxml/SettingPane.fxml");
        MENU_FXML_MAP.put(JSON_FORMAT, "/static/fxml/JsonFormatPane.fxml");
    }

    /**
     * 菜单id
     */
    public static class MenuIds {
        public static String ABOUT = "about";
        public static String JSON_FORMAT = "jsonFormat";
        public static String SETTING = "setting";
        public static String ONLINE = "online";
        public static String BJM = "bjm";
        public static String MAIN = "main";
    }

    /**
     * 按钮id
     */
    public static class BtnIds {
        public static String FORMAT_BTN = "formatBtn";
        public static String CONVERT_BTN = "convertBtn";
        public static String COMPRESS_BTN = "compressBtn";
    }

    /**
     * 格式化下拉框可选项
     */
    public static class FormatSelected {
        public static String JSON = "JSON";
        public static String XML = "XML";
        public static String JSON_XML = "JSON-XML";
    }

    /**
     * 软件信息
     */
    public static class SoftInfo {
        public static final String TITLE = "Mdj-开发小工具";
        public static final String HEADER_TEXT = "欢迎使用开发小工具! \n ----作者:马定健";
        public static final String CONTENT_TEXT = "本软件集成常用编码解码, json等工具, 方便离线时候使用; \n 同时也收录了常用的在线文档供快速访问。";
    }

    /**
     * 静态资源
     */
    public static class StaticFiles {
        public static final String SOFT_LOGO = "/config/images/logo.jpg";
        public static final String COMMON_CSS = "/static/css/common.css";
        public static final String CONSOLE_LOGO = "config/images/console.png";
    }

    /**
     * css 样式
     */
    public static class CssStyle {
        public static final String TWO_LEFT_PADDING = "two-left-padding";
    }

    /**
     * 加解密算法
     */
    public static class EncodeAndDecode {
        public static final String BASE64 = "BASE64";
        public static final String MD5 = "MD5";
        public static final String DES = "DES";
    }
}
