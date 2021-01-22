package com.duan.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.duan.domain.AreaDTO;
import com.duan.domain.GeoHash;
import com.duan.service.EasyBaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * 经纬度编码 数据层
 *
 * @author duanwenjun
 * @date 2020-12-12 20:45:07
 */
public interface GeoHashMapper extends EasyBaseMapper<GeoHash> {

  /**
   * 查询经纬度编码列表
   *
   * @param page    分页参数
   * @param geoHash 经纬度编码
   * @return 经纬度编码集合
   */
  IPage<GeoHash> queryGeoHashPageList (@Param("page") Page<GeoHash> page, @Param("geoHash") GeoHash geoHash);

  /**
   * 导出经纬度编码列表
   *
   * @param geoHash 经纬度编码
   * @return 经纬度编码集合
   */
  List<GeoHash> queryGeoHashList (@Param("geoHash") GeoHash geoHash);

  /**
   * 根据GeoHash 查询城市信息
   *
   * @param geoHash 经纬度geoHash后的编码
   * @return 城市信息
   */
  AreaDTO queryAreaByGeoHash (@Param("geoHash") String geoHash);

}