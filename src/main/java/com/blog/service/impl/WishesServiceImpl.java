package com.blog.service.impl;

import com.blog.entity.Wishes;
import com.blog.mapper.WishesMapper;
import com.blog.service.WishesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 留言板 服务实现类
 * </p>
 *
 * @author 关注公众号：MarkerHub
 * @since 2023-03-07
 */
@Service
public class WishesServiceImpl extends ServiceImpl<WishesMapper, Wishes> implements WishesService {

}
