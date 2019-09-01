package com.rsdtm.supermarket.mbg.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import tk.mybatis.mapper.annotation.KeySql;

@ApiModel(value = "商品表")
@Table(name = "bms_product")
@Data
public class BmsProduct implements Serializable {
    @ApiModelProperty(value = "id")
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;

    @ApiModelProperty(value = "产品名称")
    private String name;

    @ApiModelProperty(value = "是否启用：0->下架,1->上架")
    private Byte enable;

    @ApiModelProperty(value = "分类id")
    private Integer productCategoryId;

    @ApiModelProperty(value = "图片路径")
    private String picture;

    @ApiModelProperty(value = "排序权重")
    private Integer sort;

    @ApiModelProperty(value = "销量")
    private BigDecimal sale;

    @ApiModelProperty(value = "价格")
    private BigDecimal price;

    @ApiModelProperty(value = "促销价")
    private BigDecimal promotionPrice;

    @ApiModelProperty(value = "计价单位")
    private String chargeUnit;

    @ApiModelProperty(value = "赠送积分")
    private Integer giftPoint;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "库存")
    private BigDecimal stock;

    @ApiModelProperty(value = "库存预警值")
    private BigDecimal lowStock;

    @ApiModelProperty(value = "单位：默认为千克")
    private String unit;

    @ApiModelProperty(value = "储存条件")
    private String storageCondition;

    @ApiModelProperty(value = "备注")
    private String comment;

    private static final long serialVersionUID = 1L;
}