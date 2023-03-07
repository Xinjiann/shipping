package com.shipping.controller;


import com.shipping.common.lang.Result;
import com.shipping.entity.ShippingOrder;
import com.shipping.entity.param.ReviewParam;
import com.shipping.entity.param.SubmitOrderParam;
import com.shipping.service.ShippingOrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Objects;

/**
 * @author lixinjian
 * @since 2023-03-08
 */
@RestController
@RequestMapping("/shipping-order")
public class ShippingOrderController {

    @Resource
    private ShippingOrderService shippingOrderService;

    @PostMapping("/submit")
    public Result submit(@RequestBody SubmitOrderParam submitOrderParam) {
        /** 参数校验 */
        if (Objects.isNull(submitOrderParam.getAddress())) {
            return Result.fail("请输入地址");
        }
        if (Objects.isNull(submitOrderParam.getTrackingNumber())) {
            return Result.fail("请输入快递单号");
        }

        ShippingOrder shippingOrder = new ShippingOrder();
        shippingOrder.setAddress(submitOrderParam.getAddress());
        shippingOrder.setTrackingNumber(submitOrderParam.getTrackingNumber());
        shippingOrder.setRemark(submitOrderParam.getRemark());
        shippingOrder.setCreateBy(submitOrderParam.getOpId());
        shippingOrder.setCreateTime(new Date());
        shippingOrder.setFinished(0);
        shippingOrder.setDeleted(0);
        shippingOrderService.save(shippingOrder);
        return Result.success();
    }

    @PostMapping("/review")
    public Result review(@RequestBody ReviewParam reviewParam) {
        /** 参数校验 */
        if (Objects.isNull(reviewParam.getPrice())) {
            return Result.fail("请输入价格");
        }
        if(Objects.isNull(reviewParam.getOrderNumber())) {
            return Result.fail("请输入国际物流单号");
        }

        ShippingOrder shippingOrder = shippingOrderService.getById(reviewParam.getId());
        if (Objects.isNull(shippingOrder)) {
            return Result.fail("该订单不存在");
        }
        if (shippingOrder.getFinished() == 1) {
            return Result.fail("重复操作");
        }
        shippingOrder.setOrderNumber(reviewParam.getOrderNumber());
        shippingOrder.setPrice(reviewParam.getPrice());
        shippingOrder.setFinished(1);
        shippingOrderService.saveOrUpdate(shippingOrder);
        return Result.success();
    }

}
