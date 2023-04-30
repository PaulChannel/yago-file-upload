package com.yago.upload.controller;

import com.yago.upload.domain.vo.FileChunkVo;
import com.yago.upload.domain.vo.ResultVo;
import com.yago.upload.service.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: yougen.hu
 * @time: 2023/4/28 17:31
 */

@RestController
@RequestMapping("/upload")
@RequiredArgsConstructor
public class UploadController {

  private final UploadService uploadService;

  @PostMapping("/chunkUpload")
  public ResultVo chunkUpload(FileChunkVo fileChunkVo) {
    boolean b = uploadService.uploadFile(fileChunkVo);
    if (b) {
      return new ResultVo(200, "文件上传成功");
    }
    return new ResultVo(500, "文件上传失败");
  }
}
