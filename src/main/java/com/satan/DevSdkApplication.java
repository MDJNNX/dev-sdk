package com.satan;

import com.satan.menu.main.IStageResizeObserver;
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

public class DevSdkApplication extends Application {

    //主界面宽度
    private static final int WIDTH = 800;

    //主界面高度
    private static final int HEIGHT = 600;

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane rootLayout = FXMLLoader.load(getClass().getResource("/fxml/MainPane.fxml"));
        PaneUtil.setStage(primaryStage);
        PaneUtil.setRootLayout(rootLayout);
        primaryStage.getIcons().add(new Image("/images/logo.jpg"));
        primaryStage.setTitle("Mdj-开发工具");
        Scene scene = new Scene(rootLayout, WIDTH, HEIGHT);
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
    }

    public static void main(String[] args) {
        launch(args);
    }
}
