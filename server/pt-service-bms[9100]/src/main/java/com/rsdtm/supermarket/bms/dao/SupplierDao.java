package com.rsdtm.supermarket.bms.dao;

import com.rsdtm.supermarket.mbg.model.BmsSupplier;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SupplierDao extends Mapper<BmsSupplier> {
    @Select("select * from bms_supplier where id in(select supplier_id from bms_product_supplier where product_id = #{id})")
    List<BmsSupplier> querySupplierByProductId(@Param("id") Integer id);
}
