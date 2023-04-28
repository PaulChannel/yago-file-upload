package com.yago.upload.service;

import com.yago.upload.domain.FileChunkEntity;

/**
 * @author: yougen.hu
 * @time: 2023/4/28 16:44
 */

public interface LocalStorageService {

  void saveLocalStorage(FileChunkEntity fileChunkEntity);
}
