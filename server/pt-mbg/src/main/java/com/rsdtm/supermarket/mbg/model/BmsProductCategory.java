package com.rsdtm.supermarket.mbg.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import tk.mybatis.mapper.annotation.KeySql;

@ApiModel(value = "商品分类表")
@Table(name = "bms_product_category")
@Data
public class BmsProductCategory implements Serializable {
    @ApiModelProperty(value = "商品ID")
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;

    @ApiModelProperty(value = "1->1级,2->2级,3->3级")
    private Integer type;

    @ApiModelProperty(value = "分类名称")
    private String name;

    @ApiModelProperty(value = "父类ID,一级分类则为0")
    private Integer parentId;

    @ApiModelProperty(value = "图标")
    private String icon;

    private static final long serialVersionUID = 1L;
}