package com.itlozg.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itlozg.admin.entity.SignGroup;

import java.util.List;

public interface ISignGroupService extends IService<SignGroup> {
    List<SignGroup> getList();
    String getMsgNos(String dateNow);
}
