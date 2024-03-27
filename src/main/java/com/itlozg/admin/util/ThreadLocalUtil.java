package com.itlozg.admin.util;

import com.itlozg.admin.entity.dto.LoginDto;

public class ThreadLocalUtil {
    private static ThreadLocal<LoginDto> userThread = new ThreadLocal<LoginDto>();

    public static void set(LoginDto loginDto){
        userThread.set(loginDto);
    }

    public static LoginDto get(){
        return userThread.get();
    }

    //防止内存泄漏
    public static void remove(){
        userThread.remove();
    }
}
