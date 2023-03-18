package com.shipping.service.impl;

import com.shipping.entity.ShippingUser;
import com.shipping.mapper.ShippingUserMapper;
import com.shipping.service.ShippingUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 物流用户 服务实现类
 * </p>
 *
 * @author lixinjian
 * @since 2023-03-15
 */
@Service
public class ShippingUserServiceImpl extends ServiceImpl<ShippingUserMapper, ShippingUser> implements ShippingUserService {

}
