package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.constant.StatusConstant;
import com.sky.dto.AdminDTO;
import com.sky.dto.AdminLoginDTO;
import com.sky.dto.AdminPageQueryDTO;
import com.sky.entity.Admin;
import com.sky.exception.AccountLockedException;
import com.sky.exception.AccountNotFoundException;
import com.sky.exception.PasswordErrorException;
import com.sky.mapper.AdminMapper;
import com.sky.result.PageResult;
import com.sky.service.AdminService;
import com.sky.vo.AdminVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    /**
     * 员工登录
     *
     * @param adminLoginDTO
     * @return
     */
    public Admin login(AdminLoginDTO adminLoginDTO) {
        String adminname = adminLoginDTO.getAdminname();
        String password = adminLoginDTO.getPassword();

        //1、根据用户名查询数据库中的数据
        Admin admin = adminMapper.getByUsername(adminname);

        //2、处理各种异常情况（用户名不存在、密码不对、账号被锁定）
        if (admin == null) {
            //账号不存在
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        //密码比对
        // TODO 后期需要进行md5加密，然后再进行比对
        if (!password.equals(admin.getPassword())) {
            //密码错误
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        if (admin.getStatus() == StatusConstant.DISABLE) {
            //账号被锁定
            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
        }

        //3、返回实体对象
        return admin;
    }

    /**
     * 新增管理员
     * @param adminDTO
     */
    @Override
    public void save(AdminDTO adminDTO) {
        System.out.println("当前线程id:" + Thread.currentThread().getId());
        Admin admin = new Admin();

        //对象属性拷贝
        BeanUtils.copyProperties(adminDTO, admin);

        //设置账号状态
        admin.setStatus(StatusConstant.ENABLE);

        admin.setCreateTime(LocalDateTime.now());
        admin.setUpdateTime(LocalDateTime.now());

        //密码加密
        admin.setPassword(DigestUtils.md5DigestAsHex(admin.getPassword().getBytes()));

        adminMapper.save(admin);

    }

    /**
     * 管理员分页查询
     * @param adminPageQueryDTO
     * @return
     */
    @Override
    public PageResult pageQuery(AdminPageQueryDTO adminPageQueryDTO) {
        PageHelper.startPage(adminPageQueryDTO.getPage(), adminPageQueryDTO.getPageSize());
        Page<AdminVO> page = adminMapper.pageQuery(adminPageQueryDTO);

        long total = page.getTotal();
        List<AdminVO> records = page.getResult();

        return new PageResult(total, records);
    }

    /**
     * 启用禁用管理员账号
     * @param status
     * @param id
     */
    @Override
    public void startOrStop(Integer status, String id) {
        Admin admin = Admin.builder()
                .status(status)
                .adminId(id)
                .build();
        adminMapper.update(admin);
    }

    @Override
    public Admin getById(Long id) {
        Admin admin = adminMapper.getById(id);
        return admin;
    }

    @Override
    public void update(AdminDTO adminDTO) {
        Admin admin = new Admin();
        BeanUtils.copyProperties(adminDTO, admin);
        adminMapper.update(admin);
    }


}
