package com.duan.service;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.duan.domain.AreaDTO;
import com.duan.domain.GeoHash;

/**
 * 经纬度编码 服务层
 *
 * @author duanwenjun
 * @date 2020-12-12 20:45:07
 */
public interface GeoHashService extends IService<GeoHash> {

  /**
   * 查询经纬度编码列表
   *
   * @param page    分页参数
   * @param geoHash 经纬度编码
   * @return 经纬度编码集合
   */
  IPage<GeoHash> getGeoHashPageList (Page<GeoHash> page, GeoHash geoHash);

  /**
   * 导出经纬度编码列表
   *
   * @param geoHash 经纬度编码
   * @return 经纬度编码集合
   */
  List<GeoHash> getGeoHashList (GeoHash geoHash);

  /**
   * 根据GeoHash 查询城市信息
   *
   * @param geoHash 经纬度geoHash后的编码
   * @return 城市信息
   */
  AreaDTO getAreaByGeoHash (String geoHash);

  /**
   * 批量插入
   * @param geoHashes 批量数据
   * @return 影响行数
   */
  Integer batchSave (List<GeoHash> geoHashes);

}