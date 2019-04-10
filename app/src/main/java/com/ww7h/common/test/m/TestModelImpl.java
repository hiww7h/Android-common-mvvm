package com.ww7h.common.test.m;

/**
 * ================================================
 * 描述：
 * 来源：     Android Studio.
 * 项目名：   Android-common-mvvm
 * 包名：     com.ww7h.common.test.m
 * 创建时间：  2019/4/9 19:49
 *
 * @author ww  Github地址：https://github.com/ww7hcom
 * ================================================
 */
public class TestModelImpl implements TestModel{
    private String contentDescription;

    private String imagePath;

    private String name;

    private String information;

    private String id;


    public void setContentDescription(String contentDescription) {
        this.contentDescription = contentDescription;
    }


    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }


    public void setName(String name) {
        this.name = name;
    }


    public void setInformation(String infomation) {
        this.information = infomation;
    }


    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getContentDescription() {
        return contentDescription;
    }

    @Override
    public String getImagePath() {
        return imagePath;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getInformation() {
        return information;
    }

    @Override
    public String getId() {
        return id;
    }
}
