package com.itlozg.admin.model.response;


/**
 * @create 2017-12-15
 */
public class ConfigResponse extends BaseResponse {

    /**
     * 类型
     */
    private Integer category;


    /**
     * 内容
     */
    private String content;

    /**
     * 简介
     */
    private String introduction;

    /**
     * 是否固定， 0默认为不固定，1=固定；固定就不能再去修改
     */
    private Integer isFixed;

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Integer getIsFixed() {
        return isFixed;
    }

    public void setIsFixed(Integer isFixed) {
        this.isFixed = isFixed;
    }
}
