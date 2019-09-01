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

@ApiModel(value = "订单退货申请")
@Table(name = "oms_return_apply")
@Data
public class OmsReturnApply implements Serializable {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;

    @ApiModelProperty(value = "订单退货编码")
    private String returnSn;

    @ApiModelProperty(value = "订单id")
    private Integer orderId;

    @ApiModelProperty(value = "商品id")
    private Integer productId;

    @ApiModelProperty(value = "订单编号")
    private String orderSn;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty(value = "申请时间")
    private Date createTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty(value = "处理时间")
    private Date endTime;

    @ApiModelProperty(value = "客户姓名")
    private String userName;

    @ApiModelProperty(value = "客户id")
    private Integer userId;

    @ApiModelProperty(value = "退货金额")
    private BigDecimal returnAmount;

    @ApiModelProperty(value = "退货人姓名")
    private String renturnName;

    @ApiModelProperty(value = "退货人电话")
    private String returnPhone;

    @ApiModelProperty(value = "申请状态:0->待处理,1->退货中,2->已完成,3->已拒绝")
    private Byte status;

    @ApiModelProperty(value = "退货数量")
    private BigDecimal productAmount;

    @ApiModelProperty(value = "计量单位")
    private String productUnit;

    @ApiModelProperty(value = "商品单价")
    private BigDecimal productPrice;

    @ApiModelProperty(value = "实际支付单价")
    private BigDecimal productRealPrice;

    @ApiModelProperty(value = "原因")
    private Integer reason;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "凭证照片，多个用','隔开")
    private String proofPics;

    @ApiModelProperty(value = "处理备注")
    private String handleComment;

    @ApiModelProperty(value = "处理人")
    private String handleMan;

    @ApiModelProperty(value = "处理人员工号")
    private Integer handleManId;

    @ApiModelProperty(value = "收货人")
    private String receiveMan;

    @ApiModelProperty(value = "收货人员工id")
    private Integer receiveManId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty(value = "收货时间")
    private Date receiveTime;

    @ApiModelProperty(value = "收货备注")
    private String receiveNote;

    private static final long serialVersionUID = 1L;
}