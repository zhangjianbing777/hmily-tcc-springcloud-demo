
package com.zhangjianbing.story.service.impl;

import com.zhangjianbing.modul.api.IServiceAccountApi;
import com.zhangjianbing.modul.api.IServiceInventoryApi;
import com.zhangjianbing.modul.dto.AccountDTO;
import com.zhangjianbing.modul.dto.InventoryDTO;
import com.zhangjianbing.story.entity.Order;
import com.zhangjianbing.story.enums.OrderStatusEnum;
import com.zhangjianbing.story.mapper.OrderMapper;
import com.zhangjianbing.story.service.OrderService;
import com.zhangjianbing.story.service.PaymentService;
import org.dromara.hmily.annotation.Hmily;
import org.dromara.hmily.common.utils.IdWorkerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private IServiceInventoryApi serviceInventoryApi;

    @Autowired
    private IServiceAccountApi serviceAccountApi;

    @Override
    @Hmily(confirmMethod = "confirmOrderStatus", cancelMethod = "cancelOrderStatus")
    public String orderPay(Integer count, BigDecimal amount) {
        final Order order = buildOrder(count, amount);
        final int rows = orderMapper.save(order);
        if (rows > 0) {

            // 更新订单状态 --- 支付中
            order.setStatus(OrderStatusEnum.PAYING.getCode());
            orderMapper.update(order);

            // 进入扣减库存操作
//            InventoryDTO inventoryDTO = new InventoryDTO();
//            inventoryDTO.setCount(order.getCount());
//            inventoryDTO.setProductId(order.getProductId());
//            System.out.println("=========== 执行springcloud减库存接口 ==========");
//            serviceInventoryApi.decrease(inventoryDTO);

            // 进入扣减资金操作
//            AccountDTO accountDTO = new AccountDTO();
//            accountDTO.setAmount(order.getTotalAmount());
//            accountDTO.setUserId(order.getUserId());
//            System.out.println("=========== 执行Account项目支付接口 ==========");
//            serviceAccountApi.payment(accountDTO);

            // 更新账户信息
//            System.out.println("=========== 执行Account项目更新账户信息接口 ==========");
//            serviceAccountApi.updateMsg();
        }
        return "success";
    }

    public void confirmOrderStatus(Order order) {
        order.setStatus(OrderStatusEnum.PAY_SUCCESS.getCode());
        orderMapper.update(order);
        System.out.println("=========进行订单confirm操作完成================");
    }

    public void cancelOrderStatus(Order order) {
        order.setStatus(OrderStatusEnum.PAY_FAIL.getCode());
        orderMapper.update(order);
        System.out.println("=========进行订单cancel操作完成================");
    }

    // =======================================================================================

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
