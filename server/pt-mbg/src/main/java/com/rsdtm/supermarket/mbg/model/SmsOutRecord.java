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

@ApiModel(value = "出库记录")
@Table(name = "sms_out_record")
@Data
public class SmsOutRecord implements Serializable {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;

    @ApiModelProperty(value = "仓库名")
    private String storeHouse;

    @ApiModelProperty(value = "出库商品id")
    private Integer productId;

    @ApiModelProperty(value = "出库数量")
    private BigDecimal quantity;

    @ApiModelProperty(value = "单位")
    private String unit;

    @ApiModelProperty(value = "商品名")
    private String productName;

    @ApiModelProperty(value = "申请人")
    private String applicant;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    private static final long serialVersionUID = 1L;
}