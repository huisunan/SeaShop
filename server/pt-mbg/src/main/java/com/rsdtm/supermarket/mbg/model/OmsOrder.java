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

@ApiModel(value = "销售订单")
@Table(name = "oms_order")
@Data
public class OmsOrder implements Serializable {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;

    @ApiModelProperty(value = "订单编号")
    private String orderSn;

    @ApiModelProperty(value = "客户id")
    private Integer userId;

    @ApiModelProperty(value = "员工id")
    private Integer staffId;

    @ApiModelProperty(value = "客户名")
    private String userName;

    @ApiModelProperty(value = "员工名")
    private String staffName;

    @ApiModelProperty(value = "订单总金额")
    private BigDecimal totalAmount;

    @ApiModelProperty(value = "实际支付金额")
    private BigDecimal payAmount;

    @ApiModelProperty(value = "会员优惠金额")
    private BigDecimal vipAmount;

    @ApiModelProperty(value = "支付类型:0->支付宝;1->微信;2->银行卡;3->现金")
    private Byte payType;

    @ApiModelProperty(value = "赠送积分")
    private Integer giftPoint;

    @ApiModelProperty(value = "发票抬头")
    private String billHeader;

    @ApiModelProperty(value = "发票内容")
    private String billContent;

    @ApiModelProperty(value = "收票人电话")
    private String billReceiverPhone;

    private static final long serialVersionUID = 1L;
}