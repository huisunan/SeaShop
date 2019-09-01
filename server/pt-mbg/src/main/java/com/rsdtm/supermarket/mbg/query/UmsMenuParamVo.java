package com.rsdtm.supermarket.mbg.query;

import com.rsdtm.supermarket.mbg.model.UmsMenu;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
public class UmsMenuParamVo extends UmsMenu {
    private Integer[] roles;
}
