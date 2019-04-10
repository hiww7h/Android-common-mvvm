package com.ww7h.common.test.m;

/**
 * ================================================
 * 描述：
 * 来源：     Android Studio.
 * 项目名：   Android-common-mvvm
 * 包名：     com.ww7h.common.test.m
 * 创建时间：  2019/4/9 19:16
 *
 * @author ww  Github地址：https://github.com/ww7hcom
 * ================================================
 */
public interface TestModel {

    /**
     * 获取图片说明文字
     * @return 图片说明文字
     */
    String getContentDescription();

    /**
     * 获取图片地址
     * @return 图片地址
     */
    String getImagePath();

    /**
     * 获取test名称
     * @return test名称
     */
    String getName();

    /**
     * 获取test 说明
     * @return test 说明
     */
    String getInformation();

    /**
     * 获取id
     * @return id
     */
    String getId();

}
