package com.rsdtm.supermarket.mbg.vo;

import com.rsdtm.supermarket.mbg.model.BmsProduct;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@ApiModel("产品VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProductVo extends BmsProduct {
    @ApiModelProperty("分类名")
    private String categoryName;

    @ApiModelProperty("分类节点列表")
    private List<Integer> categoryNodeList;
//
//    @ApiModelProperty("供应商名")
//    private String supplierName;


}
