package com.sky.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class AdminDTO implements Serializable {

    private String adminId;

    private String adminName;

    private String password;

    private String email;

    private String phone;

}
