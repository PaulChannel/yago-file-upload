package com.yago.upload.service;

import java.util.List;

import com.yago.upload.domain.FileChunkEntity;

/**
 * @author: yougen.hu
 * @time: 2023/4/28 14:18
 */

public interface FileChunkService {

  /**
   * 通过md5查询文件
   *
   * @param md5
   * @return
   */
  List<FileChunkEntity> findByMd5(String md5);

  /**
   * 保存fileChunk信息
   */
  void saveFileChunk(FileChunkEntity fileChunkEntity);
}
