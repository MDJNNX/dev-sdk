package com.satan.menu.json.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * @Description: json格式化
 * @Date: 2021/1/18 22:51:12下午
 * @Author: MDJ
 */
public class JsonHandler extends CompressHandler {

    private static final String ENCODING = "UTF-8";

    public String handle(String btnId, String selected, String rawStr) {

        if (btnId.equals("formatBtn")) {
            return jsonFormat(rawStr);
        }

        if (selected.equals("JSON-XML")) {
            if (btnId.equals("convertBtn")) {
                return jsonToXml(rawStr);
            } else {
                return xmlToJson(rawStr);
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
//        retJsonCodeArea.replaceText(retStr);
//        appendToConsole(new ConsoleLog(event.getSource(), MessageFormat.format("action:{0},rawText:{1},retText:{2}", btnName, rawStr, retStr)));

    }

    /**
     * 将json转成xml
     *
     * @param rawStr 原始字符串
     * @return xml字符串
     */
    private String jsonToXml(String rawStr) {
        return "";
    }

    /**
     * 将xml转成json
     *
     * @param rawStr 原始字符串
     * @return json字符串
     */
    private String xmlToJson(String rawStr) {
        return "";
    }
}
