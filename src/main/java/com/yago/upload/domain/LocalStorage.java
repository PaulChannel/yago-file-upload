package com.yago.upload.domain;

import java.io.Serializable;
import java.util.Date;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * Created by tao.
 * Date: 2022/7/1 9:27
 * 描述:
 */
@Data
public class LocalStorage implements Serializable {

    private Long id;

    private String realName;

    private String name;

    private String suffix;

    private String path;

    private String type;

    private String size;

    private String identifier;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    public LocalStorage() {
    }

    public LocalStorage(String realName, String name, String suffix, String path, String type, String size, String identifier) {
        this.realName = realName;
        this.name = name;
        this.suffix = suffix;
        this.path = path;
        this.type = type;
        this.size = size;
        this.identifier = identifier;
    }

    public LocalStorage(Long id, String realName, String name, String suffix, String path, String type, String size, String identifier) {
        this.id = id;
        this.realName = realName;
        this.name = name;
        this.suffix = suffix;
        this.path = path;
        this.type = type;
        this.size = size;
        this.identifier = identifier;
    }

    public void copy(LocalStorage source) {
        BeanUtil.copyProperties(source, this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
