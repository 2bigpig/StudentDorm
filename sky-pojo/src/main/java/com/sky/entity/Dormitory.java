package com.sky.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Dormitory {

    private String dormId;    //宿舍号

    private String dormBuild;     //楼栋

    private Integer dormFloor;     //楼层

    private Integer capacity;    //床位

    private Integer availableBeds;     //闲置床位

    private String gender;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}
