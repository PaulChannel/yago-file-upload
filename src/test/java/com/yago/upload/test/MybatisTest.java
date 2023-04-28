package com.yago.upload.test;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

import com.yago.upload.dao.UploadDao;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: yougen.hu
 * @time: 2023/4/28 09:58
 */


@Slf4j
public class MybatisTest extends BaseTest {

  @Autowired
  private DataSource dataSource;
  @Autowired
  private UploadDao uploadDao;

  @Test
  public void testDateSource() throws SQLException {
    Connection connection = dataSource.getConnection();
    log.info("connection=====>{}", connection);
  }

  @Test
  public void testMybatis() {
    Object o = uploadDao.queryAll();
    log.info("res==={}", o);
  }
}
