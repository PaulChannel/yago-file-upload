package com.yago.upload.dao;

import com.yago.upload.domain.LocalStorageEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: yougen.hu
 * @time: 2023/4/28 13:41
 */

@Mapper
public interface LocalStorageDao {

  void saveLocalStorage(LocalStorageEntity localStorageEntity);
}
