package com.yago.upload.service.impl;

import com.yago.upload.service.UploadService;
import com.yago.upload.test.dao.UploadDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: yougen.hu
 * @time: 2023/4/28 10:33
 */

@Service
public class UploadServiceImpl implements UploadService {

  @Autowired
  private UploadDao uploadDao;


  @Override
  public Object queryAll() {
    return uploadDao.mybatisTest();
  }
}
