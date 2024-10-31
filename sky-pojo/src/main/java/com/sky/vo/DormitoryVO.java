package com.sky.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DormitoryVO {

    private String dormId;    //宿舍号

    private String dormBuild;     //楼栋

    private Integer dormFloor;     //楼层

    private Integer capacity;    //床位

    private Integer availableBeds;     //闲置床位

    private String gender;


}
