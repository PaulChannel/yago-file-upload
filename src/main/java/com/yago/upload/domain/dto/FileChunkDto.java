package com.yago.upload.domain.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author: yougen.hu
 * @time: 2023/4/28 13:05
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FileChunkDto {

  private Long id;

  private Integer chunkNumber;

  private Float chunkSize;

  private Float currentChunkSize;

  private Integer totalChunks;

  private Double totalSize;

  private String identifier;

  private String filename;

  private String relativePath;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date createTime;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date updateTime;

  private MultipartFile multipartFile;
}
