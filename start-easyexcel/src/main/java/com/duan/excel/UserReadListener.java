package com.duan.excel;

import cn.hutool.json.JSONUtil;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.extern.slf4j.Slf4j;

/**
 * @author duanwj
 */
@Slf4j
public class UserReadListener extends AnalysisEventListener<UserDTO> {

  @Override
  public void invoke (UserDTO data, AnalysisContext context) {
    log.info("解析到一条数据{}", JSONUtil.parse(data));
  }

  @Override
  public void doAfterAllAnalysed (AnalysisContext context) {
    log.info("所有数据解析完成!");
  }
}
