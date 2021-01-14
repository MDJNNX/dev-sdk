/*
 * Copyright (c) 2019. TDH All Rights Reserved
 * Author: 马定健
 * Date: 2021/1/14
 */

package com.satan.menu.setting;

import com.satan.menu.common.BaseMenu;
import com.satan.menu.main.IStageResizeObserver;
import com.satan.util.PaneUtil;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextFlow;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;

/**
 * @Description: 设置菜单
 * @Authore: 马定健
 * @Date: 2021/1/14
 */
public class SettingController extends BaseMenu implements IStageResizeObserver {

    private CodeArea codeArea;

    /**
     * 设置主面板
     */
    @FXML
    private HBox settingPane;

    /**
     * 初始化
     */
    @FXML
    private void initialize() {
        PaneUtil.getStatusMenuPane().setVisible(false);
        PaneUtil.getStageResizeObservers().add(this);
        postProcess1();
    }

    private void postProcess1() {
        TextFlow textFlow = new TextFlow();
//        Text t1 = new Text("hello");
        Image btnImg = new Image("config/images/console.png");
        ImageView imageView = new ImageView(btnImg);
        imageView.setOnKeyPressed((event) -> {
            System.out.println("hello imageView");
        });
//        Text t2 = new Text("world");
//        textFlow.getChildren().addAll(t1, imageView, t2);

        CodeArea t1 = new CodeArea();
        t1.setParagraphGraphicFactory(LineNumberFactory.get(t1));
        t1.replaceText(0, 0, "hello");
        t1.insertText(5, "---");

        CodeArea t2 = new CodeArea();
        t2.setParagraphGraphicFactory(LineNumberFactory.get(t2));
        t2.replaceText(0, 0, "world");

        textFlow.getChildren().addAll(t1, imageView, t2);
        settingPane.getChildren().add(textFlow);
    }

    private void postProcess() {
        codeArea = new CodeArea();
        codeArea.setParagraphGraphicFactory(LineNumberFactory.get(codeArea));
        String sampleCode = String.join("\n", new String[] {
                "package com.example;",
                "",
                "import java.util.*;",
                "",
                "public class Foo extends Bar implements Baz {",
                "",
                "    /*",
                "     * multi-line comment",
                "     */",
                "    public static void main(String[] args) {",
                "        // single-line comment",
                "        for(String arg: args) {",
                "            if(arg.length() != 0)",
                "                System.out.println(arg);",
                "            else",
                "                System.err.println(\"Warning: empty string as argument\");",
                "        }",
                "    }",
                "",
                "}"
        });
        codeArea.replaceText(0, 0, sampleCode);
        settingPane.getChildren().add(codeArea);
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
        codeArea.setPrefWidth(width - 5);
        codeArea.setPrefHeight(height - 5);
    }
}
