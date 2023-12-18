package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.enumeration.OperationType;
import com.sky.vo.DishVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper
public interface DishMapper {

    /**
     * 根据分类id查询详细菜品
     * @param categoryId
     * @return
     */
    @Select("select count(*) from dish where category_id = #{categoryId}")
    Integer countByCategoryId(Long categoryId);

    /**
     * 添加菜品
     * @param dish
     */
    @AutoFill(OperationType.INSERT)
    void insert(Dish dish);

    /**
     *  菜品分页查询
     * @param dishPageQueryDTO
     * @return
     */
    Page<DishVO> selectByPage(DishPageQueryDTO dishPageQueryDTO);

    /**
     * 根据菜品id查询菜品
     * @return
     */
    @Select("select * from dish where id = #{id}")
    Dish getById(Long id);

    /**
     * 根据菜品id删除菜品
     * @param id
     */
    @Delete("delete from dish where id = #{id}")
    void deleteById(Long id);

    /**
     *  修改菜品表
     * @param dish
     */
    void update(Dish dish);

    /**
     *  根据分类id查询菜品
     * @param categoryId
     * @return
     */
    @Select("select * from dish where category_id = #{categoryId}")
    List<Dish> getByCategoryId(Long categoryId);

    /**
     * 根据套餐id查询套餐关联的菜品详细信息
     * @param id
     * @return
     */
    List<Dish> selectBySetmealId(Long id);

    /**
     *  条件查询菜品和口味
     * @param dish
     * @return
     */
    List<Dish> list(Dish dish);
}
