package com.shipping.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shipping.entity.UserLikes;
import com.shipping.mapper.LikeMapper;
import com.shipping.service.LikeService;
import org.springframework.stereotype.Service;

@Service
public class LikeServiceImpl extends ServiceImpl<LikeMapper, UserLikes> implements LikeService {

}
