package com.itlozg.admin.model.request;


public class PublicNumberMsgRequest {

    //接收者id
    String[] receiversId;
    //接收者oa
    String[] loginNames;
    //公众号名称 或 id
    String publicNumber;

    //消息通知
    String title;
    String content;
    String image;
    String url;
    String ddurl;
    String urlIsExternalOpen;//url是否可以外部打开  0：不可以 1：可以
    String infoId;//信息唯一标识

    public String[] getReceiversId() {
        return receiversId;
    }

    public void setReceiversId(String[] receiversId) {
        this.receiversId = receiversId;
    }

    public String[] getLoginNames() {
        return loginNames;
    }

    public void setLoginNames(String[] loginNames) {
        this.loginNames = loginNames;
    }

    public String getPublicNumber() {
        return publicNumber;
    }

    public void setPublicNumber(String publicNumber) {
        this.publicNumber = publicNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlIsExternalOpen() {
        return urlIsExternalOpen;
    }

    public void setUrlIsExternalOpen(String urlIsExternalOpen) {
        this.urlIsExternalOpen = urlIsExternalOpen;
    }

    public String getInfoId() {
        return infoId;
    }

    public void setInfoId(String infoId) {
        this.infoId = infoId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDdurl() {
        return ddurl;
    }

    public void setDdurl(String ddurl) {
        this.ddurl = ddurl;
    }
}
