package com.rsdtm.supermarket.mbg.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import tk.mybatis.mapper.annotation.KeySql;

@ApiModel(value = "采购订单")
@Table(name = "gms_purchase")
@Data
public class GmsPurchase implements Serializable {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;

    @ApiModelProperty(value = "采购编号")
    private String purchaseSn;

    @ApiModelProperty(value = "数量")
    private BigDecimal quantity;

    @ApiModelProperty(value = "计量单位")
    private String unit;

    @ApiModelProperty(value = "最低采购单价")
    private BigDecimal purchasePriceLow;

    @ApiModelProperty(value = "最高采购单价")
    private BigDecimal purchasePriceMax;

    @ApiModelProperty(value = "采购商品id")
    private Integer productId;

    @ApiModelProperty(value = "实际采购单价")
    private BigDecimal realPrice;

    @ApiModelProperty(value = "实际采购数量")
    private BigDecimal realQuantity;

    @ApiModelProperty(value = "实际采购总价")
    private BigDecimal realTotalPrice;

    @ApiModelProperty(value = "采购人id")
    private Integer staffId;

    @ApiModelProperty(value = "采购人姓名")
    private String staffName;

    @ApiModelProperty(value = "采购状态:0->发起中,1->采购中,2->已完成,3->已关闭")
    private Integer status;

    @ApiModelProperty(value = "采购订单创建者")
    private String operator;

    @ApiModelProperty(value = "创建者id")
    private Integer operatorId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty(value = "采购时间")
    private Date purchaseTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty(value = "结束时间")
    private Date endTime;

    @ApiModelProperty(value = "创建备注")
    private String cretaeCommnet;

    @ApiModelProperty(value = "采购备注")
    private String purchaseComment;

    @ApiModelProperty(value = "结束备注")
    private String endComment;

    private static final long serialVersionUID = 1L;
}