package com.itlozg.admin.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itlozg.admin.util.BeanCopier;

import java.util.List;

public abstract class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> {

    public Page convert(Page source, Class destinationClass) {
        List result = BeanCopier.copy(source.getRecords(), destinationClass);
        source.setRecords(result);
        return source;
    }
}
