package com.yago.upload.test.service;

import java.util.Date;

import com.yago.upload.domain.FileChunkEntity;
import com.yago.upload.service.LocalStorageService;
import com.yago.upload.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: yougen.hu
 * @time: 2023/4/28 17:00
 */

public class LocalStorageServiceTest extends BaseTest {

  @Autowired
  private LocalStorageService localStorageService;

  @Test
  public void testSaveLocalStorage() {
    FileChunkEntity fileChunkEntity = new FileChunkEntity();
    fileChunkEntity.setId(2L).setChunkSize(1F).setChunkNumber(1).setCurrentChunkSize(1F).setTotalChunk(1).setFilename("test")
        .setCreateTime(new Date()).setUpdateTime(new Date());
    localStorageService.saveLocalStorage(fileChunkEntity);
  }
}
