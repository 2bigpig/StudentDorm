package com.sky.service.impl;

import com.sky.dto.DormitoryDTO;
import com.sky.entity.Dormitory;
import com.sky.mapper.DormDataMapper;
import com.sky.service.DormDataService;
import com.sky.vo.DormitoryVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DormDataServiceImpl implements DormDataService {

    @Autowired
    private DormDataMapper dormDataMapper;

    //新增宿舍
    @Override
    public void save(DormitoryDTO dormitoryDTO) {
        Dormitory dormitory = new Dormitory();
        BeanUtils.copyProperties(dormitoryDTO, dormitory);

        dormDataMapper.save(dormitory);
    }

    /**
     * 根据宿舍id查询宿舍信息
     * @param dormId
     * @return
     */
    @Override
    public DormitoryVO getById(String dormId) {
        DormitoryVO dormitoryVO = dormDataMapper.getById(dormId);
        return dormitoryVO;
    }

    /**
     * 修改宿舍信息
     * @param dormitoryDTO
     */
    @Override
    public void update(DormitoryDTO dormitoryDTO) {
        Dormitory dormitory = new Dormitory();
        BeanUtils.copyProperties(dormitoryDTO, dormitory);
        dormDataMapper.update(dormitory);
    }

    /**
     * 批量删除宿舍
     * @param dormIds
     */
    @Override
    public void delete(List<String> dormIds) {
        dormDataMapper.delete(dormIds);
    }

    @Override
    public List<DormitoryVO> availableBeds() {
        return dormDataMapper.listAvailableBeds();
    }
}
