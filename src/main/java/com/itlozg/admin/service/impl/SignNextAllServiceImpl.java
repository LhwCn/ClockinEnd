package com.itlozg.admin.service.impl;

import com.itlozg.admin.entity.SignNextAll;
import com.itlozg.admin.mapper.SignNextAllMapper;
import com.itlozg.admin.service.ISignNextAllService;
import com.itlozg.admin.model.request.SignNextAllRequest;
import com.itlozg.admin.model.response.SignNextAllResponse;
import com.itlozg.admin.util.BeanCopier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class SignNextAllServiceImpl extends BaseServiceImpl<SignNextAllMapper, SignNextAll> implements ISignNextAllService {

    /**
     * 获取打卡明细
     *
     * @param userId
     * @param signDay
     * @return
     */
    @Override
    public List<SignNextAllResponse> getSignDetailByUserDay(String userId, String signDay) {
        List<SignNextAll> list = baseMapper.getSignDetailByUserDay(userId, signDay);
        if (list != null) {
            return BeanCopier.copy(list, SignNextAllResponse.class);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public boolean saveSignInfo(SignNextAllRequest request) {
        SignNextAll signNextAll = BeanCopier.copy(request, SignNextAll.class);
        signNextAll.setCreatedBy(request.getUserId());
        signNextAll.setLastUpdatedBy(request.getUserId());
        signNextAll.setCreationDate(new Date());
        signNextAll.setLastUpdateDate(new Date());
        signNextAll.setDelFlag(0);
        int insert = baseMapper.insert(signNextAll);
        if(insert == 1){
            return true;
        }else {
            return false;
        }
    }
    @Override
    public List<SignNextAllResponse> getSignDetailByNextId(String nextId) {
        List<SignNextAll> list = baseMapper.getSignDetailByNextId(nextId);
        return BeanCopier.copy(list, SignNextAllResponse.class);
    }
}
