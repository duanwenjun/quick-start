package com.duan.config;

import java.util.List;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.extension.injector.methods.InsertBatchSomeColumn;

/**
 * @author duanwj
 */
public class EasySqlInjector extends DefaultSqlInjector {

  @Override
  public List<AbstractMethod> getMethodList (Class<?> mapperClass) {
    List<AbstractMethod> methodList = super.getMethodList(mapperClass);
    methodList.add(new InsertBatchSomeColumn());
    return methodList;
  }
}
