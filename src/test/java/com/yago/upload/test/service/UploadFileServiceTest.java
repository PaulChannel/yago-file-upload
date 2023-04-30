package com.yago.upload.test.service;

import java.util.UUID;

import com.yago.upload.domain.vo.FileChunkVo;
import com.yago.upload.service.UploadService;
import com.yago.upload.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;

/**
 * @author: yougen.hu
 * @time: 2023/4/28 17:51
 */

public class UploadFileServiceTest extends BaseTest {

  @Autowired
  private UploadService uploadService;

  @Test
  public void testSingleFileUpload() {
    FileChunkVo fileChunkVo = new FileChunkVo();
    fileChunkVo.setRelativePath("test1");
    fileChunkVo.setChunkNumber(1);
    fileChunkVo.setTotalChunk(1);
    fileChunkVo.setFileName("test.txt");
    fileChunkVo.setIdentifier(UUID.randomUUID().toString());
    fileChunkVo.setTotalSize(3D);
    MockMultipartFile mockMultipartFile = new MockMultipartFile("test", "test", "txt", "aaa".getBytes());
    fileChunkVo.setMultipartFile(mockMultipartFile);
    uploadService.uploadFile(fileChunkVo);
  }
}

