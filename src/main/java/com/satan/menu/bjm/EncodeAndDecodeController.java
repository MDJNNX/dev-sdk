package com.satan.menu.bjm;

import com.satan.menu.bjm.algandler.EncodeAndDecodeHandlerFactory;
import com.satan.menu.common.BaseMenu;
import com.satan.menu.common.ConsoleLog;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

import java.text.MessageFormat;

public class EncodeAndDecodeController extends BaseMenu {

    /**
     * 算法下拉选择框
     */
    @FXML
    private ComboBox algComboBox;

    /**
     * 原始文本
     */
    @FXML
    private TextArea rawTextArea;

    /**
     * 转换后文本
     */
    @FXML
    private TextArea retTextArea;

    /**
     * 初始化
     */
    @FXML
    @SuppressWarnings("unchecked")
    private void initialize() {
        ObservableList<String> options = FXCollections.observableArrayList("BASE64", "MD5", "DES");
        algComboBox.setItems(options);
        algComboBox.setValue("BASE64");

        algComboBox.getSelectionModel().selectedItemProperty().addListener((ObservableValue observable, Object oldValue, Object newValue) -> {
            if (!oldValue.equals(newValue)) {
                retTextArea.setText("");
            }
        });

    }

    @FXML
    public void clear() {
        rawTextArea.setText("");
        retTextArea.setText("");
    }

    @FXML
    public void encode(ActionEvent event) {
        String alg = (String) algComboBox.getValue();
        String rawText = rawTextArea.getText();
        String retText = com.satan.menu.bjm.algandler.EncodeAndDecodeHandlerFactory.getHandler(alg).encode(rawText);
        retTextArea.setText(retText);
        appendToConsole(new ConsoleLog(event.getSource(), MessageFormat.format("alg:{0},rawText:{1},retText:{2}", alg, rawText, retText)));
    }

    @FXML
    public void decode(ActionEvent event) {
        String alg = (String) algComboBox.getValue();
        String rawText = rawTextArea.getText();
        String retText = EncodeAndDecodeHandlerFactory.getHandler((String) algComboBox.getValue()).decode(rawText);
        retTextArea.setText(retText);
        appendToConsole(new ConsoleLog(event.getSource(), MessageFormat.format("alg:{0},rawText:{1},retText:{2}", alg, rawText, retText)));
    }


    @FXML
    public void algChanged() {
        System.out.println("nnn");
    }
}
