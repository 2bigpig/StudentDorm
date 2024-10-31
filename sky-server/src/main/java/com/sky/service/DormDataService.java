package com.sky.service;

import com.sky.dto.DormitoryDTO;
import com.sky.vo.DormitoryVO;

import java.util.List;

public interface DormDataService {
    /**
     * 新增宿舍
     * @param dormitoryDTO
     */
    void save(DormitoryDTO dormitoryDTO);

    /**
     * 按id查询宿舍信息
     *
     * @param dormId
     * @return
     */
    DormitoryVO getById(String dormId);

    /**
     * 编辑宿舍信息
     * @param dormitoryDTO
     */
    void update(DormitoryDTO dormitoryDTO);

    /**
     * 批量删除宿舍
     * @param dormIds
     */
    void delete(List<String> dormIds);

    /**
     * 闲置空床查询
     *
     * @return
     */
    List<DormitoryVO> availableBeds();
}
