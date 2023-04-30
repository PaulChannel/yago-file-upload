package com.yago.upload.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.yago.upload.dao.FileChunkDao;
import com.yago.upload.domain.FileChunkEntity;
import com.yago.upload.service.FileChunkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: yougen.hu
 * @time: 2023/4/28 14:20
 */

@Service
public class FileChunkServiceImpl implements FileChunkService {

  @Autowired
  FileChunkDao fileChunkDao;

  @Override
  public void saveFileChunk(FileChunkEntity fileChunkEntity) {
    if (Objects.isNull(fileChunkEntity.getCreateTime())) {
      fileChunkEntity.setCreateTime(new Date());
    }
    if (Objects.isNull(fileChunkEntity.getUpdateTime())) {
      fileChunkEntity.setUpdateTime(new Date());
    }
    fileChunkDao.saveFileChunk(fileChunkEntity);
  }

  @Override
  public List<FileChunkEntity> findByMd5(String md5) {
    List<FileChunkEntity> listFileChunk = fileChunkDao.findByMd5(md5);
    return listFileChunk;
  }
}
