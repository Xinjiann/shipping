package com.shipping.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shipping.entity.UserFollow;
import com.shipping.mapper.FollowMapper;
import com.shipping.service.FollowService;
import org.springframework.stereotype.Service;

@Service
public class FollowServiceImpl extends ServiceImpl<FollowMapper, UserFollow> implements
    FollowService {

}
