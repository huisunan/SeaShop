package com.rsdtm.supermarket.mbg.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel("分页查询的商品参数类")
public class ProductParamVo {

    @ApiModelProperty(value = "产品名称,模糊查询")
    private String name;

    @ApiModelProperty(value = "查询是否上架，0->未上架；1->上架")
    private Byte enable;

    @ApiModelProperty(value = "分类id")
    private Integer productCategoryId;

    @ApiModelProperty(value = "是否排序,根据权重排序")
    private Boolean sort;

    @ApiModelProperty(value = "根据销量范围查询,起始销量(>=)")
    private BigDecimal startSale;

    @ApiModelProperty(value = "根据销量范围查询,终止销量(<=)")
    private BigDecimal endSale;

    @ApiModelProperty(value = "根据价格范围查询,起始价格(>=)")
    private BigDecimal startPrice;

    @ApiModelProperty(value = "根据价格范围查询,终止价格(<=)")
    private BigDecimal endPrice;

    @ApiModelProperty(value = "根据优惠价格范围查询,起始优惠价格(>=)")
    private BigDecimal startPromotionPrice;

    @ApiModelProperty(value = "根据优惠价格范围查询,终止优惠价格(<=)")
    private BigDecimal endPromotionPrice;

    @ApiModelProperty(value = "根据积分范围查询,起始积分(>=)")
    private Integer startGiftPoint;

    @ApiModelProperty(value = "根据积分范围查询,终止积分(<=)")
    private Integer endGiftPoint;

    @ApiModelProperty(value = "根据库存范围查询,起始库存(>=)")
    private BigDecimal startStock;

    @ApiModelProperty(value = "根据库存范围查询,终止库存(<=)")
    private BigDecimal endStock;

    @ApiModelProperty(value = "根据库存预警范围查询,起始库存预警(>=)")
    private BigDecimal startLowStock;

    @ApiModelProperty(value = "根据库存预警范围查询,终止库存预警(<=)")
    private BigDecimal endLowStock;

    @ApiModelProperty(value = "筛选库存低于预警库存的")
    private Boolean stockLessLowStock;
}
