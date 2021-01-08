package com.satan.menu.main;

/**
 * @Description: IStageResizeObserver
 * @Authore: 马定健
 * @Date: 2021/1/7
 */
@FunctionalInterface
public interface IStageResizeObserver {

    /**
     * 调整大小
     */
    void resize(double width, double height);
}
