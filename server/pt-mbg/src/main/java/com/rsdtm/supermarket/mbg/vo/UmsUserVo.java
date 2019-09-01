package com.rsdtm.supermarket.mbg.vo;

import com.rsdtm.supermarket.mbg.model.UmsRole;
import com.rsdtm.supermarket.mbg.model.UmsUser;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("用户VO")
@ToString(callSuper = true)
public class UmsUserVo extends UmsUser {
    List<UmsRole> roles;
}
