package com.yago.upload.test;

import java.util.List;

import cn.hutool.json.JSONUtil;
import com.yago.upload.domain.FileChunkEntity;
import com.yago.upload.service.impl.FileChunkService;
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
}
