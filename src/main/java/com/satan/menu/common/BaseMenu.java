/*
 * Copyright (c) 2019. TDH All Rights Reserved
 * Author: 马定健
 * Date: 2020/12/30
 */

package com.satan.menu.common;

import com.satan.util.PaneUtil;
import javafx.scene.control.TableView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Description: 菜单接口类
 * @Authore: 马定健
 * @Date: 2020/12/30
 */
public abstract class BaseMenu {

    /**
     * ui初始化, 将当前菜单加入到主界面中;
     */
    public void initUi() {
    }

    /**
     * 将数据添加到console的table中,并scroll到该条数据
     */
    @SuppressWarnings("unchecked")
    protected void appendToConsole(ConsoleLog consoleLog) {
        TableView logTable = PaneUtil.getConsoleTable();
        consoleLog.setTime(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
        consoleLog.setLog(consoleLog.getTime() + " " + consoleLog.getLog());
        logTable.getItems().add(consoleLog);
        logTable.scrollTo(consoleLog);
    }
}
