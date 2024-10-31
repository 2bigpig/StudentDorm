package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.dto.StudentDTO;
import com.sky.dto.StudentPageQueryDTO;
import com.sky.entity.Student;
import com.sky.mapper.StudentMapper;
import com.sky.result.PageResult;
import com.sky.service.StudentService;
import com.sky.vo.StudentVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public void save(StudentDTO studentDTO) {
        Student student = new Student();
        BeanUtils.copyProperties(studentDTO, student);

        studentMapper.save(student);
    }

    @Override
    public void update(StudentDTO studentDTO) {
        Student student = new Student();
        BeanUtils.copyProperties(studentDTO, student);

        studentMapper.update(student);
    }

    @Override
    public StudentVO getById(String studentId) {
        return studentMapper.getById(studentId);
    }

    @Override
    public void delete(List<String> studentIds) {
        studentMapper.delete(studentIds);
    }

    @Override
    public PageResult pageQuery(StudentPageQueryDTO studentPageQueryDTO) {
        PageHelper.startPage(studentPageQueryDTO.getPage(), studentPageQueryDTO.getPageSize());
        Page<StudentVO> page = studentMapper.pageQuery(studentPageQueryDTO);

        long total = page.getTotal();
        List<StudentVO> records = page.getResult();

        return new PageResult(total, records);
    }

    @Override
    public List<StudentVO> getByName(String studentName) {
        return studentMapper.getByName(studentName);
    }
}
