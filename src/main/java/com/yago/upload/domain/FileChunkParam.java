package com.yago.upload.domain;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * Created by tao.
 * Date: 2022/6/29 9:53
 * 描述:
 */
@Data
public class FileChunkParam implements Serializable {

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


}
