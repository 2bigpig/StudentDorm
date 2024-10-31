package com.sky.dto;

import lombok.Data;

@Data
public class AdminPageQueryDTO{
    //管理员姓名
    private String name;

    //页码
    private int page;

    //每页显示记录数
    private int pageSize;
}
