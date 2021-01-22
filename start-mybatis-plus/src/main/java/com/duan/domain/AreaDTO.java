package com.duan.domain;

import java.io.Serializable;

import lombok.Data;

/**
 * 位置信息
 *
 * @author duanwj
 */
@Data
public class AreaDTO implements Serializable {

  private static final long serialVersionUID = -1205319541964769456L;

  /**
   * 市级编码（6位数）
   */
  private Integer code;

  /**
   * 组合名
   */
  private String mergeName;

  /**
   * 城市编码
   * 原来fp_cms_api 中调用高德地图api中adcode
   */
  private String cityCode;

  /**
   * 区号
   * 原来fp_cms_api 中调用高德地图api中citycode
   */
  private String areaCode;

  /**
   * 省城市名称
   */
  private String dictProvince;

  /**
   * 省编码
   */
  private String dictProvinceCode;

  /**
   * 市城市名称
   */
  private String dictCity;

  /**
   * 市城市编码
   */
  private String dictCityCode;

}
