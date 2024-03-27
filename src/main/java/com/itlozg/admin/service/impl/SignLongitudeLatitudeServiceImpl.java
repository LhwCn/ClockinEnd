package com.itlozg.admin.service.impl;

import com.itlozg.admin.entity.SignLongitudeLatitude;
import com.itlozg.admin.mapper.SignLongitudeLatitudeMapper;
import com.itlozg.admin.service.ISignLongitudeLatitudeService;
import com.itlozg.admin.model.response.SignLonLatInfoResponse;
import com.itlozg.admin.model.response.SignLongitudeLatitudeResponse;
import com.itlozg.admin.util.BeanCopier;
import com.itlozg.admin.util.ListGroupBy;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class SignLongitudeLatitudeServiceImpl extends BaseServiceImpl<SignLongitudeLatitudeMapper, SignLongitudeLatitude> implements ISignLongitudeLatitudeService {
    /**
     * 查询电子围栏 （后台遮盖物）
     */
    @Override
    public SignLonLatInfoResponse findFenceByRuleIdGroupBy(String ruleId) {
        SignLonLatInfoResponse lonlatInfo = new SignLonLatInfoResponse();
        //获取到对应规则的经纬度集合
        List<SignLongitudeLatitude> signLongitudeLatitudes = baseMapper.findFenceByRuleId(ruleId);
        //获取经纬度集合的第一个结果的组id
        if (StringUtils.isNotBlank(signLongitudeLatitudes.get(0).getGroupId())) {
            lonlatInfo = groupCovering(signLongitudeLatitudes);
        } else {
            //将多个经纬度集合封装到list中返回给前端
            List<List<SignLongitudeLatitudeResponse>> polygonData = new ArrayList<>();
            List<SignLongitudeLatitudeResponse> responses = BeanCopier.copy(signLongitudeLatitudes, SignLongitudeLatitudeResponse.class);
            polygonData.add(responses);
            lonlatInfo.setPolygonData(polygonData);
        }
        return lonlatInfo;
    }

    /**
     * 分组现有遮盖物
     *
     * @param signLongitudeLatitudes
     * @return
     */
    @Override
    public SignLonLatInfoResponse groupCovering(List<SignLongitudeLatitude> signLongitudeLatitudes) {
        SignLonLatInfoResponse lonlatInfo = new SignLonLatInfoResponse();
        if (signLongitudeLatitudes.size() != 0) {
            List<SignLongitudeLatitudeResponse> list = BeanCopier.copy(signLongitudeLatitudes, SignLongitudeLatitudeResponse.class);
            //这里是一个根据比较器来进行分组，目前只能看出这么多
            List<List<SignLongitudeLatitudeResponse>> result = ListGroupBy.dividerList(list, new Comparator<SignLongitudeLatitudeResponse>() {
                @Override
                public int compare(SignLongitudeLatitudeResponse response1, SignLongitudeLatitudeResponse response2) {
                    // groupId 分组
                    return response1.getGroupId().equals(response2.getGroupId()) ? 0 : -1;
                }
            });
            List<SignLongitudeLatitudeResponse> circle = new ArrayList<>();//圆形
            List<List<SignLongitudeLatitudeResponse>> polygon = new ArrayList<>();//多边形
            //根据结果集 区分是圆形还是多边形
            for (int i = 0; i < result.size(); i++) {
                List<SignLongitudeLatitudeResponse> itemArray = result.get(i);
                if (itemArray.size() > 0) {
                    if (StringUtils.isNotBlank(itemArray.get(0).getRadius()) && itemArray.size() < 2) {//圆形数据
                        circle.addAll(itemArray);
                    } else {//多边形数据
                        polygon.add(itemArray);
                    }
                }
            }
            lonlatInfo.setCircleData(circle);
            lonlatInfo.setPolygonData(polygon);
        }
        return lonlatInfo;
    }


}
