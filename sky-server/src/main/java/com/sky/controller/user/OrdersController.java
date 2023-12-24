package com.sky.controller.user;

import com.sky.dto.OrdersPageQueryDTO;
import com.sky.dto.OrdersSubmitDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.OrdersService;
import com.sky.vo.OrderSubmitVO;
import com.sky.vo.OrderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController("userOderController")
@Api(tags = "C端-订单接口")
@RequestMapping("/user/order")
@Slf4j
public class OrdersController {

    @Resource
    private OrdersService ordersService;

    @PostMapping("/submit")
    @ApiOperation("用户下单")
    public Result<OrderSubmitVO> submit(@RequestBody OrdersSubmitDTO ordersSubmitDTO){
        OrderSubmitVO orderSubmitVO = ordersService.submitOrder(ordersSubmitDTO);
        return Result.success(orderSubmitVO);
    }

    /**
     * 分页查询历史订单
     * @return
     */
    @GetMapping("/historyOrders")
    @ApiOperation("查询历史订单")
    public Result<PageResult> historyOrderes(int page, int pageSize, Integer status){
        PageResult pageResult = ordersService.pageQueryHistoryOrders(page,pageSize,status);
        return Result.success(pageResult);
    }

    /**
     * 查询订单详情
     * @param id
     * @return
     */
    @GetMapping("/orderDetail/{id}")
    @ApiOperation("/查询订单详情")
    public Result<OrderVO> orderDetail(@PathVariable Long id){
        OrderVO orderVO = ordersService.queryOrderDetail(id);
        return Result.success(orderVO);
    }
}
