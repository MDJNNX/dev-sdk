package com.satan.menu.online;

import com.satan.models.OnlineWebsites;
import com.satan.models.Website;
import com.satan.util.PaneUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.yaml.snakeyaml.Yaml;

/**
 * @Description: 在线网站
 * @Date: 2021/1/7 22:46:09下午
 * @Author: MDJ
 */
public class OnlineWebsitesController {

    /**
     * 站点列表面板
     */
    @FXML
    private GridPane websiteListPane;

    /**
     * 初始化
     */
    @FXML
    private void initialize() {
        PaneUtil.getStatusMenuPane().setVisible(false);
        renderWebSitesListPane();
    }

    /**
     * 渲染站点列表面板
     */
    private void renderWebSitesListPane() {
        Yaml yaml = new Yaml();
        OnlineWebsites websites = yaml.loadAs(getClass().getResourceAsStream("/config/websites.yml"), OnlineWebsites.class);
        int columnSize = 5;
        int rowIndex = 0;
        int websitesNum = websites.getWebsites().size();
        int itemIndex = 0;
        for (int columnIndex = 0; columnIndex < columnSize && itemIndex < websitesNum; columnIndex++) {
            if ((columnIndex + 1) % columnSize == 0) {
                columnIndex = 0;
                rowIndex++;
            }
            Website website = websites.getWebsites().get(itemIndex++);
            Hyperlink hyperlink = new Hyperlink();
            hyperlink.setText(website.getName());
            hyperlink.setVisited(false);
            hyperlink.setOnAction((ActionEvent event) -> loadWebsite(website));
            try {
                hyperlink.setGraphic(new ImageView(new Image("/images/" + website.getIcon())));
            } catch (Exception e) {
                hyperlink.setGraphic(new ImageView(new Image("/images/default.png")));
            }
            System.out.println("name:" + website.getName() + ",columnIndex:" + columnIndex + ",rowIndex:" + rowIndex);
            websiteListPane.add(hyperlink, columnIndex, rowIndex);
        }

    }

    /**
     * 加载新界面展示网站
     *
     * @param website 网站信息
     */
    private void loadWebsite(Website website) {
        try {
            VBox websiteViewPane = FXMLLoader.load(getClass().getResource("/fxml/WebsiteViewPane.fxml"));
            WebView browser = new WebView();
            PaneUtil.getWebsiteViewPane().getChildren().removeAll();
            PaneUtil.getWebsiteViewPane().getChildren().add(browser);
            WebEngine webEngine = browser.getEngine();
            webEngine.load(website.getUrl());
            browser.setPrefHeight(PaneUtil.getStage().getHeight());
            browser.setPrefWidth(PaneUtil.getStage().getWidth());
            PaneUtil.setWebView(browser);
            PaneUtil.getRootLayout().setCenter(websiteViewPane);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("打开网站失败...");
        }
    }

}
