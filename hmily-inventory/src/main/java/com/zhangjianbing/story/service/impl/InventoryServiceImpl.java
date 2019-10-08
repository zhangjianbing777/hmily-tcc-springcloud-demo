
package com.zhangjianbing.story.service.impl;

import com.zhangjianbing.modul.dto.InventoryDTO;
import com.zhangjianbing.story.entity.InventoryDO;
import com.zhangjianbing.story.mapper.InventoryMapper;
import com.zhangjianbing.story.service.InventoryService;
import org.dromara.hmily.annotation.Hmily;
import org.dromara.hmily.common.exception.HmilyRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InventoryServiceImpl implements InventoryService {

    /**
     * logger.
     */
    @Autowired
    private InventoryMapper inventoryMapper;

    /**
     * 扣减库存操作.
     * 这一个tcc接口
     *
     * @param inventoryDTO 库存DTO对象
     * @return true
     */
    @Override
    @Hmily(confirmMethod = "confirmMethod1", cancelMethod = "cancelMethod1")
    public Boolean decrease(InventoryDTO inventoryDTO) {
        System.out.println("==========springcloud调用扣减库存decrease===========");
        inventoryMapper.decrease(inventoryDTO);
        return true;
    }

    public Boolean confirmMethod1(InventoryDTO inventoryDTO) {
        System.out.println("==========Springcloud调用扣减库存确认方法===========");
        final int rows = inventoryMapper.confirm(inventoryDTO);
        return true;
    }

    public Boolean cancelMethod1(InventoryDTO inventoryDTO) {
        System.out.println("==========Springcloud调用扣减库存取消方法===========");
        int rows = inventoryMapper.cancel(inventoryDTO);
        return true;
    }

    // =================================================================================

    /**
     * 获取商品库存信息.
     *
     * @param productId 商品id
     * @return InventoryDO
     */
    @Override
    public InventoryDO findByProductId(String productId) {
        return inventoryMapper.findByProductId(productId);
    }

    @Override
    @Hmily(confirmMethod = "confirmMethod2", cancelMethod = "cancelMethod2")
    @Transactional
    public Boolean mockWithTryException(InventoryDTO inventoryDTO) {
        //这里是模拟异常所以就直接抛出异常了
        throw new HmilyRuntimeException("库存扣减异常！");
    }

    public Boolean confirmMethod2(InventoryDTO inventoryDTO) {
        System.out.println("==========Springcloud调用扣减库存确认方法===========");
        final int rows = inventoryMapper.confirm(inventoryDTO);
        return true;
    }

    public Boolean cancelMethod2(InventoryDTO inventoryDTO) {
        System.out.println("==========Springcloud调用扣减库存取消方法===========");
        int rows = inventoryMapper.cancel(inventoryDTO);
        return true;
    }

    // =================================================================================


    @Override
    @Hmily(confirmMethod = "confirmMethod3", cancelMethod = "cancelMethod3")
    @Transactional(rollbackFor = Exception.class)
    public Boolean mockWithTryTimeout(InventoryDTO inventoryDTO) {
        try {
            //模拟延迟 当前线程暂停10秒
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("==========springcloud调用扣减库存mockWithTryTimeout===========");
        final int decrease = inventoryMapper.decrease(inventoryDTO);
        if (decrease != 1) {
            throw new HmilyRuntimeException("库存不足");
        }
        return true;
    }

    public Boolean confirmMethod3(InventoryDTO inventoryDTO) {
        System.out.println("==========Springcloud调用扣减库存确认方法===========");
        final int rows = inventoryMapper.confirm(inventoryDTO);
        return true;
    }

    public Boolean cancelMethod3(InventoryDTO inventoryDTO) {
        System.out.println("==========Springcloud调用扣减库存取消方法===========");
        int rows = inventoryMapper.cancel(inventoryDTO);
        return true;
    }

    // =================================================================================

    @Transactional(rollbackFor = Exception.class)
    public Boolean confirmMethodTimeout(InventoryDTO inventoryDTO) {
        try {
            //模拟延迟 当前线程暂停11秒
            Thread.sleep(11000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("==========Springcloud调用扣减库存确认方法===========");
        inventoryMapper.decrease(inventoryDTO);
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    public Boolean confirmMethodException(InventoryDTO inventoryDTO) {
        System.out.println("==========Springcloud调用扣减库存确认方法===========");
        final int decrease = inventoryMapper.decrease(inventoryDTO);
        if (decrease != 1) {
            throw new HmilyRuntimeException("库存不足");
        }
        return true;
        // throw new TccRuntimeException("库存扣减确认异常！");
    }

}
