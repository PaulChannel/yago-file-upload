package com.yago.upload.test.service;

import java.util.Date;
import java.util.List;

import cn.hutool.json.JSONUtil;
import com.yago.upload.domain.FileChunkEntity;
import com.yago.upload.service.FileChunkService;
import com.yago.upload.test.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: yougen.hu
 * @time: 2023/4/28 14:22
 */

@Slf4j
public class FileChunkServiceTest extends BaseTest {

  @Autowired
  FileChunkService fileChunkService;

  @Test
  public void testFindByMd5() {
    List<FileChunkEntity> listFileChunk = fileChunkService.findByMd5("1");
    log.info(JSONUtil.toJsonStr(listFileChunk));
  }

  @Test
  public void testSaveFileChunk() {
    FileChunkEntity fileChunkEntity = new FileChunkEntity();
    fileChunkEntity.setId(2L).setChunkSize(1F).setChunkNumber(1).setCurrentChunkSize(1F).setTotalChunk(1).setFilename("test")
        .setCreateTime(new Date()).setUpdateTime(new Date());
    fileChunkService.saveFileChunk(fileChunkEntity);
  }
}
