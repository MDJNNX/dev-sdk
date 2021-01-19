package com.satan.menu.json;

import com.satan.menu.common.BaseMenu;
import com.satan.menu.json.handler.CompressHandler;
import com.satan.menu.json.handler.JsonHandler;
import com.satan.menu.json.handler.XmlHandler;
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

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Description: json格式化
 * @Date: 2021/1/5 23:25:18下午
 * @Author: MDJ
 */
public class JsonFormatController extends BaseMenu implements IStageResizeObserver {

    @FXML
    private HBox rawJsonPane;

    /**
     * 转换规则下拉选择框
     */
    @FXML
    private ComboBox convertorComboBox;

    private CodeArea rawCodeArea;

    private CodeArea retCodeArea;

    /**
     * 操作按钮列表面板
     */
    @FXML
    private HBox operateBtnsPane;

    /**
     * 格式化按钮
     */
    @FXML
    private Button formatBtn;

    /**
     * 转换按钮
     */
    @FXML
    private Button convertBtn;

    /**
     * 反转按钮
     */
    @FXML
    private Button reverseConvertBtn;

    /**
     * 压缩按钮
     */
    @FXML
    private Button compressBtn;

    /**
     * 去除转义按钮
     */
    @FXML
    private Button noescapeBtn;

    /**
     * 重置清空按钮
     */
    @FXML
    private Button resetBtn;

    private static Map<String, List<Button>> btnMaps = new HashMap<>();
    private static Set<Button> allBtns = new HashSet<>();

    public JsonFormatController() {
        rawCodeArea = new CodeArea();
        rawCodeArea.setWrapText(true);
        rawCodeArea.setParagraphGraphicFactory(LineNumberFactory.get(rawCodeArea));
        rawCodeArea.getStyleClass().add("two-left-padding");

        retCodeArea = new CodeArea();
        retCodeArea.setWrapText(true);
        retCodeArea.setParagraphGraphicFactory(LineNumberFactory.get(retCodeArea));
        retCodeArea.getStyleClass().add("two-left-padding");
    }

    /**
     * 初始化
     * 设置初始大小时,需要同时设置代码和界面的HBox的Vgrow为Always
     */
    @FXML
    @SuppressWarnings("unchecked")
    private void initialize() {
        ObservableList<String> options = FXCollections.observableArrayList("JSON", "XML", "JSON-XML");
        convertorComboBox.setItems(options);
        convertorComboBox.setValue("JSON");

        convertorComboBox.getSelectionModel().selectedItemProperty().addListener((ObservableValue observable, Object oldValue, Object newValue) -> {
            if (!oldValue.equals(newValue)) {
                retCodeArea.replaceText("");
            }
            showBtns((String) newValue);
        });

        initBtnVisibleProperty();
        showBtns("JSON");

        PaneUtil.getStageResizeObservers().add(this);
        setSize(PaneUtil.getStage().getWidth(), PaneUtil.getStage().getHeight());
        rawJsonPane.getChildren().addAll(rawCodeArea, retCodeArea);

        rawCodeArea.setVisible(true);
        rawCodeArea.requestFocus();
    }

    /**
     * 初始化按钮的可见性
     */
    private void initBtnVisibleProperty() {
        btnMaps.put("JSON", Arrays.asList(formatBtn, compressBtn, resetBtn));
        btnMaps.put("XML", Arrays.asList(formatBtn, compressBtn, resetBtn));
        btnMaps.put("JSON-XML", Arrays.asList(convertBtn, reverseConvertBtn, resetBtn));
        allBtns.addAll(Arrays.asList(formatBtn, compressBtn, noescapeBtn, convertBtn, reverseConvertBtn, resetBtn));
        allBtns.forEach((btn) -> btn.managedProperty().bindBidirectional(btn.visibleProperty()));
    }

    /**
     * 只显示该显示的btn
     *
     * @param item 标识:JSON/XML/JSON-XML
     */
    private void showBtns(String item) {
        List<Button> btns = btnMaps.get(item);
        allBtns.forEach((btn) -> {
            btn.setVisible(btns.contains(btn));
        });
    }

    @FXML
    private void reset() {
        rawCodeArea.replaceText("");
        retCodeArea.replaceText("");
    }

    @FXML
    private void btnClick(ActionEvent event) {
        String btnId = ((Button) event.getTarget()).getId();
        String selected = (String) convertorComboBox.getValue();
        String rawStr = rawCodeArea.getText();
        String retStr = "";
        if (btnId.equals("compressBtn")) {
            retStr = new CompressHandler().compress(rawStr);
        } else {
            if (selected.startsWith("JSON")) {
                retStr = new JsonHandler().handle(btnId, selected, rawStr);
            } else if (selected.startsWith("XML")) {
                retStr = new XmlHandler().handle(btnId, selected, rawStr);
            }
        }
        retCodeArea.replaceText(retStr);
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
        rawCodeArea.setPrefWidth(stageWidth / 2 - 5);
        retCodeArea.setPrefWidth(stageWidth / 2 - 5);
    }
}
