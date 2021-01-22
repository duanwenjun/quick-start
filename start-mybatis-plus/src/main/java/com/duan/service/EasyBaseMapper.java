package com.duan.service;

import java.util.Collection;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author duanwj
 */
public interface EasyBaseMapper<T> extends BaseMapper<T> {

  /**
   * 批量保存
   *
   * @param entityList 实体列表
   * @return 操作行数
   */
  Integer insertBatchSomeColumn (Collection<T> entityList);

}
