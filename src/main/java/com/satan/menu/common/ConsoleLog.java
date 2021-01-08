package com.satan.menu.common;

/**
 * @Description: 控制台日志
 * @Date: 2021/1/4 23:24:37下午
 * @Author: MDJ
 */
public class ConsoleLog {

    /**
     * 事件源
     */
    private Object source;

    /**
     * 日志信息
     */
    private String log;

    /**
     * 日志时间
     */
    private String time;

    public ConsoleLog() {
    }

    public ConsoleLog(Object source, String log) {
        this.source = source;
        this.log = log;
    }

    public Object getSource() {
        return source;
    }

    public void setSource(Object source) {
        this.source = source;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
