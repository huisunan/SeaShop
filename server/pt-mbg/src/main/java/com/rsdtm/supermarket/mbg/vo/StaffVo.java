package com.rsdtm.supermarket.mbg.vo;

import com.rsdtm.supermarket.mbg.model.BmsStaff;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ApiModel("员工职务VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class StaffVo extends BmsStaff {
    private String staffJobName;
}
