package com.duan.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.duan.domain.User;
import com.duan.mapper.UserMapper;
import com.duan.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author duanwj
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

  @Override
  public Integer batchSave (List<User> list) {
    return baseMapper.insertBatchSomeColumn(list);
  }
}
