package com.shipping.service.impl;

import com.shipping.entity.User;
import com.shipping.mapper.UserMapper;
import com.shipping.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

  @Autowired
  UserService userService;

  @Override
  public void updateAvatar(Long id, String filePath) {
    User user = userService.getById(id);
    user.setAvatar(filePath);
    userService.saveOrUpdate(user);
  }
}
