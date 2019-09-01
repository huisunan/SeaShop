package com.rsdtm.supermarket.mbg.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@ApiModel(value = "供应商查询类")
public class SupplierParamVo {
    @ApiModelProperty(value = "模糊查询的名字")
    private String name;

    @ApiModelProperty(value = "模糊查询的地址")
    private String address;

    @ApiModelProperty(value = "模糊查询的负责人姓名")
    private String leader;

    @ApiModelProperty(value = "根据范围时间查询，起始时间 格式(yyyy-MM-dd HH:mm:ss)")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @ApiModelProperty(value = "根据范围时间查询，终止时间 格式(yyyy-MM-dd HH:mm:ss)")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
}
