package com.shipping.service.impl;

import com.shipping.entity.ShippingOrder;
import com.shipping.mapper.ShippingOrderMapper;
import com.shipping.service.ShippingOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lixinjian
 * @since 2023-03-08
 */
@Service
public class ShippingOrderServiceImpl extends ServiceImpl<ShippingOrderMapper, ShippingOrder> implements ShippingOrderService {

}
