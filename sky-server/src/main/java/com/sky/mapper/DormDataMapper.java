package com.sky.mapper;

import com.sky.annotation.AutoFill;
import com.sky.entity.Dormitory;
import com.sky.enumeration.OperationType;
import com.sky.vo.DormitoryVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface DormDataMapper {
    @Insert("insert into dormitory (dorm_id, dorm_build, dorm_floor, capacity, available_beds, gender) " +
            "values (#{dormId}, #{dormBuild}, #{dormFloor}, #{capacity}, #{availableBeds}, #{gender})")
    void save(Dormitory dormitory);

    @Select("select * from dormitory where dorm_id = #{dormId}")
    DormitoryVO getById(String dormId);

    void update(Dormitory dormitory);

    void delete(List<String> dormIds);

    List<DormitoryVO> listAvailableBeds();
}
