package com.sky.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminVO implements Serializable {

    private String adminId;

    private String adminName;

    private String password;

    private String email;

    private String phone;

}
