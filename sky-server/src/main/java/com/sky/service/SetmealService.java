package com.sky.service;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.vo.SetmealVO;

import java.util.List;

public interface SetmealService {
    /**
     * 套餐分页查询
     * @param setmealPageQueryDTO
     * @return
     */
    PageResult page(SetmealPageQueryDTO setmealPageQueryDTO);

    /**
     * 新增套餐
     * @param setmealDTO
     */
    void add(SetmealDTO setmealDTO);

    /**
     * 删除套餐
     * @param ids
     */
    void deleteBatch(List<Long> ids);

    /**
     * 根据id查询套餐，用于修改操作回显
     * @param id
     * @return
     */
    SetmealVO getById(Long id);

    /**
     *  修改套餐
     * @param setmealDTO
     */
    void update(SetmealDTO setmealDTO);

    /**
     *  启售、停售套餐
     * @param status
     * @param id
     */
    void startOrStop(Integer status, Long id);
}
