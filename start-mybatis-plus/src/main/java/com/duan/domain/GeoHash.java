package com.duan.domain;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 经纬度编码对象 t_geo_hash
 *
 * @author duanwenjun
 * @date 2020-12-12 20:45:07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_geo_hash")
public class GeoHash implements Serializable {

  private static final long serialVersionUID = 1L;
  @TableId(value = "f_id")
  private Long id;

  /**
   * 编码值
   */
  @TableField(value = "f_geo_hash")
  private String geoHash;
  /**
   * 市级编码
   */
  @TableField(value = "f_code")
  private Integer code;
  /**
   * 省编码
   */
  @TableField(value = "f_province_code")
  private String provinceCode;
  /**
   * 省名称
   */
  @TableField(value = "f_province_name")
  private String provinceName;
  /**
   * 市编码
   */
  @TableField(value = "f_city_code")
  private String cityCode;
  /**
   * 市名称
   */
  @TableField(value = "f_city_name")
  private String cityName;
  /**
   * 县编码
   */
  @TableField(value = "f_country_code")
  private String countryCode;
  /**
   * 县名称
   */
  @TableField(value = "f_country_name")
  private String countryName;
  /**
   * 组合编码
   */
  @TableField(value = "f_merge_code")
  private String mergeCode;
  /**
   * 组合名称
   */
  @TableField(value = "f_merge_name")
  private String mergeName;
  /**
   * 区号
   */
  @TableField(value = "f_area_code")
  private String areaCode;
  /**
   * 经纬度信息
   */
  @TableField(value = "f_location")
  private String location;
  /**
   * 第三方返回值
   */
  @TableField(value = "f_source")
  private String source;
  /**
   * 备注
   */
  @TableField(value = "f_remark")
  private String remark;


  /**
   * 创建者
   */
  @TableField(value = "f_create_by")
  private String createBy;

  /**
   * 创建时间
   */
  @TableField(value = "f_create_time")
  private Date createTime;

  /**
   * 更新者
   */
  @TableField(value = "f_update_by")
  private String updateBy;

  /**
   * 更新时间
   */
  @TableField(value = "f_update_time")
  private Date updateTime;

  @TableLogic
  @TableField(value = "f_yn")
  private Integer yn;
}
