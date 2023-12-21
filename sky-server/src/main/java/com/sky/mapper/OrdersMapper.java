package com.sky.mapper;

import com.sky.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrdersMapper {
    /**
     *  新增一条数据
     * @param orders
     */
    void insert(Orders orders);
}
