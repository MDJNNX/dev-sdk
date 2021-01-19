package com.satan;

import com.satan.menu.common.Constant;
import com.satan.menu.main.IStageResizeObserver;
import com.satan.menu.main.MainPaneController;
import com.satan.util.PaneUtil;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

import static com.satan.menu.common.Constant.MENU_FXML_MAP;

public class DevSdkApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(MENU_FXML_MAP.get(Constant.MenuIds.MAIN)));
        BorderPane rootLayout = fxmlLoader.load();
        PaneUtil.setStage(primaryStage);
        PaneUtil.setRootLayout(rootLayout);
        primaryStage.getIcons().add(new Image(Constant.StaticFiles.SOFT_LOGO));
        primaryStage.setTitle(Constant.SoftInfo.TITLE);
        Scene scene = new Scene(rootLayout, Constant.WIDTH, Constant.HEIGHT);
        scene.getStylesheets().add(getClass().getResource(Constant.StaticFiles.COMMON_CSS).toExternalForm());
        PaneUtil.setScene(scene);
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.show();

        //窗口大小调整监听
        ChangeListener<Number> stageSizeListener = (observable, oldValue, newValue) -> {
            List<IStageResizeObserver> stageResizeObservers = PaneUtil.getStageResizeObservers();
            double height = primaryStage.getHeight();
            double width = primaryStage.getWidth();
            stageResizeObservers.forEach((controller) -> controller.resize(width, height));
        };
        primaryStage.widthProperty().addListener(stageSizeListener);
        primaryStage.heightProperty().addListener(stageSizeListener);
        PaneUtil.setStageResizeObservers(new ArrayList<>());

        //窗口关闭事件
        primaryStage.setOnCloseRequest((event) -> System.out.print("监听到窗口关闭"));

        //默认显示JSON面板:
        MainPaneController jfc = fxmlLoader.getController();
        jfc.showPane(Constant.MenuIds.JSON_FORMAT);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
