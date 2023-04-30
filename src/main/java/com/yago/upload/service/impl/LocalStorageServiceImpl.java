package com.yago.upload.service.impl;

import java.util.Date;
import java.util.Objects;

import com.yago.upload.dao.LocalStorageDao;
import com.yago.upload.domain.FileChunkEntity;
import com.yago.upload.domain.LocalStorageEntity;
import com.yago.upload.service.LocalStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: yougen.hu
 * @time: 2023/4/28 16:45
 */

@Service
public class LocalStorageServiceImpl implements LocalStorageService {

  @Autowired
  private LocalStorageDao localStorageDao;

  @Override
  public void saveLocalStorage(FileChunkEntity fileChunkEntity) {

    LocalStorageEntity localStorageEntity = new LocalStorageEntity();
    String fileName = fileChunkEntity.getFileName();
    if (Objects.isNull(fileChunkEntity.getCreateTime())) {
      fileChunkEntity.setCreateTime(new Date());
    }
    if (Objects.isNull(fileChunkEntity.getUpdateTime())) {
      fileChunkEntity.setUpdateTime(new Date());
    }
    if (fileName.contains(".")) {
      int idx = fileName.indexOf(".");
      localStorageEntity.setSuffix(fileName.substring(idx));
      localStorageEntity.setType(fileName.substring(idx + 1));
    }
    localStorageEntity.setRealName(fileName).setName(fileName).setUpdateTime(fileChunkEntity.getCreateTime())
        .setCreateTime(fileChunkEntity.getUpdateTime()).setPath(fileChunkEntity.getRelativePath()).setIdentifier(fileChunkEntity.getIdentifier())
        .setSize(fileChunkEntity.getTotalSize()).setUpdateTime(new Date()).setIdentifier(fileChunkEntity.getIdentifier());
    localStorageDao.saveLocalStorage(localStorageEntity);
  }
}
