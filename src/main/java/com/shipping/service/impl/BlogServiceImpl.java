package com.shipping.service.impl;

import com.shipping.entity.Blog;
import com.shipping.mapper.BlogMapper;
import com.shipping.service.BlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

}
