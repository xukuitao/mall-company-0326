package cn.exrick.manager.service;


import cn.exrick.manager.dto.front.Order;
import cn.exrick.manager.dto.front.OrderInfo;
import cn.exrick.manager.pojo.DataTablesResult;

import java.util.List;

/**
 * @author Exrickx
 */
public interface OrderService {

    /**
     * 获得订单列表
     * @return
     */
    DataTablesResult getOrderList();

    /**
     * 统计订单数
     * @return
     */
    Long countOrder();

    /**
     * 删除订单
     * @param id
     * @return
     */
    int deleteOrder(String id);

    /**
     * 定时取消订单
     */
    int cancelOrder();

    List<Order> getOrderList(Long userId);

    Order getOrder(Long orderId);

    int cancelOrder(Long orderId);

    Long createOrder(OrderInfo orderInfo);

    int delOrder(Long orderId);
}
