package com.rsdtm.supermarket.mbg.vo;

import com.rsdtm.supermarket.mbg.model.BmsProductSupplier;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("商品供应商VO")
@ToString(callSuper = true)
public class ProductSupplierVo extends BmsProductSupplier {
    @ApiModelProperty("产品名称")
    private String productName;
    @ApiModelProperty("供应商名称")
    private String supplierName;
}
