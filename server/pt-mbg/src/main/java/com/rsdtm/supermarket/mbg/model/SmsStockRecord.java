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

@ApiModel(value = "库存操作记录表")
@Table(name = "sms_stock-record")
@Data
public class SmsStockRecord implements Serializable {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;

    @ApiModelProperty(value = "商品id")
    private Integer productId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "类型:0->销售出库,1->采购入库,2->退货入库")
    private Byte type;

    @ApiModelProperty(value = "0->库存减少,1->库存增加")
    private Byte status;

    @ApiModelProperty(value = "库存数量")
    private BigDecimal quantity;

    @ApiModelProperty(value = "计量单位")
    private String unit;

    @ApiModelProperty(value = "上次库存量")
    private BigDecimal lastStock;

    @ApiModelProperty(value = "先有库存量")
    private BigDecimal stock;

    @ApiModelProperty(value = "库存损耗")
    private BigDecimal expendStock;

    @ApiModelProperty(value = "入库订单id")
    private Integer inId;

    @ApiModelProperty(value = "出库订单id")
    private Integer outId;

    @ApiModelProperty(value = "退货订单id")
    private Integer returnId;

    private static final long serialVersionUID = 1L;
}