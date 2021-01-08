/*
 * Copyright (c) 2019. TDH All Rights Reserved
 * Author: 马定健
 * Date: 2021/1/8
 */

package com.satan.menu.online;

import com.satan.menu.main.IStageResizeObserver;
import com.satan.util.PaneUtil;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

/**
 * @Description: 显示站点面板
 * @Authore: 马定健
 * @Date: 2021/1/8
 */
public class WebsiteShowController implements IStageResizeObserver {

    @FXML
    private VBox websiteViewPane;

    @FXML
    public void initialize() {
        PaneUtil.getStageResizeObservers().add(this);
        PaneUtil.setWebsiteViewPane(websiteViewPane);
    }

    /**
     * 调整大小
     *
     * @param width  宽
     * @param height 高
     */
    @Override
    public void resize(double width, double height) {
        System.out.println("width:" + width + ",height:" + height);
        PaneUtil.getWebView().setPrefWidth(width - 5);
        PaneUtil.getWebView().setPrefHeight(height - 5);
    }
}
