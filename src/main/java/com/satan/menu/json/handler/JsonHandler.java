package com.satan.menu.json.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.satan.util.XmlBaseUtil;

import java.util.Iterator;
import java.util.Map;

/**
 * @Description: json格式化
 * @Date: 2021/1/18 22:51:12下午
 * @Author: MDJ
 */
public class JsonHandler extends CompressHandler {

    public String handle(String btnId, String selected, String rawStr) {

        if (btnId.equals("formatBtn")) {
            return jsonFormat(rawStr);
        }

        if (selected.equals("JSON-XML")) {
            if (btnId.equals("convertBtn")) {
                return jsonToXml(rawStr);
            } else {
                return new XmlHandler().xmlToJson(rawStr);
            }
        }
        return "";
    }

    /**
     * jackson 格式化代码:
     * ObjectMapper mapper = new ObjectMapper();
     * Object obj = mapper.readValue(rawStr, Object.class);
     * retStr = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
     */
    private String jsonFormat(String rawStr) {
        String retStr;
        try {
            JSONObject object = JSONObject.parseObject(rawStr);
            retStr = JSON.toJSONString(object,
                    SerializerFeature.PrettyFormat,
                    SerializerFeature.WriteMapNullValue,
                    SerializerFeature.WriteDateUseDateFormat);
        } catch (Exception e) {
            e.printStackTrace();
            retStr = "json格式化异常:\n" + e.getMessage();
        }
        return retStr;
    }

    /**
     * 将json转成xml
     *
     * @param rawStr 原始字符串
     * @return xml字符串
     */
    private String jsonToXml(String rawStr) {
        String retStr;
        try {
            StringBuilder sb = new StringBuilder();
            JSONObject json = JSONObject.parseObject(rawStr, Feature.OrderedField);
            jsonToXmlstr(json, sb);
            sb = XmlBaseUtil.addHeader(sb);
            retStr = new XmlHandler().format(sb.toString());
            retStr = XmlBaseUtil.removeTail(retStr);
        } catch (Exception e) {
            e.printStackTrace();
            retStr = "JSON转XML异常:\n" + e.getMessage();
        }
        return retStr.trim();
    }


    /**
     * json文本转xml方法
     *
     * @param json json字符串
     * @param sb   结果
     */
    private static void jsonToXmlstr(JSONObject json, StringBuilder sb) {
        Iterator<Map.Entry<String, Object>> it = json.entrySet().iterator();
        Map.Entry<String, Object> en;
        while (it.hasNext()) {
            en = it.next();
            if (en.getKey().startsWith("-")) {
                continue;
            }
            if (en.getKey().equals("#text")) {
                sb.append(en.getValue());//直接输出文本
                continue;
            }
            if (en.getValue() instanceof JSONObject) {
                sb.append("<").append(en.getKey()).append(getAttr((JSONObject) en.getValue())).append(">");
                JSONObject jo = json.getJSONObject(en.getKey());
                jsonToXmlstr(jo, sb);
                sb.append("</").append(en.getKey()).append(">");
            } else if (en.getValue() instanceof JSONArray) {
                JSONArray jarray = json.getJSONArray(en.getKey());
                for (int i = 0; i < jarray.size(); i++) {
                    JSONObject jsonobject = jarray.getJSONObject(i);
                    sb.append("<").append(en.getKey()).append(getAttr(jsonobject)).append(">");
                    jsonToXmlstr(jsonobject, sb);
                    sb.append("</").append(en.getKey()).append(">");
                }
            } else {
                sb.append("<").append(en.getKey()).append(">").append(en.getValue()).append("</").append(en.getKey()).append(">");
            }
        }
    }

    /**
     * 拼当前节点属性
     */
    private static String getAttr(JSONObject json) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Object> entity : json.entrySet()) {
            if (entity.getKey().startsWith("-")) {
                sb.append(" ").append(entity.getKey().substring(1)).append("=\"").append(entity.getValue().toString()).append("\"");
            }

        }
        return sb.toString();
    }
}
