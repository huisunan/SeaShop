package com.rsdtm.supermarket.bms.dao;

import com.rsdtm.supermarket.mbg.model.BmsProduct;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ProductDao extends Mapper<BmsProduct> {
    @Select("select * from bms_product where id in (select product_id from bms_product_supplier where supplier_id = #{id})")
    List<BmsProduct> queryProductBySupplierId(@Param("id") Integer id);
}
