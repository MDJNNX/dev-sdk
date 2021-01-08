package com.satan.menu.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.satan.menu.common.BaseMenu;
import com.satan.menu.common.ConsoleLog;
import com.satan.menu.main.IStageResizeObserver;
import com.satan.util.PaneUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;

import java.text.MessageFormat;

/**
 * @Description: json格式化
 * @Date: 2021/1/5 23:25:18下午
 * @Author: MDJ
 */
public class JsonFormatController extends BaseMenu implements IStageResizeObserver {

    @FXML
    private HBox rawJsonPane;

    @FXML
    private TextArea rawJsonTextArea;

    @FXML
    private HBox formatJsonPane;

    @FXML
    private TextArea retJsonTextArea;

    /**
     * 初始化
     * 设置初始大小时,需要同时设置代码和界面的HBox的Vgrow为Always
     */
    @FXML
    private void initialize() {
        PaneUtil.getStageResizeObservers().add(this);
        setSize(PaneUtil.getStage().getWidth(), PaneUtil.getStage().getHeight());
    }

    /**
     * jackson 格式化代码:
     * ObjectMapper mapper = new ObjectMapper();
     * Object obj = mapper.readValue(rawJsonText, Object.class);
     * retJsonText = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
     */
    @FXML
    public void jsonFormat(ActionEvent event) {
        String retJsonText;
        String rawJsonText = "";
        String btnName = ((Button) event.getSource()).getText();
        try {
            rawJsonText = rawJsonTextArea.getText();
            JSONObject object = JSONObject.parseObject(rawJsonText);
            retJsonText = JSON.toJSONString(object, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue,
                    SerializerFeature.WriteDateUseDateFormat);
        } catch (Exception e) {
            e.printStackTrace();
            retJsonText = "json格式化异常:\n" + e.getMessage();
        }
        System.out.println(retJsonText);
        retJsonTextArea.setText(retJsonText);
        appendToConsole(new ConsoleLog(event.getSource(), MessageFormat.format("action:{0},rawText:{1},retText:{2}", btnName, rawJsonText, retJsonText)));

    }

    @FXML
    public void jsonPress(ActionEvent event) {
        String rawJsonText = rawJsonTextArea.getText();
        String retJsonText = rawJsonText.replaceAll("\\s+", "");
        String btnName = ((Button) event.getSource()).getText();
        retJsonTextArea.setText(retJsonText);
        appendToConsole(new ConsoleLog(event.getSource(), MessageFormat.format("action:{0},rawText:{1},retText:{2}", btnName, rawJsonText, retJsonText)));

    }

    /**
     * 调整大小
     */
    @Override
    public void resize(double width, double height) {
        System.out.println("JsonFormatController resize");
        setSize(width, height);
    }

    /**
     * 调整相关组件块宽度:
     *
     * @param stageWidth  宽
     * @param stageHeight 高
     */
    private void setSize(double stageWidth, double stageHeight) {
        rawJsonPane.setPrefWidth(stageWidth / 2 - 5);
        rawJsonTextArea.setPrefWidth(rawJsonPane.getWidth() - 5);
        formatJsonPane.setPrefWidth(stageWidth / 2 - 5);
    }
}
