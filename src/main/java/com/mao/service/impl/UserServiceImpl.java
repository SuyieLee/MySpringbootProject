package com.mao.service.impl;

import com.mao.entity.User;
import com.mao.mapper.UserMapper;
import com.mao.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
