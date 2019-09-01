package com.rsdtm.supermarket.mbg.query;

import com.rsdtm.supermarket.mbg.model.OmsOrderItem;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class OmsOrderItemParamVo extends OmsOrderItem {
    private Integer userId;
}
