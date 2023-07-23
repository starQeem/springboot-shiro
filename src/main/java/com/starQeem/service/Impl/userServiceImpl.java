package com.starQeem.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.starQeem.mapper.userMapper;
import com.starQeem.pojo.user;
import com.starQeem.service.userService;
import org.springframework.stereotype.Service;

/**
 * @Date: 2023/5/18 13:25
 * @author: Qeem
 */
@Service
public class userServiceImpl extends ServiceImpl<userMapper, user> implements userService {
}
