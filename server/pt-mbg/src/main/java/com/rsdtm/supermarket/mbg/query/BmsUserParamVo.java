package com.rsdtm.supermarket.mbg.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@ApiModel("分页查询的客户参数类")
public class BmsUserParamVo {

    @ApiModelProperty("根据姓名模糊查询")
    private String name;

    @ApiModelProperty(value = "根据年龄范围查询，起始年龄")
    private Integer startAge;

    @ApiModelProperty(value = "根据年龄范围查询，终止年龄")
    private Integer endAge;

    @ApiModelProperty(value = "性别查询,性别：0->女，1->男")
    private Byte sex;


    @ApiModelProperty(value = "住址，模糊查询")
    private String address;

    @ApiModelProperty(value = "根据会员查询")
    private Integer vipId;

    @ApiModelProperty(value = "根据积分范围查询，起始积分")
    private Integer startPoint;

    @ApiModelProperty(value = "根据积分范围查询，终止积分")
    private Integer endPoint;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "根据创建时间查询,起始时间")
    private Date startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "根据创建时间查询,终止时间")
    private Date endTime;
}
