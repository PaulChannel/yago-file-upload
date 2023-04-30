package com.yago.upload.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Method;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Objects;

import com.yago.upload.dao.UploadDao;
import com.yago.upload.domain.vo.FileChunkVo;
import com.yago.upload.exception.BizException;
import com.yago.upload.service.FileChunkService;
import com.yago.upload.service.LocalStorageService;
import com.yago.upload.service.UploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.Cleaner;

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

  @Autowired
  private FileChunkService fileCHunkService;
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
    File realFile;
    String div = "/";
    // 如果上传的的文件包含子目录
    if (relativePath.contains("/") || relativePath.contains(File.separator)) {
      log.debug("上传解析文件夹====>{}", relativePath);
      div = relativePath.contains(File.separator) ? File.separator : "/";
      String tempPath = relativePath.substring(0, relativePath.lastIndexOf(div));
      File realDir = new File(BASE_FILE_SAVE_PATH + div + tempPath);
      if (!realDir.exists()) {
        boolean mkdir = realDir.mkdir();
        if (mkdir) {
          log.info("创建上传文件夹成功{}", realDir);
        } else {
          log.error("创建上传文件夹失败{}", realDir);
        }
      }
      // 如果上传的是基于基础路径的文件
    }
    realFile = new File(BASE_FILE_SAVE_PATH + div + relativePath);
    // 将真实路径进行重制方便存入数据库。
    log.debug("relativePath====>{}", relativePath);
    String path = realFile.toURI().getPath();
    fileChunkVo.setRelativePath(path);

    // 如果是单片文件上传
    if (fileChunkVo.getTotalChunk() == 1) {
      uploadSingleFile(multipartFile, realFile);
      localStorageService.saveLocalStorage(fileChunkVo);
      return true;
    }
    // 如果是多片文件上传
    // uploadByRandomAccessFile(realFile, fileChunkVo);
    uploadByByteBuffer(realFile, fileChunkVo);
    return true;
  }


  public boolean uploadSingleFile(MultipartFile multipartFile, File dest) {
    try {
      log.debug("file ==>{}", dest);
      log.debug("file ==>{}", multipartFile);
      multipartFile.transferTo(dest);
    } catch (IOException e) {
      throw new BizException("单片文件上传io异常", e);
    }
    return true;
  }

  public boolean uploadByRandomAccessFile(File realName, FileChunkVo fileChunkVo) {
    try (RandomAccessFile randomAccessFile = new RandomAccessFile(realName, "rw")) {
      long offset = (fileChunkVo.getChunkNumber() - 1) * fileChunkVo.getChunkSize().longValue();
      randomAccessFile.seek(offset);
      long filePointer = randomAccessFile.getFilePointer();
      log.info("filePointer===>{}", filePointer);
      randomAccessFile.write(fileChunkVo.getMultipartFile().getBytes());
      fileCHunkService.saveFileChunk(fileChunkVo);
      if (Objects.equals(fileChunkVo.getChunkNumber(), fileChunkVo.getTotalChunk())) {
        localStorageService.saveLocalStorage(fileChunkVo);
      }
    } catch (FileNotFoundException e) {
      log.error("文件未找到", e);
      throw new RuntimeException(e);
    } catch (IOException e) {
      log.error("分片上传io异常", e);
      throw new RuntimeException(e);
    }
    return true;
  }

  public boolean uploadByByteBuffer(File realName, FileChunkVo fileChunkVo) {
    MappedByteBuffer mappedByteBuffer = null;
    try (RandomAccessFile randomAccessFile = new RandomAccessFile(realName, "rw"); FileChannel channel = randomAccessFile.getChannel();) {
      long offset = fileChunkVo.getChunkSize().longValue() * (fileChunkVo.getChunkNumber() - 1);

      mappedByteBuffer = channel.map(MapMode.READ_WRITE, offset, fileChunkVo.getChunkSize().longValue());
      mappedByteBuffer.put(fileChunkVo.getMultipartFile().getBytes());
      if (fileChunkVo.getChunkNumber().equals(fileChunkVo.getTotalChunk())) {
        localStorageService.saveLocalStorage(fileChunkVo);
      }

    } catch (IOException e) {
      log.error("uploadByByteBuffer fial", e);
      throw new BizException("uploadByByteBuffer fial");

    } finally {
      umap(mappedByteBuffer);
    }
    return true;
  }

  public void umap(MappedByteBuffer mappedByteBuffer) {
    if (mappedByteBuffer == null) {
      return;
    }
    // 将缓冲区中的内容强行写入文件中
    mappedByteBuffer.force();
    AccessController.doPrivileged((PrivilegedAction<Object>) () -> {
      try {
        Method method = mappedByteBuffer.getClass().getMethod("cleaner");
        method.setAccessible(true);
        Cleaner cleaner = (Cleaner) method.invoke(mappedByteBuffer, new Object[0]);
        cleaner.clean();
      } catch (Exception e) {
        log.error("释放mappedByteBuffer失败", e);
        throw new RuntimeException(e);
      }
      return null;
    });
  }

}
