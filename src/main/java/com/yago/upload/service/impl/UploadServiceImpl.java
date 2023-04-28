package com.yago.upload.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import com.yago.upload.dao.UploadDao;
import com.yago.upload.domain.vo.FileChunkVo;
import com.yago.upload.exception.BizException;
import com.yago.upload.service.LocalStorageService;
import com.yago.upload.service.UploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author: yougen.hu
 * @time: 2023/4/28 10:33
 */

@Service
@Slf4j
public class UploadServiceImpl implements UploadService {

  @Autowired
  private UploadDao uploadDao;

  @Autowired
  private LocalStorageService localStorageService;
  @Value("${BASE_FILE_SAVE_PATH}")
  private String BASE_FILE_SAVE_PATH;

  @Override
  public Object queryAll() {
    return uploadDao.queryAll();
  }

  @Override
  public boolean uploadFile(FileChunkVo fileChunkVo) {
    MultipartFile multipartFile = fileChunkVo.getMultipartFile();
    if (Objects.isNull(multipartFile)) {
      throw new BizException("传入参数文件为空！");
    }
    // 如果系统还未创建基础文件夹
    File baseFile = new File(BASE_FILE_SAVE_PATH);
    if (!baseFile.exists()) {
      boolean mkdir = baseFile.mkdir();
      if (mkdir) {
        log.info("创建base_file成功");
      } else {
        log.error("创建base_file_dir失败");
        return false;
      }
    }
    String relativePath = fileChunkVo.getRelativePath();
    File realPath;
    // 如果上传的文件夹
    if (relativePath.contains("/") || relativePath.contains(File.separator)) {
      String div = relativePath.contains(File.separator) ? File.separator : "/";
      String tempPath = relativePath.substring(0, relativePath.indexOf(div));
      realPath = new File(BASE_FILE_SAVE_PATH + div + tempPath);
      if (!realPath.exists()) {
        boolean mkdir = realPath.mkdir();
        if (mkdir) {
          log.info("创建上传文件夹成功");
        } else {
          log.error("创建上传文件夹失败");
        }
      }
      // 如果上传的是基于基础路径的文件
    } else {
      realPath = baseFile;
    }

    // 如果是单片文件上传
    if (fileChunkVo.getTotalChunk() == 1) {
      uploadSingleFile(multipartFile, realPath);
      localStorageService.saveLocalStorage(fileChunkVo);
    }
    return false;
  }


  public boolean uploadSingleFile(MultipartFile multipartFile, File dest) {

    try {
      multipartFile.transferTo(dest);


    } catch (IOException e) {
      throw new BizException("单片文件上传io异常", e);

    }
    return true;
  }
}
