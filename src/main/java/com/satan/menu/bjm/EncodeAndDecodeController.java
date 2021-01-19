package com.satan.menu.bjm;

import com.satan.menu.bjm.algandler.EncodeAndDecodeHandlerFactory;
import com.satan.menu.common.BaseMenu;
import com.satan.menu.common.ConsoleLog;
import com.satan.menu.common.Constant;
import com.satan.menu.common.ITextFocus;
import com.satan.util.PaneUtil;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;

import java.text.MessageFormat;
import java.util.function.BiFunction;

public class EncodeAndDecodeController extends BaseMenu implements ITextFocus {

    /**
     * 算法下拉选择框
     */
    @FXML
    private ComboBox algComboBox;

    @FXML
    private HBox encodeAndDecodePane;

    /**
     * 原始文本
     */
    private CodeArea rawCodeArea;

    /**
     * 转换后文本
     */
    private CodeArea retCodeArea;

    public EncodeAndDecodeController() {
        rawCodeArea = new CodeArea();
        rawCodeArea.setWrapText(true);
        rawCodeArea.setParagraphGraphicFactory(LineNumberFactory.get(rawCodeArea));
        rawCodeArea.getStyleClass().add(Constant.CssStyle.TWO_LEFT_PADDING);
        rawCodeArea.requestFocus();

        retCodeArea = new CodeArea();
        retCodeArea.setWrapText(true);
        retCodeArea.setParagraphGraphicFactory(LineNumberFactory.get(rawCodeArea));
        retCodeArea.getStyleClass().add(Constant.CssStyle.TWO_LEFT_PADDING);
        retCodeArea.requestFocus();
    }

    /**
     * 初始化
     */
    @FXML
    @SuppressWarnings("unchecked")
    private void initialize() {
        ObservableList<String> options = FXCollections.observableArrayList(Constant.EncodeAndDecode.BASE64, Constant.EncodeAndDecode.MD5, Constant.EncodeAndDecode.DES);
        algComboBox.setItems(options);
        algComboBox.setValue(Constant.EncodeAndDecode.BASE64);

        algComboBox.getSelectionModel().selectedItemProperty().addListener((ObservableValue observable, Object oldValue, Object newValue) -> {
            if (!oldValue.equals(newValue)) {
                retCodeArea.replaceText("");
            }
            focus();
        });
        setSize(PaneUtil.getStage().getWidth(), PaneUtil.getStage().getHeight());
        encodeAndDecodePane.getChildren().addAll(rawCodeArea, retCodeArea);
    }

    @FXML
    public void clear() {
        rawCodeArea.replaceText(Constant.EMPTY_STR);
        retCodeArea.replaceText(Constant.EMPTY_STR);
        focus();
    }

    @FXML
    public void encode() {
        operate((alg, rawText) -> EncodeAndDecodeHandlerFactory.getHandler(alg).encode(rawText));
    }

    @FXML
    public void decode() {
        operate((alg, rawText) -> EncodeAndDecodeHandlerFactory.getHandler(alg).decode(rawText));
    }

    /**
     * 计算方法
     *
     * @param fun 函数
     */
    private void operate(BiFunction<String, String, String> fun) {
        String alg = (String) algComboBox.getValue();
        String rawText = rawCodeArea.getText();
        String retText = fun.apply(alg, rawText);
        retCodeArea.replaceText(retText);
        appendToConsole(new ConsoleLog(null, MessageFormat.format("算法:{0},原文本:{1},结果:{2}", alg, rawText, retText)));
    }

    /**
     * 文本域聚焦
     */
    @Override
    public void focus() {
        rawCodeArea.requestFocus();
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
