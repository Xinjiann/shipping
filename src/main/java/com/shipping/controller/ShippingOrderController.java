package com.shipping.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shipping.common.constant.ShippingOrderStatus;
import com.shipping.common.lang.Result;
import com.shipping.entity.ShippingOrder;
import com.shipping.entity.param.QueryOrderForPageParam;
import com.shipping.entity.param.ReviewParam;
import com.shipping.entity.param.SubmitOrderParam;
import com.shipping.service.ShippingOrderService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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
        shippingOrder.setOpenid(submitOrderParam.getOpenid());
        shippingOrder.setCreateTime(new Date());
        shippingOrder.setStatus(ShippingOrderStatus.CREATED);
        shippingOrder.setDeleted(0);
        shippingOrderService.save(shippingOrder);
        return Result.success();
    }

    @PostMapping("/list")
    public Result list(@RequestBody QueryOrderForPageParam queryOrderForPageParam) {
        /** 参数校验 */
        if (Objects.isNull(queryOrderForPageParam.getPageParam())) {
            log.error("分页部件为空");
            return Result.fail();
        }
        if (Objects.isNull(queryOrderForPageParam.getOpenid())) {
            log.error("openid为空");
            return Result.fail();
        }

        QueryWrapper<ShippingOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("openid", queryOrderForPageParam.getOpenid());
        queryWrapper.eq("status", queryOrderForPageParam.getStatus());
        queryWrapper.eq("deleted", 0);
        Page<ShippingOrder> page = new Page(queryOrderForPageParam.getPageParam().getCurrent(), queryOrderForPageParam.getPageParam().getSize());
        IPage<ShippingOrder> res = shippingOrderService.page(page, queryWrapper);
        return Result.success(res);
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
        if (shippingOrder.getStatus() == 1) {
            return Result.fail("重复操作");
        }
        shippingOrder.setOrderNumber(reviewParam.getOrderNumber());
        shippingOrder.setPrice(reviewParam.getPrice());
        shippingOrder.setStatus(ShippingOrderStatus.WAITING_FOR_PAID);
        shippingOrderService.saveOrUpdate(shippingOrder);
        return Result.success();
    }

}
