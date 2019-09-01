package com.rsdtm.supermarket.mbg.vo;

import com.rsdtm.supermarket.mbg.model.BmsUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ApiModel("客户VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BmsUserVo extends BmsUser {
    @ApiModelProperty("vip的名称")
    private String vipName;
}
