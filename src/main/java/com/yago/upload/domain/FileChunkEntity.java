package com.yago.upload.domain;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by tao.
 * Date: 2022/6/29 9:53
 * 描述:
 */
@Data
@Accessors(chain = true)
public class FileChunkEntity implements Serializable {

  private Long id;

  /**
   * 当前分片编号，从1开始
   */
  private Integer chunkNumber;
  /**
   * 分片大小
   */
  private Float chunkSize;
  /**
   * 当前分片大小
   */
  private Float currentChunkSize;
  /**
   * 分片总量
   */
  private Integer totalChunk;

  /**
   * 文件总大小
   */
  private Double totalSize;

  /**
   * 文件标识,md5校验码
   */
  private String identifier;

  /**
   * 文件名称
   */
  private String filename;
  /**
   * 文件真实路径
   */
  private String relativePath;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date createTime;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date updateTime;


}
