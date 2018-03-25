package cn.exrick.manager.controller;

import cn.exrick.common.dto.Order;
import cn.exrick.common.dto.OrderInfo;
import cn.exrick.common.pojo.DataTablesResult;
import cn.exrick.common.pojo.Result;
import cn.exrick.common.service.OrderService;
import cn.exrick.common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Exrickx
 */
@RestController
@Api(description= "订单管理")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/order/list",method = RequestMethod.GET)
    @ApiOperation(value = "获取订单列表")
    public DataTablesResult getOrderList(){

        DataTablesResult result=orderService.getOrderList();
        return result;
    }

    @RequestMapping(value = "/order/count",method = RequestMethod.GET)
    @ApiOperation(value = "获取订单总数")
    public Result<Object> getOrderCount(){

        Long result=orderService.countOrder();
        return new ResultUtil<Object>().setData(result);
    }

    @RequestMapping(value = "/order/del/{id}",method = RequestMethod.DELETE)
    @ApiOperation(value = "删除订单")
    public Result<Object> getUserInfo(@PathVariable String id){

        orderService.deleteOrder(id);
        return new ResultUtil<Object>().setData(null);
    }

    @RequestMapping(value = "/member/orderList",method = RequestMethod.GET)
    @ApiOperation(value = "获得用户所有订单")
    public Result<List<Order>> getOrderList(String userId){

        List<Order> list=orderService.getOrderList(Long.valueOf(userId));
        return new ResultUtil<List<Order>>().setData(list);
    }

    @RequestMapping(value = "/member/orderDetail",method = RequestMethod.GET)
    @ApiOperation(value = "通过id获取订单")
    public Result<Order> getOrder(String orderId){

        Order order=orderService.getOrder(Long.valueOf(orderId));
        return new ResultUtil<Order>().setData(order);
    }

    @RequestMapping(value = "/member/addOrder",method = RequestMethod.POST)
    @ApiOperation(value = "创建订单")
    public Result<Object> addOrder(@RequestBody OrderInfo orderInfo){

        Long orderId=orderService.createOrder(orderInfo);
        return new ResultUtil<Object>().setData(orderId.toString());
    }

    @RequestMapping(value = "/member/cancelOrder",method = RequestMethod.POST)
    @ApiOperation(value = "取消订单")
    public Result<Object> cancelOrder(@RequestBody Order order){

        int result=orderService.cancelOrder(order.getOrderId());
        return new ResultUtil<Object>().setData(result);
    }

    @RequestMapping(value = "/member/delOrder",method = RequestMethod.GET)
    @ApiOperation(value = "删除订单")
    public Result<Object> delOrder(String orderId){

        int result=orderService.delOrder(Long.valueOf(orderId));
        return new ResultUtil<Object>().setData(result);
    }
}
