package com.yago.upload.test.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UploadDao {

  Object mybatisTest();
}
