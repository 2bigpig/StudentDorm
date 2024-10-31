package com.sky.dto;

import io.swagger.models.auth.In;
import lombok.Data;

@Data
public class DormitoryDTO {

    private String dormId;    //宿舍号

    private String dormBuild;     //楼栋

    private Integer dormFloor;     //楼层

    private Integer capacity;    //床位

    private Integer availableBeds;     //闲置床位

    private String gender;


}
