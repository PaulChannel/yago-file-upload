package com.yago.upload.test.service;

import java.util.UUID;

import com.yago.upload.domain.vo.FileChunkVo;
import com.yago.upload.service.UploadService;
import com.yago.upload.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

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

  @Test
  public void testChunkFileUpload() {
    FileChunkVo fileChunkVo1 = new FileChunkVo();
    FileChunkVo fileChunkVo2 = new FileChunkVo();
    fileChunkVo2.setFileName("chunkName2.txt");
    fileChunkVo1.setFileName("chunkName2.txt");
    fileChunkVo1.setRelativePath("chunk/chunkName2.txt");
    fileChunkVo2.setRelativePath("chunk/chunkName2.txt");
    fileChunkVo1.setTotalSize(10.0);
    fileChunkVo2.setTotalSize(10.0);
    fileChunkVo1.setChunkNumber(1);
    fileChunkVo2.setChunkNumber(2);
    fileChunkVo1.setChunkSize(5F);
    fileChunkVo2.setChunkSize(5F);
    fileChunkVo2.setTotalChunk(2);
    fileChunkVo1.setTotalChunk(2);
    MultipartFile multipartFile1 = new MockMultipartFile("chuntest", "bbbbb".getBytes());
    fileChunkVo1.setMultipartFile(multipartFile1);
    MultipartFile multipartFile2 = new MockMultipartFile("chuntest", "bbbbb".getBytes());
    fileChunkVo2.setMultipartFile(multipartFile2);
    uploadService.uploadFile(fileChunkVo1);
    uploadService.uploadFile(fileChunkVo2);

  }

}

