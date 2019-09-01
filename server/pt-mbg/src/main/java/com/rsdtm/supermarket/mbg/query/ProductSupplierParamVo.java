package com.rsdtm.supermarket.mbg.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@ApiModel("供应商查询参数")
public class ProductSupplierParamVo {

    @ApiModelProperty("商品ID")
    private String productId;

    @ApiModelProperty("供应商ID")
    private String supplierId;

    @ApiModelProperty("创建时间的范围,起始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startDate;

    @ApiModelProperty("创建时间的范围,终止时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endDate;

    @ApiModelProperty("操作人，模糊查询")
    private String operator;
}
