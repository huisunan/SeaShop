package com.rsdtm.supermarket.mbg.query;

import com.rsdtm.supermarket.mbg.model.UmsUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class UmsUserParamVo extends UmsUser {
    private List<Integer> roles;
}
