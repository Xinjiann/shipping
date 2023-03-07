package com.shipping.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shipping.entity.Comment;
import com.shipping.mapper.CommentMapper;
import com.shipping.service.CommentService;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements
    CommentService {

}
