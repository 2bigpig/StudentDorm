package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.StudentPageQueryDTO;
import com.sky.entity.Student;
import com.sky.enumeration.OperationType;
import com.sky.vo.StudentVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StudentMapper {

    @Insert("insert into student (student_id, student_name, gender, birth_date, grade, phone, email, address, create_time, update_time) " +
            "values (#{studentId}, #{studentName}, #{gender}, #{birthDay}, #{grade}, #{phone}, #{email}, #{address}, #{createTime}, #{updateTime})" )
    @AutoFill(value = OperationType.INSERT)
    void save(Student student);

    @AutoFill(value = OperationType.UPDATE)
    void update(Student student);

    @Select("select * from student where student_id = #{studentId}")
    StudentVO getById(String studentId);

    void delete(List<String> studentIds);

    Page<StudentVO> pageQuery(StudentPageQueryDTO studentPageQueryDTO);

    List<StudentVO> getByName(String studentName);
}
