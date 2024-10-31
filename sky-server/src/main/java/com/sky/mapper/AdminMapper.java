package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.AdminPageQueryDTO;
import com.sky.entity.Admin;
import com.sky.enumeration.OperationType;
import com.sky.vo.AdminVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminMapper {

    /**
     * 根据用户名查询员工
     * @param username
     * @return
     */
    @Select("select * from admin where admin_name = #{adminName}")
    Admin getByUsername(String username);

    @Insert("insert into admin (admin_id, admin_name, password, email, phone, status) " +
            "values (#{adminId}, #{adminName}, #{password}, #{email}, #{phone}, #{status})")
    void save(Admin admin);

    Page<AdminVO> pageQuery(AdminPageQueryDTO adminPageQueryDTO);

    void update(Admin admin);

    @Select("select * from admin where admin_id = #{id}")
    Admin getById(Long id);
}
