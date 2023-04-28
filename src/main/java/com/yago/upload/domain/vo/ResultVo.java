package com.yago.upload.domain.vo;

import lombok.Data;

/**
 * @author: yougen.hu
 * @time: 2023/4/28 12:46
 */
@Data
public class ResultVo<T> {

  /**
   * 返回数据
   */
  T data;
  /**
   * 状态码
   */
  private Integer code;
  /**
   * 返回信息
   */
  private String msg;

  public ResultVo(Integer code, String msg, T data) {
    this.code = 200;
    this.msg = msg;
    this.data = data;
  }

  public ResultVo(Integer code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  public ResultVo() {
  }
}
