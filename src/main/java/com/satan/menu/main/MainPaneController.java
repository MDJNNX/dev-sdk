/*
 * Copyright (c) 2019. TDH All Rights Reserved
 * Author: 马定健
 * Date: 2021/1/5
 */

package com.satan.menu.main;

import com.satan.DevSdkApplication;
import com.satan.menu.common.ConsoleLog;
import com.satan.util.PaneUtil;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * @Description: MainPaneController
 * @Authore: 马定健
 * @Date: 2021/1/5
 */
public class MainPaneController {

    /**
     * 状态栏
     */
    private VBox statusMenuPane;

    @FXML
    private Hyperlink consoleBtn;

    @FXML
    private TableView consoleTable;

    @FXML
    private TableColumn logMsg;

    /**
     * 初始化
     */
    @FXML
    private void initialize() {
        initConsoleTable();
        setConsoleIcon();
    }

    /**
     * 设置控制台图标
     */
    private void setConsoleIcon() {
        //设置console图标
        Image btnImg = new Image("config/images/console.png");
        ImageView imageView = new ImageView(btnImg);
        consoleBtn.setGraphic(imageView);
    }

    /**
     * 初始化控制台表格,手动将表格加入布局
     */
    @SuppressWarnings("unchecked")
    private void initConsoleTable() {
        TableView consoleTable = new TableView();
        TableColumn logMsg = new TableColumn("log") {{
            prefWidthProperty().bind(consoleTable.widthProperty().multiply(1));
        }};
        //表格可编辑需要同时设置表格可编辑和列可编辑;
        consoleTable.setEditable(true);
        logMsg.setCellFactory(TextFieldTableCell.forTableColumn());
        logMsg.setCellValueFactory(
                new PropertyValueFactory<ConsoleLog, String>("log"));
        consoleTable.getColumns().add(logMsg);
        consoleTable.setBorder(null);
        //这里需要使用双向绑定才能使得serVisible和Managed生效
        consoleTable.managedProperty().bindBidirectional(consoleTable.visibleProperty());
        consoleTable.setVisible(false);
        consoleTable.setMinHeight(150);
        consoleTable.setMaxHeight(150);
        //去除表头
        consoleTable.widthProperty().addListener((ObservableValue<? extends Number> ov, Number t, Number t1) -> {
            Pane header = (Pane) consoleTable.lookup("TableHeaderRow");
            if (header != null && header.isVisible()) {
                header.setMaxHeight(0);
                header.setMinHeight(0);
                header.setPrefHeight(0);
            }
        });

        //初始化表格列
        logMsg.setCellValueFactory(new PropertyValueFactory<ConsoleLog, String>("log"));

        statusMenuPane = ((VBox) consoleBtn.getParent().getParent());
        statusMenuPane.getChildren().add(consoleTable);
        statusMenuPane.managedProperty().bindBidirectional(statusMenuPane.visibleProperty());
        PaneUtil.setStatusMenuPane(statusMenuPane);
        PaneUtil.setConsoleTable(consoleTable);
    }

    /**
     * 点击console, 显示consoleTable
     */
    @FXML
    public void clickConsole() {
        statusMenuPane.getChildren().get(1).setVisible(true);
    }

    /**
     * console关闭按钮事件
     */
    @FXML
    public void closeConsole() {
        ((VBox) consoleBtn.getParent().getParent()).getChildren().get(1).setVisible(false);
    }

    /**
     * 加载不同的菜单面板到主面板;
     *
     * @param event 按钮事件
     * @throws Exception 异常
     */
    @FXML
    public void menuClick(ActionEvent event) throws Exception {
        String menuFxmlPath;
        MenuItem menuItem = (MenuItem) event.getSource();
        if (menuItem.getId().equals("about")) {
            showDialog();
            return;
        }
        switch (menuItem.getId()) {
            case "bjm":
                menuFxmlPath = "/static/fxml/EncodeAndDecodePane.fxml";
                break;
            case "online":
                menuFxmlPath = "/static/fxml/OnlineWebsitesPane.fxml";
                break;
            case "setting":
                menuFxmlPath = "/static/fxml/SettingPane.fxml";
                break;
            case "jsonFormat":
            default:
                menuFxmlPath = "/static/fxml/JsonFormatPane.fxml";
                break;

        }
        PaneUtil.getStatusMenuPane().setVisible(true);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(DevSdkApplication.class.getResource(menuFxmlPath));
        Node menuNode = loader.load();
        PaneUtil.getRootLayout().setCenter(menuNode);
    }

    private void showDialog() {
        Alert _alert = new Alert(Alert.AlertType.INFORMATION);
        _alert.setTitle("开发小工具");
        _alert.setHeaderText("欢迎使用开发小工具! \n ----作者:马定健");
        _alert.setContentText("本软件集成常用编码解码, json等工具, 方便离线时候使用; \n 同时也收录了常用的在线文档供快速访问。");
        _alert.initOwner(PaneUtil.getStage());
        _alert.show();
    }
}
