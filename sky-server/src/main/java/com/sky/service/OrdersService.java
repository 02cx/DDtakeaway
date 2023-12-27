package com.sky.service;

import com.sky.dto.OrdersPageQueryDTO;
import com.sky.dto.OrdersSubmitDTO;
import com.sky.result.PageResult;
import com.sky.vo.OrderSubmitVO;
import com.sky.vo.OrderVO;

public interface OrdersService {
    /**
     * 用户下单
     * @param ordersSubmitDTO
     * @return
     */
    OrderSubmitVO submitOrder(OrdersSubmitDTO ordersSubmitDTO);

    /**
     *  分页查询历史订单
     * @return
     */
    PageResult pageQueryHistoryOrders(int page, int pageSize, Integer status);


    /**
     *  查询订单详情
     * @param id
     * @return
     */
    OrderVO queryOrderDetail(Long id);

    /**
     *  用户取消订单
     * @param id
     */
    void userCancelById(Long id) throws Exception;

    /**
     * 再来一单
     *
     * @param id
     */
    void repetition(Long id);

    /**
     * 用户催单
     * @param id
     */
    void reminder(Long id);
}
