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

@ApiModel(value = "库存盘点")
@Table(name = "gms_stock_check")
@Data
public class GmsStockCheck implements Serializable {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;

    @ApiModelProperty(value = "仓库名")
    private String wareHouse;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "盘点人id")
    private Integer staffId;

    @ApiModelProperty(value = "员工姓名")
    private String staffName;

    @ApiModelProperty(value = "商品编号")
    private Integer productId;

    @ApiModelProperty(value = "商品名")
    private String productName;

    @ApiModelProperty(value = "盘点库存")
    private BigDecimal stock;

    @ApiModelProperty(value = "库存单位")
    private String unit;

    private static final long serialVersionUID = 1L;
}