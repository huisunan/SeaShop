package com.rsdtm.supermarket.mbg.vo;

import com.rsdtm.supermarket.mbg.model.BmsStaffJob;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ApiModel("员工职务VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BmsStaffJobVo extends BmsStaffJob {
    private String staffClass;
}
