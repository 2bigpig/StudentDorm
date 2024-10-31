package com.sky.service;

import com.sky.dto.AdminDTO;
import com.sky.dto.AdminLoginDTO;
import com.sky.dto.AdminPageQueryDTO;
import com.sky.entity.Admin;
import com.sky.result.PageResult;

public interface AdminService {

    /**
     * 员工登录
     * @param adminLoginDTO
     * @return
     */
    Admin login(AdminLoginDTO adminLoginDTO);

    void save(AdminDTO adminDTO);

    PageResult pageQuery(AdminPageQueryDTO adminPageQueryDTO);

    void startOrStop(Integer status, String id);

    Admin getById(Long id);

    void update(AdminDTO adminDTO);
}
