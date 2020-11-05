package com.duan.user.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.duan.user.domain.User;
import com.duan.user.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
 * @author duanwj
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
