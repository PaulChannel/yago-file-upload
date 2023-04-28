package com.yago.upload.dao;

import java.util.List;

import com.yago.upload.domain.FileChunkEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author: yougen.hu
 * @time: 2023/4/28 13:40
 */

@Mapper
public interface FileChunkDao {

  List<FileChunkEntity> findByMd5(@Param("md5") String md5);
}
