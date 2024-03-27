package com.itlozg.admin.model.response;

import java.util.List;

public class SignLonLatInfoResponse {

    /*
    * 打卡范围为圆形的经纬度集合
    * */
    private List<SignLongitudeLatitudeResponse> circleData;

    /*
    * 打卡范围为多边形的经纬度集合
    * */
    private List<List<SignLongitudeLatitudeResponse>> polygonData;

    public List<SignLongitudeLatitudeResponse> getCircleData() {
        return circleData;
    }

    public void setCircleData(List<SignLongitudeLatitudeResponse> circleData) {
        this.circleData = circleData;
    }

    public List<List<SignLongitudeLatitudeResponse>> getPolygonData() {
        return polygonData;
    }

    public void setPolygonData(List<List<SignLongitudeLatitudeResponse>> polygonData) {
        this.polygonData = polygonData;
    }
}
