package com.sky.controller.admin;

import com.sky.dto.StudentDTO;
import com.sky.dto.StudentPageQueryDTO;
import com.sky.entity.Student;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.StudentService;
import com.sky.vo.StudentVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/StudentData")
@Slf4j
public class StudentDataController {

    @Autowired
    private StudentService studentService;

    /**
     * 添加学生数据
     */
    @PostMapping
    @ApiOperation("新增学生信息")
    public Result save(@RequestBody StudentDTO studentDTO) {
        log.info("新增学生信息：{}", studentDTO);
        studentService.save(studentDTO);
        return Result.success();
    }

    @PutMapping
    @ApiOperation("编辑学生信息")
    public Result update(@RequestBody StudentDTO studentDTO) {
        log.info("编辑学生信息：{}", studentDTO);
        studentService.update(studentDTO);
        return Result.success();
    }

    @DeleteMapping
    @ApiOperation("批量删除学生信息")
    public Result delete(@RequestParam List<String> studentIds) {
        log.info("删除学生信息：{}", studentIds);
        studentService.delete(studentIds);

        return Result.success();
    }

    @GetMapping("/page")
    @ApiOperation("分页查询学生信息")
    public Result page(StudentPageQueryDTO studentPageQueryDTO) {
        log.info("分页查询学生信息：{}", studentPageQueryDTO);
        PageResult pageResult = studentService.pageQuery(studentPageQueryDTO);
        return Result.success(pageResult);
    }

    @GetMapping("studentId/{studentId}")
    @ApiOperation("按id查询学生信息")
    public Result<StudentVO> getById(@PathVariable String studentId) {
        log.info("按id查询学生信息：{}", studentId);
        return Result.success(studentService.getById(studentId));
    }

    @GetMapping("studentName/{studentName}")
    @ApiOperation("按姓名查询学生信息")
    public Result<List<StudentVO>> getByName(@PathVariable String studentName) {
        log.info("按姓名查询学生信息：{}", studentName);
        return Result.success(studentService.getByName(studentName));
    }

}
