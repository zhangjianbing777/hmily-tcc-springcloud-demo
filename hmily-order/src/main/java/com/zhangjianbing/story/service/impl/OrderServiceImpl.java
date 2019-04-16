
package com.zhangjianbing.story.service.impl;

import com.zhangjianbing.story.entity.Order;
import com.zhangjianbing.story.enums.OrderStatusEnum;
import com.zhangjianbing.story.mapper.OrderMapper;
import com.zhangjianbing.story.service.OrderService;
import com.zhangjianbing.story.service.PaymentService;
import org.dromara.hmily.common.utils.IdWorkerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;


@Service
@SuppressWarnings("all")
public class OrderServiceImpl implements OrderService {

    /**
     * logger.
     */

    private final OrderMapper orderMapper;

    private final PaymentService paymentService;

    @Autowired(required = false)
    public OrderServiceImpl(OrderMapper orderMapper, PaymentService paymentService) {
        this.orderMapper = orderMapper;
        this.paymentService = paymentService;
    }

    @Override
    public String orderPay(Integer count, BigDecimal amount) {
        final Order order = buildOrder(count, amount);
        final int rows = orderMapper.save(order);

        if (rows > 0) {
            paymentService.makePayment(order);
        }
        return "success";
    }

    /**
     * 模拟在订单支付操作中，库存在try阶段中的库存异常
     *
     * @param count  购买数量
     * @param amount 支付金额
     * @return string
     */
    @Override
    public String mockInventoryWithTryException(Integer count, BigDecimal amount) {
        final Order order = buildOrder(count, amount);
        final int rows = orderMapper.save(order);

        if (rows > 0) {
            paymentService.mockPaymentInventoryWithTryException(order);
        }


        return "success";
    }

    /**
     * 模拟在订单支付操作中，库存在try阶段中的timeout
     *
     * @param count  购买数量
     * @param amount 支付金额
     * @return string
     */
    @Override
    public String mockInventoryWithTryTimeout(Integer count, BigDecimal amount) {
        final Order order = buildOrder(count, amount);
        final int rows = orderMapper.save(order);

        if (rows > 0) {
            paymentService.mockPaymentInventoryWithTryTimeout(order);
        }


        return "success";
    }


    @Override
    public void updateOrderStatus(Order order) {
        orderMapper.update(order);
    }

    private Order buildOrder(Integer count, BigDecimal amount) {
        System.out.println("构建订单对象");
        Order order = new Order();
        order.setCreateTime(new Date());
        order.setNumber(IdWorkerUtils.getInstance().buildPartNumber());
        //demo中的表里只有商品id为 1的数据
        order.setProductId("1");
        order.setStatus(OrderStatusEnum.NOT_PAY.getCode());
        order.setTotalAmount(amount);
        order.setCount(count);
        //demo中 表里面存的用户id为10000
        order.setUserId("10000");
        return order;
    }
}
