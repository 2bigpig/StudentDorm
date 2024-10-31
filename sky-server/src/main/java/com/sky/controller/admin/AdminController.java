package com.sky.controller.admin;

import com.sky.constant.JwtClaimsConstant;
import com.sky.dto.AdminDTO;
import com.sky.dto.AdminLoginDTO;
import com.sky.dto.AdminPageQueryDTO;
import com.sky.entity.Admin;
import com.sky.properties.JwtProperties;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.AdminService;
import com.sky.utils.JwtUtil;
import com.sky.vo.AdminLoginVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 员工管理
 */
@RestController
@RequestMapping("/admin/Admin")
@Slf4j
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 登录
     *
     * @param adminLoginDTO
     * @return
     */
    @PostMapping("/login")
    @ApiOperation("管理员登录")
    public Result<AdminLoginVO> login(@RequestBody AdminLoginDTO adminLoginDTO) {
        log.info("管理员登录：{}", adminLoginDTO);

        Admin admin = adminService.login(adminLoginDTO);

        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, admin.getAdminId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);


        AdminLoginVO adminLoginVO = AdminLoginVO.builder()
                .adminId(admin.getAdminId())
                .adminName(admin.getAdminName())
                .token(token)
                .build();


        return Result.success(adminLoginVO);
    }



    /**
     * 退出
     *
     * @return
     */
    @PostMapping("/logout")
    @ApiOperation("管理员退出")
    public Result<String> logout() {
        return Result.success();
    }

    /**
     * 新增管理员
     */
    @PostMapping
    @ApiOperation("新增管理员")
    public Result save(@RequestBody AdminDTO adminDTO) {
        log.info("新增管理员：{}", adminDTO);
        adminService.save(adminDTO);
        return Result.success();
    }

    /**
     * 员工分页查询
     */
    @GetMapping("/page")
    @ApiOperation("员工分页查询")
    public Result<PageResult> page(AdminPageQueryDTO adminPageQueryDTO) {
        log.info("员工分页查询，参数：", adminPageQueryDTO);
        PageResult pageResult = adminService.pageQuery(adminPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 启用、禁用管理员账号
     */
    @PostMapping("/status/{status}")
    @ApiOperation("启用、禁用管理员账号")
    public Result startOrStop(@PathVariable Integer status, String id) {      //id 怎么来的    数据类型long改String
        log.info("启用、禁用管理员账号：{},{}", status, id);
        adminService.startOrStop(status, id);
        return Result.success();
    }

    /**
     * 按adminId查询管理员
     */
    @GetMapping("/{id}")
    @ApiOperation("按adminId查询管理员")
    public Result<Admin> getById(@PathVariable Long id) {
        log.info("按adminId查询管理员：{}", id);
        Admin admin = adminService.getById(id);
        return Result.success(admin);
    }

    /**
     * 编辑管理员
     */
    @PutMapping
    @ApiOperation("编辑管理员")
    public Result update(@RequestBody AdminDTO adminDTO) {
        log.info("编辑管理员：{}", adminDTO);
        adminService.update(adminDTO);
        return Result.success();
    }


}
