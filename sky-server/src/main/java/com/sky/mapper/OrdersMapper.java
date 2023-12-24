package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.OrdersPageQueryDTO;
import com.sky.entity.Orders;
import com.sky.vo.OrderVO;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface OrdersMapper {
    /**
     *  新增一条数据
     * @param orders
     */
    void insert(Orders orders);

    /**
     *  分页查询历史订单
     * @param ordersPageQueryDTO
     * @return
     */
    Page<Orders> pageQuery(OrdersPageQueryDTO ordersPageQueryDTO);

    /**
     *  查询订单详情   一对多
     * @param id
     * @return
     */
    OrderVO queryOrderAndOrderDetail(Integer id);

    /**
     *  根据订单id查询订单
     * @return
     */
    @Select("select * from orders where id = #{id}")
    Orders getById(Long id);

    /**
     * 用户退款，修改数据
     * @param orders
     */
    void update(Orders orders);
}
