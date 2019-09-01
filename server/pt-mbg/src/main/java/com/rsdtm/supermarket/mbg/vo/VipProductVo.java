package com.rsdtm.supermarket.mbg.vo;


import com.rsdtm.supermarket.mbg.model.BmsVipProduct;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("会员商品VO")
@ToString(callSuper = true)
public class VipProductVo extends BmsVipProduct {
    @ApiModelProperty("vip名称")
    private String vipName;
    @ApiModelProperty("商品名称")
    private String productName;
    @ApiModelProperty("计量单位")
    private String unit;
}
