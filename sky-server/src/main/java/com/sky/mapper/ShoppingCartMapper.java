package com.sky.mapper;

import com.sky.entity.ShoppingCart;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface ShoppingCartMapper {

    /**
     *  条件查询当前菜品是否在购物车中
     * @param shoppingCart
     * @return
     */
     List<ShoppingCart> list(ShoppingCart shoppingCart);

    /**
     * 根据购物车id修改菜品数量
     * @param shoppingCart
     */
     @Update("update shopping_cart set number = #{number} where id = #{id}")
    void updateNumberById(ShoppingCart shoppingCart);

    /**
     * 新增购物车中的菜品
     * @param shoppingCart
     */
    @Insert("insert into shopping_cart(name, user_id, dish_id, setmeal_id, dish_flavor, " +
            "number, amount, image, create_time) values (#{name},#{userId},#{dishId},#{setmealId}" +
            ",#{dishFlavor},#{number},#{amount},#{image},#{createTime})")
    void insert(ShoppingCart shoppingCart);

    /**
     *  根据userID删除购物车数据
     * @param userId
     */
    @Delete("delete from shopping_cart where user_id = #{userId}")
    void deleteByUserId(Long userId);

    /**
     *  根据购物车id删除商品
     * @param id
     */
    @Delete("delete from shopping_cart where id = #{id}")
    void deleteById(Long id);
}
