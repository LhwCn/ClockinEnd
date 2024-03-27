//package com.itlozg.admin.service.impl;
//
//
//import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
//
//import com.itlozg.admin.entity.Config;
//import com.itlozg.admin.enums.StatusCode;
//import com.itlozg.admin.exception.ApplicationException;
//import com.itlozg.admin.mapper.ConfigMapper;
//import com.itlozg.admin.service.IConfigService;
//import com.itlozg.admin.model.request.ConfigRequest;
//import com.itlozg.admin.model.response.ConfigResponse;
//import com.itlozg.admin.util.BeanCopier;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//
///**
// * Created ysc on 2017-12-15.
// */
//@Service
//public class ConfigServiceImpl extends BaseServiceImpl<ConfigMapper, Config> implements IConfigService {
//    @Autowired
//    private ConfigMapper configMapper;
//
////    @Override
////    public ConfigResponse getById(String id) {
////        Config existing = selectById(id);
////        if (existing != null) {
////            return BeanCopier.copy(existing, ConfigResponse.class);
////        } else {
////            //不存在
////            throw new ApplicationException(StatusCode.NOT_FOUND.getCode(), StatusCode.NOT_FOUND.getMessage());
////        }
////    }
//
//    @Override
//    public ConfigResponse getByType(ConfigRequest request) {
//        Config existing = findByType(request);
//        if (existing != null) {
//            return BeanCopier.copy(existing, ConfigResponse.class);
//        } else {
//            //不存在
//            return null;
//            //throw new ApplicationException(StatusCode.NOT_FOUND.getCode(), StatusCode.NOT_FOUND.getMessage());
//        }
//    }
//
//    @Override
//    public ConfigResponse getByType(int typeVal) {
//        ConfigRequest request = new ConfigRequest();
//        request.setCategory(typeVal);
//        Config existing = findByType(request);
//        if (existing != null) {
//            return BeanCopier.copy(existing, ConfigResponse.class);
//        } else {
//            //不存在
//            throw new ApplicationException(StatusCode.NOT_FOUND.getCode(), StatusCode.NOT_FOUND.getMessage());
//        }
//    }
//
////    @Override
////    public Page<ConfigResponse> getPage(Page<Config> page, ConfigRequest request) {
////        List<Config> config = baseMapper.findConfig(page, request);
////        page.setRecords(config);
////        return convert(page, ConfigResponse.class);
////    }
//
//
////    @Override
////    @Transactional
////    public int deleteData(String id) {
////        return configMapper.deleteData(id, ConfigRequest.DEL_FLAG_DELETE);
////    }
//
////    @Override
////    public  List<Map>  getTableByName() {
////        return baseMapper.getTableByName();
////    }
//
//
////    @Override
////    @Transactional
////    public ConfigResponse save(ConfigRequest request) {
////        Config exist = findByType(request);
////        if (exist == null) {
////            Config config = BeanCopier.copy(request, Config.class);
////            String content = config.getContent().replaceAll("</?[^>]+>", ""); //剔出<p>标签
////            config.setContent(content);
////            UserResponse user = UserUtils.getUser();
////            config.setCreatedBy(user.getId());
////            config.setLastUpdatedBy(user.getId());
////            config.setCreationDate(new Date());
////            config.setLastUpdateDate(new Date());
////            super.insert(config);
////
////            return BeanCopier.copy(config, ConfigResponse.class);
////        } else {
////            //已存在
////            throw new ApplicationException(StatusCode.CONFLICT.getCode(), StatusCode.CONFLICT.getMessage());
////        }
////    }
//
////    @Override
////    @Transactional
////    public ConfigResponse update(ConfigRequest request) {
////        Config existing = selectById(request.getId());
////        if (existing != null) {
////            String content = request.getContent().replaceAll("</?[^>]+>", ""); //剔出<p>标签
////
////            existing.setContent(content);
////            existing.setCategory(request.getCategory());
////            existing.setIntroduction(request.getIntroduction());
////            existing.setIsFixed(request.getIsFixed());
////
////            UserResponse userInfo = UserUtils.getUser();
////            existing.setLastUpdatedBy(userInfo.getId());
////            existing.setLastUpdateDate(new Date());
////
////            super.insertOrUpdate(existing);
////            return BeanCopier.copy(existing, ConfigResponse.class);
////        } else {
////            //不存在
////            throw new ApplicationException(StatusCode.NOT_FOUND.getCode(), StatusCode.NOT_FOUND.getMessage());
////        }
////    }
//
//    public Config findByType(ConfigRequest request) {
//        LambdaQueryWrapper<Config> configLambdaQueryWrapper = new LambdaQueryWrapper<>();
//        configLambdaQueryWrapper.eq(Config::getCategory,request.getCategory())
//                        .eq(Config::getDelFlag,ConfigRequest.DEL_FLAG_NORMAL);
//        return baseMapper.selectOne(configLambdaQueryWrapper);
////        return super.selectOne(new EntityWrapper<Config>().eq("category", request.getCategory()).eq("del_flag", ConfigRequest.DEL_FLAG_NORMAL));
//    }
//
//}
