package com.sky.mapper;

import com.sky.entity.SetmealDish;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SetmealDishMapper {

    /**
     * 根据菜品id查询关联的套餐
     * @param ids
     * @return
     */
    List<Long> getSetmealIdsByDishId(List<Long> ids);

    /**
     *  根据套餐id查询关联的菜品
     * @param id
     * @return
     */
    List<SetmealDish> selectBySetmealId(Long id);

    /**
     * 新增套餐对应的菜品
     * @param setmealDishes
     */
    void insertBatch(List<SetmealDish> setmealDishes);

    /**
     *  批量删除套餐关联的菜品
     * @param ids
     */
    void deleteBatch(List<Long> ids);

    /**
     *  根据套餐id删除套餐关联的菜品
     * @param id
     */
    @Delete("delete from setmeal_dish where setmeal_id = #{id}")
    void deleteBySetmealId(Long id);
}
