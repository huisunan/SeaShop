package com.rsdtm.supermarket.mbg.query;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@ApiModel("分页查询的员工参数类")
public class StaffParamVo {

    @ApiModelProperty(value = "姓名,模糊查询")
    private String name;

    @ApiModelProperty(value = "根据职务id查询")
    private Integer staffJobId;

    @ApiModelProperty(value = "根据年龄范围查询,起始年龄")
    private Integer startAge;

    @ApiModelProperty(value = "根据年龄范围查询,终止年龄")
    private Integer endAge;

    @ApiModelProperty(value = "性别:0->女,1->男")
    private Byte sex;

    @ApiModelProperty(value = "地址,模糊查询")
    private String address;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "入职时间，起始时间")
    private Date startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "入职时间，终止时间")
    private Date endTime;
}
