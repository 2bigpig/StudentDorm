package com.sky.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentVO {

    private String studentId;

    private String studentName;

    private String gender;

    private String birthDay;

    private String grade;

    private String phone;

    private String email;

    private String address;


}
