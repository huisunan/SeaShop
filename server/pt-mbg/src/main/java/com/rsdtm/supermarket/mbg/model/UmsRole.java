package com.rsdtm.supermarket.mbg.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import tk.mybatis.mapper.annotation.KeySql;

@ApiModel(value = "角色表")
@Table(name = "ums_role")
@Data
public class UmsRole implements Serializable {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;

    @ApiModelProperty(value = "英文角色名，用于security角色验证")
    private String name;

    @ApiModelProperty(value = "中文角色名，用于描述")
    private String nameZh;

    private static final long serialVersionUID = 1L;
}