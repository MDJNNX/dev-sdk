/*
 * Copyright (c) 2019. TDH All Rights Reserved
 * Author: 马定健
 * Date: 2021/1/19
 */

package com.satan.menu.json.handler;

import com.satan.util.XmlBaseUtil;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import org.json.JSONException;
import org.json.XML;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

/**
 * @Description: xml处理类
 * @Authore: 马定健
 * @Date: 2021/1/19
 */
public class XmlHandler {

    public String handle(String btnId, String selected, String rawStr) {
        String retStr = "";
        if (btnId.equals("formatBtn")) {
            rawStr = XmlBaseUtil.addHeader(rawStr);
            retStr = format(rawStr);
        }
        retStr = XmlBaseUtil.removeTail(retStr).trim();
        return retStr;
    }

    /**
     * 将xml转成json
     *
     * @param rawStr 原始字符串
     * @return json字符串
     */
    String xmlToJson(String rawStr) {
        String retStr;
        try {
            org.json.JSONObject xmlJSONObj = XML.toJSONObject(rawStr);
            retStr = xmlJSONObj.toString(0);
        } catch (JSONException je) {
            System.out.println(je.toString());
            retStr = "error...";
        }
        return retStr;
    }

    /**
     * 格式化
     *
     * @param unformattedXml 未格式化xml
     * @return 结果
     */
    String format(String unformattedXml) {
        try {
            final Document document = parseXmlFile(unformattedXml);
            OutputFormat format = new OutputFormat(document);
            format.setLineWidth(65);
            format.setIndenting(true);
            format.setIndent(2);
            Writer out = new StringWriter();
            XMLSerializer serializer = new XMLSerializer(out, format);
            serializer.serialize(document);
            return out.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 解析xml文本内容
     *
     * @param in 内容
     * @return doc
     */
    private Document parseXmlFile(String in) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(in));
            return db.parse(is);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
