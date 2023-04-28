package com.yago.upload.service.impl;

import java.util.Date;

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
    localStorageEntity.setId(1L).setCreateTime(new Date()).setUpdateTime(new Date());
    localStorageDao.saveLocalStorage(localStorageEntity);
  }
}
