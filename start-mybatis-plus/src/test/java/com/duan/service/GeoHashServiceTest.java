package com.duan.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.StopWatch;
import cn.hutool.core.util.RandomUtil;
import com.duan.domain.GeoHash;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@Slf4j
@ExtendWith(SpringExtension.class)
class GeoHashServiceTest {

  @Resource
  private GeoHashService geoHashService;

  @Test
  public void test () {
    List<GeoHash> list = geoHashService.list();
    for (int j = 0; j < 50; j++) {
      StopWatch stopWatch = DateUtil.createStopWatch();
      stopWatch.start();
      log.info("开始执行第{}插入", j + 1);
      for (GeoHash geoHash : list) {
        List<GeoHash> geoHashes = new ArrayList<>(5000);
        for (int i = 0; i < 5000; i++) {
          GeoHash geo = new GeoHash();
          BeanUtil.copyProperties(geoHash, geo);
          geo.setGeoHash(RandomUtil.randomString(8));
          geoHashes.add(geo);
        }
        geoHashService.batchSave(geoHashes);
      }
      stopWatch.stop();
      log.info("第{}插入执行结束耗时{}", j + 1, stopWatch.getTotalTimeSeconds());
    }
  }

}