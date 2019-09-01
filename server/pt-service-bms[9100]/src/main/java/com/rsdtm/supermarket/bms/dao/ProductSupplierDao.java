package com.rsdtm.supermarket.bms.dao;

import com.rsdtm.supermarket.mbg.model.BmsProductSupplier;
import com.rsdtm.supermarket.mbg.vo.ProductSupplierVo;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

public interface ProductSupplierDao extends Mapper<BmsProductSupplier> {

    @Select("select ps.*,p.name product_name,s.name supplier_name from bms_product_supplier ps,bms_product p,bms_supplier s where ps.id = #{id} and ps.product_id = p.id and ps.supplier_id = s.id")
    ProductSupplierVo getProductSupplierById(Integer id);

}
