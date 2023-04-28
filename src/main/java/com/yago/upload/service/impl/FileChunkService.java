package com.yago.upload.service.impl;

import java.util.List;

import com.yago.upload.domain.FileChunkEntity;

/**
 * @author: yougen.hu
 * @time: 2023/4/28 14:18
 */

public interface FileChunkService {

  List<FileChunkEntity> findByMd5(String md5);
}
