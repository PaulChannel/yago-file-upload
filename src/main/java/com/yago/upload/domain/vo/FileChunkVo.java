package com.yago.upload.domain.vo;

import com.yago.upload.domain.FileChunkEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author: yougen.hu
 * @time: 2023/4/28 13:05
 */

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FileChunkVo extends FileChunkEntity {

  private MultipartFile multipartFile;
}
