package com.yago.upload.exception;

/**
 * 全局自定义异常
 *
 * @author: yougen.hu
 * @time: 2023/4/28 15:55
 */

public class BizException extends RuntimeException {

  public BizException() {
  }

  public BizException(String msg) {
    super(msg);
  }

  public BizException(String message, Throwable cause) {
    super(message, cause);
  }

  public BizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  public BizException(Throwable cause) {
    super(cause);
  }
}
