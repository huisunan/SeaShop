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

@ApiModel(value = "入库记录")
@Table(name = "sms_in_record")
@Data
public class SmsInRecord implements Serializable {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;

    @ApiModelProperty(value = "sn编号")
    private String inSn;

    @ApiModelProperty(value = "仓库名")
    private String storeHouse;

    @ApiModelProperty(value = "供应商id")
    private Integer supplier;

    @ApiModelProperty(value = "供应商名称")
    private String supplierName;

    @ApiModelProperty(value = "商品id")
    private Integer productId;

    @ApiModelProperty(value = "商品名称")
    private String productName;

    @ApiModelProperty(value = "单位")
    private String unit;

    @ApiModelProperty(value = "数量")
    private BigDecimal qunaity;

    @ApiModelProperty(value = "检查员")
    private String operator;

    @ApiModelProperty(value = "员工id")
    private Integer staffId;

    @ApiModelProperty(value = "状态:0->采购,1->退货")
    private Byte type;

    @ApiModelProperty(value = "关联的采购订单")
    private Integer purchaseId;

    @ApiModelProperty(value = "退货订单id")
    private Integer returnId;

    @ApiModelProperty(value = "备注")
    private String comment;

    private static final long serialVersionUID = 1L;
}