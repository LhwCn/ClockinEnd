package com.itlozg.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;


import java.io.Serializable;
import java.util.UUID;

public class PK implements Serializable {

    @TableId(value = "ID", type= IdType.ASSIGN_UUID)
    private String id;

    public PK() {

    }

    public PK(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
