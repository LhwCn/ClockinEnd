package com.itlozg.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.itlozg.admin.entity.SignLegwork;
import com.itlozg.admin.mapper.SignLegworkMapper;
import com.itlozg.admin.service.ISignLegworkService;
import com.itlozg.admin.model.request.SignLegworkRequest;
import com.itlozg.admin.model.response.SignLegworkResponse;
import com.itlozg.admin.util.BeanCopier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class SignLegworkServiceImpl extends BaseServiceImpl<SignLegworkMapper, SignLegwork> implements ISignLegworkService {

    /**
     * 获取打卡明细
     *
     * @param userId
     * @param signDay
     * @return
     */
    @Override
    public List<SignLegworkResponse> getListByUserDay(String userId, String signDay) {
        List<SignLegwork> list = baseMapper.getListByUserDay(userId, signDay);
        return BeanCopier.copy(list, SignLegworkResponse.class);
    }

    @Override
    @Transactional
    public boolean saveSignInfo(SignLegworkRequest request) {
        SignLegwork signLegwork = BeanCopier.copy(request, SignLegwork.class);
        signLegwork.setCreatedBy(request.getUserId());
        signLegwork.setLastUpdatedBy(request.getUserId());
        signLegwork.setCreationDate(new Date());
        signLegwork.setLastUpdateDate(new Date());
        signLegwork.setDelFlag(0);
        signLegwork.setGroupId(request.getGroupId());
        int insert = baseMapper.insert(signLegwork);
        if(insert == 1){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public List<SignLegwork> getSignInfoAllLastday(String groupId) {
        return this.list(new LambdaQueryWrapper<SignLegwork>()
                .eq(SignLegwork::getGroupId, groupId)
                .orderByDesc(SignLegwork::getSignTime));
    }
}
