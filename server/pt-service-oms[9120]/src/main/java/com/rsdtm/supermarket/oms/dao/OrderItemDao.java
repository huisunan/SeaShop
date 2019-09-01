package com.rsdtm.supermarket.oms.dao;

import com.rsdtm.supermarket.mbg.model.OmsOrder;
import com.rsdtm.supermarket.mbg.model.OmsOrderItem;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

public interface OrderItemDao extends Mapper<OmsOrderItem> {
    @Select("select * from oms_order_item where order_id = #{orderId} and product_id = #{productId}")
    OmsOrderItem getByOidAndPid(@Param("orderId")Integer orderId,@Param("productId")Integer productId);
}
