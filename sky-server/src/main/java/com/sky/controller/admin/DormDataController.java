package com.sky.controller.admin;

import com.sky.dto.DormitoryDTO;
import com.sky.result.Result;
import com.sky.service.DormDataService;
import com.sky.vo.DormitoryVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/DormData")
@Slf4j
public class DormDataController {

    @Autowired
    private DormDataService dormDataService;

    /**
     * 录入新宿舍
     */
    @PostMapping
    @ApiOperation("新增宿舍")
    public Result save(DormitoryDTO dormitoryDTO) {
        log.info("录入新宿舍信息：{}", dormitoryDTO);

        dormDataService.save(dormitoryDTO);

        return Result.success();
    }

    /**
     * 查询宿舍信息
     * @param dormId
     * @return
     */
    @GetMapping("/{dormId}")
    @ApiOperation("查询宿舍信息")
    public Result<DormitoryVO> getById(@PathVariable String dormId) {
        log.info("查询宿舍信息：{}", dormId);

        DormitoryVO dormitoryVO = dormDataService.getById(dormId);

        return Result.success(dormitoryVO);
    }

    /**
     * 修改宿舍信息
     */
    @PutMapping
    @ApiOperation("修改宿舍信息")
    public Result update(@RequestBody DormitoryDTO dormitoryDTO) {
        log.info("修改宿舍信息：{}", dormitoryDTO);

        dormDataService.update(dormitoryDTO);

        return Result.success();
    }

    /**
     * 批量删除宿舍
     */
    @DeleteMapping
    @ApiOperation("批量删除宿舍")
    public Result delete(@RequestParam List<String> dormIds) {
        log.info("批量删除宿舍：{}", dormIds);

        dormDataService.delete(dormIds);

        return Result.success();
    }

    /**
     *  闲置空床查询
     */
    @GetMapping("/availableBeds")
    @ApiOperation("闲置空床查询")
    public Result<List<DormitoryVO>> availableBeds() {
        log.info("闲置空床查询");

        return Result.success(dormDataService.availableBeds());
    }
}
