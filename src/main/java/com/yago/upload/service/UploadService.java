package com.yago.upload.service;

import com.yago.upload.domain.vo.FileChunkVo;

public interface UploadService {

  Object queryAll();

  boolean uploadFile(FileChunkVo fileChunkVo);
}
