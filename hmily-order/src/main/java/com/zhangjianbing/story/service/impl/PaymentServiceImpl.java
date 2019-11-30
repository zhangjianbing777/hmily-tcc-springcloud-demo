
package com.zhangjianbing.story.service.impl;

import com.zhangjianbing.modul.api.IServiceAccountApi;
import com.zhangjianbing.modul.api.IServiceInventoryApi;
import com.zhangjianbing.modul.dto.AccountDTO;
import com.zhangjianbing.modul.dto.InventoryDTO;
import com.zhangjianbing.story.entity.Order;
import com.zhangjianbing.story.enums.OrderStatusEnum;
import com.zhangjianbing.story.mapper.OrderMapper;
import com.zhangjianbing.story.service.PaymentService;
import org.dromara.hmily.annotation.Hmily;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings("all")
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private IServiceAccountApi accountClient;

    @Autowired
    private IServiceInventoryApi inventoryClient;

    @Override
    @Hmily(confirmMethod = "confirmOrderStatus", cancelMethod = "cancelOrderStatus")
    public void makePayment(Order order) {
        // 支付中
        order.setStatus(OrderStatusEnum.PAYING.getCode());
        // 更新订单状态
        orderMapper.update(order);

        // 进入扣减库存操作
        InventoryDTO inventoryDTO = new InventoryDTO();
        inventoryDTO.setCount(order.getCount());
        inventoryDTO.setProductId(order.getProductId());
        System.out.println("===========调用远程减库存接口==========");
        inventoryClient.decrease(inventoryDTO);

        // 进入扣减资金操作
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setAmount(order.getTotalAmount());
        accountDTO.setUserId(order.getUserId());
        System.out.println("===========调用远程扣减资金接口==========");
        accountClient.payment(accountDTO);
    }

    @Override
    @Hmily(confirmMethod = "confirmOrderStatus", cancelMethod = "cancelOrderStatus")
    public String mockPaymentInventoryWithTryException(Order order) {
        System.out.println("===========执行springcloud  mockPaymentInventoryWithTryException 扣减资金接口==========");
        order.setStatus(OrderStatusEnum.PAYING.getCode());
        orderMapper.update(order);
        //扣除用户余额
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setAmount(order.getTotalAmount());
        accountDTO.setUserId(order.getUserId());
        Boolean payment = accountClient.payment(accountDTO);
        System.out.println("==========远程调用扣减金额接口=====" + payment + "=====");
        InventoryDTO inventoryDTO = new InventoryDTO();
        inventoryDTO.setCount(order.getCount());
        inventoryDTO.setProductId(order.getProductId());
        Boolean inventory = inventoryClient.mockWithTryException(inventoryDTO);
        System.out.println("==========远程调用扣减库存接口=====" + inventory + "=====");
        return "success";
    }

    @Override
    @Hmily(confirmMethod = "confirmOrderStatus", cancelMethod = "cancelOrderStatus")
    public String mockPaymentInventoryWithTryTimeout(Order order) {
        System.out.println("===========执行springcloud  mockPaymentInventoryWithTryTimeout 扣减资金接口==========");
        order.setStatus(OrderStatusEnum.PAYING.getCode());
        orderMapper.update(order);
        //扣除用户余额
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setAmount(order.getTotalAmount());
        accountDTO.setUserId(order.getUserId());
        accountClient.payment(accountDTO);
        InventoryDTO inventoryDTO = new InventoryDTO();
        inventoryDTO.setCount(order.getCount());
        inventoryDTO.setProductId(order.getProductId());
        inventoryClient.mockWithTryTimeout(inventoryDTO);
        return "success";
    }

    public void confirmOrderStatus(Order order) {
        order.setStatus(OrderStatusEnum.PAY_SUCCESS.getCode());
        // 更新订单状态--支付成功
        orderMapper.update(order);
        System.out.println("=========进行订单confirm操作完成================");
    }

    public void cancelOrderStatus(Order order) {
        order.setStatus(OrderStatusEnum.PAY_FAIL.getCode());
        // 更新订单状态--支付失败
        orderMapper.update(order);
        System.out.println("=========进行订单cancel操作完成================");
    }

}
