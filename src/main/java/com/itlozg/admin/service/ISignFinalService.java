package com.itlozg.admin.service;


import com.itlozg.admin.entity.SignFinal;

public interface ISignFinalService extends IBaseService<SignFinal> {
    public SignFinal getByUserId(String userId);
}
