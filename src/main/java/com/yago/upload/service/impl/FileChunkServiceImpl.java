package com.yago.upload.service.impl;

import java.util.List;

import com.yago.upload.dao.FileChunkDao;
import com.yago.upload.domain.FileChunkEntity;
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
  public List<FileChunkEntity> findByMd5(String md5) {
    List<FileChunkEntity> listFileChunk = fileChunkDao.findByMd5(md5);
    return listFileChunk;
  }
}
