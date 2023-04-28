package com.yago.upload.domain;

import java.io.Serializable;
import java.util.Date;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by tao.
 * Date: 2022/7/1 9:27
 * 描述:
 */
@Data
@Accessors(chain = true)
public class LocalStorageEntity implements Serializable {

  private Long id;
  /**
   * 文件真实名称
   */
  private String realName;
  /**
   * 文件名
   */
  private String name;

  /**
   * 后缀
   */
  private String suffix;
  /**
   * 路径
   */
  private String path;

  /**
   * 类型
   */
  private String type;

  /**
   * 大小
   */
  private String size;

  /**
   * 文件标识，md5校验码
   */
  private String identifier;

  /**
   * 创建人
   */
  private String createBy;
  /**
   * 更新人
   */
  private String updateBy;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date createTime;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date updateTime;

  public LocalStorageEntity() {
  }

  public LocalStorageEntity(String realName, String name, String suffix, String path, String type, String size, String identifier) {
    this.realName = realName;
    this.name = name;
    this.suffix = suffix;
    this.path = path;
    this.type = type;
    this.size = size;
    this.identifier = identifier;
  }

  public LocalStorageEntity(Long id, String realName, String name, String suffix, String path, String type, String size, String identifier) {
    this.id = id;
    this.realName = realName;
    this.name = name;
    this.suffix = suffix;
    this.path = path;
    this.type = type;
    this.size = size;
    this.identifier = identifier;
  }

  public void copy(LocalStorageEntity source) {
    BeanUtil.copyProperties(source, this, CopyOptions.create().setIgnoreNullValue(true));
  }
}
