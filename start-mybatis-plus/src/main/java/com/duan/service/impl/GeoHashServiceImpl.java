package com.duan.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.duan.domain.AreaDTO;
import com.duan.domain.GeoHash;
import com.duan.mapper.GeoHashMapper;
import com.duan.service.GeoHashService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 经纬度编码 服务层实现
 *
 * @author duanwenjun
 * @date 2020-12-12 20:45:07
 */
@Service
@Transactional(readOnly = true)
public class GeoHashServiceImpl extends ServiceImpl<GeoHashMapper, GeoHash> implements GeoHashService {

  @Override
  public IPage<GeoHash> getGeoHashPageList (Page<GeoHash> page, GeoHash geoHash) {
    return baseMapper.queryGeoHashPageList(page, geoHash);
  }

  @Override
  public List<GeoHash> getGeoHashList (GeoHash geoHash) {
    return baseMapper.queryGeoHashList(geoHash);
  }

  @Override
  public AreaDTO getAreaByGeoHash (String geoHash) {
    return baseMapper.queryAreaByGeoHash(geoHash);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public Integer batchSave (List<GeoHash> geoHashes) {
    return baseMapper.insertBatchSomeColumn(geoHashes);
  }
}