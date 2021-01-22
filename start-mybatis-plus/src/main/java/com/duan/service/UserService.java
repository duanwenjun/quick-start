package com.duan.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.duan.domain.User;

/**
 * @author duanwj
 */
public interface UserService extends IService<User> {

  /**
   * 批量保存
   *
   * @return 返回插入数据数量
   * @param list
   */
  Integer batchSave (List<User> list);

}
