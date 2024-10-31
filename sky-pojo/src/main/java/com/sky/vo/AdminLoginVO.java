package com.sky.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "管理员登录时返回的数据模型")
public class AdminLoginVO implements Serializable {

    @ApiModelProperty("主键值")
    private String adminId;

    @ApiModelProperty("姓名")
    private String adminName;

    @ApiModelProperty("jwt令牌")
    private String token;

}
