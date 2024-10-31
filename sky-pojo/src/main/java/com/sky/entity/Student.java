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
public class Student {

    private String studentId;

    private String studentName;

    private String gender;

    private String birthDay;

    private String grade;

    private String phone;

    private String email;

    private String address;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}
