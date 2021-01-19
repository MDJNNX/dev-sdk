/*
 * Copyright (c) 2019. TDH All Rights Reserved
 * Author: 马定健
 * Date: 2021/1/5
 */

package com.satan.menu.main;

import com.satan.DevSdkApplication;
import com.satan.menu.common.ConsoleLog;
import com.satan.menu.common.Constant;
import com.satan.menu.common.ITextFocus;
import com.satan.util.PaneUtil;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import static com.satan.menu.common.Constant.MENU_FXML_MAP;

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
        Image btnImg = new Image(Constant.StaticFiles.CONSOLE_LOGO);
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
     */
    @FXML
    public void menuClick(ActionEvent event) {
        MenuItem menuItem = (MenuItem) event.getSource();
        if (Constant.MenuIds.ABOUT.equals(menuItem.getId())) {
            showDialog();
            return;
        }
        showPane(menuItem.getId());
    }

    /**
     * 显示面板
     *
     * @param menuId 菜单id
     */
    public void showPane(String menuId) {
        String menuFxmlPath = MENU_FXML_MAP.get(menuId);
        PaneUtil.getStatusMenuPane().setVisible(true);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(DevSdkApplication.class.getResource(menuFxmlPath));
        try {
            Node menuNode = loader.load();
            PaneUtil.getRootLayout().setCenter(menuNode);
            if (loader.getController() instanceof ITextFocus) {
                ((ITextFocus)loader.getController()).focus();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("加载异常...");
        }
    }

    private void showDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(Constant.SoftInfo.TITLE);
        alert.setHeaderText(Constant.SoftInfo.HEADER_TEXT);
        alert.setContentText(Constant.SoftInfo.CONTENT_TEXT);
        alert.initOwner(PaneUtil.getStage());
        alert.show();
    }
}
