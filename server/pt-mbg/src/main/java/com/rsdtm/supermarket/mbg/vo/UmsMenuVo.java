package com.rsdtm.supermarket.mbg.vo;

import com.rsdtm.supermarket.mbg.model.UmsMenu;
import com.rsdtm.supermarket.mbg.model.UmsRole;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@ApiModel("资源菜单VO")
@ToString(callSuper = true)
@Data
public class UmsMenuVo extends UmsMenu {
    List<UmsRole> roles;
}
