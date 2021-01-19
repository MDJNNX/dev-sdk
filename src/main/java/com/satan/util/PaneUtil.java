/*
 * Copyright (c) 2019. TDH All Rights Reserved
 * Author: 马定健
 * Date: 2021/1/5
 */

package com.satan.util;

import com.satan.menu.main.IStageResizeObserver;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


/**
 * @Description: PaneUtil
 * @Authore: 马定健
 * @Date: 2021/1/5
 */
public class PaneUtil {

    private static Stage stage;
    private static BorderPane rootLayout;
    private static TableView consoleTable;
    private static VBox statusMenuPane;
    private static VBox websiteViewPane;
    private static WebView webView;
    private static List<IStageResizeObserver> StageResizeObservers = new ArrayList<>();

    public static List<IStageResizeObserver> getStageResizeObservers() {
        return StageResizeObservers;
    }

    public static void setStageResizeObservers(List<IStageResizeObserver> stageResizeObservers) {
        StageResizeObservers = stageResizeObservers;
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        PaneUtil.stage = stage;
    }

    public static void setRootLayout(BorderPane rl) {
        rootLayout = rl;
    }

    public static BorderPane getRootLayout() {
        return rootLayout;
    }

    public static TableView getConsoleTable() {
        return consoleTable;
    }

    public static void setConsoleTable(TableView consoleTable) {
        PaneUtil.consoleTable = consoleTable;
    }

    public static VBox getStatusMenuPane() {
        return statusMenuPane;
    }

    public static void setStatusMenuPane(VBox statusMenuPane) {
        PaneUtil.statusMenuPane = statusMenuPane;
    }

    public static VBox getWebsiteViewPane() {
        return websiteViewPane;
    }

    public static void setWebsiteViewPane(VBox websiteViewPane) {
        PaneUtil.websiteViewPane = websiteViewPane;
    }

    public static WebView getWebView() {
        return webView;
    }

    public static void setWebView(WebView webView) {
        PaneUtil.webView = webView;
    }
}
