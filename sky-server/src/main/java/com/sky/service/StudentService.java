package com.sky.service;

import com.sky.dto.StudentDTO;
import com.sky.dto.StudentPageQueryDTO;
import com.sky.entity.Student;
import com.sky.result.PageResult;
import com.sky.vo.StudentVO;

import java.util.List;

public interface StudentService {

    void save(StudentDTO studentDTO);

    void update(StudentDTO studentDTO);

    StudentVO getById(String studentId);

    void delete(List<String> studentIds);

    PageResult pageQuery(StudentPageQueryDTO studentPageQueryDTO);

    List<StudentVO> getByName(String studentName);
}
