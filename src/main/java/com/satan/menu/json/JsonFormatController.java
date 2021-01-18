package com.satan.menu.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.satan.menu.common.BaseMenu;
import com.satan.menu.common.ConsoleLog;
import com.satan.menu.main.IStageResizeObserver;
import com.satan.util.PaneUtil;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;

import java.text.MessageFormat;

/**
 * @Description: json格式化
 * @Date: 2021/1/5 23:25:18下午
 * @Author: MDJ
 */
public class JsonFormatController extends BaseMenu implements IStageResizeObserver {

    /**
     * 转换规则下拉选择框
     */
    @FXML
    private ComboBox convertorComboBox;

    private CodeArea rawJsonCodeArea;

    private CodeArea retJsonCodeArea;

    @FXML
    private HBox rawJsonPane;

    public JsonFormatController() {
        rawJsonCodeArea = new CodeArea();
        rawJsonCodeArea.setWrapText(true);
        rawJsonCodeArea.setParagraphGraphicFactory(LineNumberFactory.get(rawJsonCodeArea));
        rawJsonCodeArea.getStyleClass().add("two-left-padding");

        retJsonCodeArea = new CodeArea();
        retJsonCodeArea.setWrapText(true);
        retJsonCodeArea.setParagraphGraphicFactory(LineNumberFactory.get(retJsonCodeArea));
        retJsonCodeArea.getStyleClass().add("two-left-padding");
    }

    /**
     * 初始化
     * 设置初始大小时,需要同时设置代码和界面的HBox的Vgrow为Always
     */
    @FXML
    @SuppressWarnings("unchecked")
    private void initialize() {
        ObservableList<String> options = FXCollections.observableArrayList("XML-XML", "JSON-JSON", "JSON-XML");
        convertorComboBox.setItems(options);
        convertorComboBox.setValue("JSON-JSON");

        convertorComboBox.getSelectionModel().selectedItemProperty().addListener((ObservableValue observable, Object oldValue, Object newValue) -> {
            if (!oldValue.equals(newValue)) {
                retJsonCodeArea.replaceText("");
            }
        });

        PaneUtil.getStageResizeObservers().add(this);
        setSize(PaneUtil.getStage().getWidth(), PaneUtil.getStage().getHeight());
        rawJsonPane.getChildren().addAll(rawJsonCodeArea, retJsonCodeArea);
    }

    /**
     * jackson 格式化代码:
     * ObjectMapper mapper = new ObjectMapper();
     * Object obj = mapper.readValue(rawJsonText, Object.class);
     * retJsonText = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
     */
    public void jsonFormat(ActionEvent event) {
        String retJsonText;
        String rawJsonText = "";
        String btnName = ((Button) event.getSource()).getText();
        try {
            rawJsonText = rawJsonCodeArea.getText();
            JSONObject object = JSONObject.parseObject(rawJsonText);
            retJsonText = JSON.toJSONString(object, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue,
                    SerializerFeature.WriteDateUseDateFormat);
        } catch (Exception e) {
            e.printStackTrace();
            retJsonText = "json格式化异常:\n" + e.getMessage();
        }
        System.out.println(retJsonText);
        retJsonCodeArea.replaceText(retJsonText);
        appendToConsole(new ConsoleLog(event.getSource(), MessageFormat.format("action:{0},rawText:{1},retText:{2}", btnName, rawJsonText, retJsonText)));

    }

    public void jsonPress(ActionEvent event) {
        String rawJsonText = rawJsonCodeArea.getText();
        String retJsonText = rawJsonText.replaceAll("\\s+", "");
        String btnName = ((Button) event.getSource()).getText();
        retJsonCodeArea.replaceText(retJsonText);
        appendToConsole(new ConsoleLog(event.getSource(), MessageFormat.format("action:{0},rawText:{1},retText:{2}", btnName, rawJsonText, retJsonText)));
    }

    /**
     * 调整大小
     */
    @Override
    public void resize(double width, double height) {
        setSize(width, height);
    }

    /**
     * 调整相关组件块宽度:
     *
     * @param stageWidth  宽
     * @param stageHeight 高
     */
    private void setSize(double stageWidth, double stageHeight) {
        rawJsonCodeArea.setPrefWidth(stageWidth / 2 - 5);
        retJsonCodeArea.setPrefWidth(stageWidth / 2 - 5);
    }
}
