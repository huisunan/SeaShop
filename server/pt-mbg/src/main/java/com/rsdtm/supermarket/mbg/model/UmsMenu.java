package com.rsdtm.supermarket.mbg.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import tk.mybatis.mapper.annotation.KeySql;

@ApiModel(value = "资源表")
@Table(name = "ums_menu")
@Data
public class UmsMenu implements Serializable {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;

    @ApiModelProperty(value = "url,ant风格匹配规则")
    private String url;

    @ApiModelProperty(value = "方法")
    private String method;

    @ApiModelProperty(value = "用于vue")
    private String path;

    @ApiModelProperty(value = "用于vue route")
    private String component;

    @ApiModelProperty(value = "描述，展示于路由导航")
    private String name;

    @ApiModelProperty(value = "是否需要验证:0->不，1->是")
    private Byte requireAuth;

    @ApiModelProperty(value = "字体类")
    private String iconClass;

    private Integer keepAlive;

    private static final long serialVersionUID = 1L;
}