package com.rsdtm.supermarket.bms.dao;

import com.rsdtm.supermarket.mbg.model.BmsVipProduct;
import com.rsdtm.supermarket.mbg.vo.VipProductVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface VipProductDao extends Mapper<BmsVipProduct> {
    @Select("select vp.*,v.type vip_name,p.name product_name,p.charge_unit unit from bms_vip_product vp,bms_vip v,bms_product p where vp.id = #{id} and vp.vip_id = v.id and vp.product_id = p.id")
    VipProductVo getVipProductVoById(Integer id);

    @Select("select vp.*,v.type vip_name,p.name product_name,p.charge_unit unit from bms_vip_product vp,bms_vip v,bms_product p where p.id = #{id} and vp.vip_id = v.id and vp.product_id = p.id")
    List<VipProductVo> getVipProductVoByProductId(Integer id);

    @Select("select count(1) from bms_vip_product where vip_id = #{vipId} and product_id=#{productId}")
    int checkExist(BmsVipProduct bmsVipProduct);

    @Select("select vp.*,v.type vip_name,p.name product_name,p.charge_unit unit from bms_vip_product vp,bms_vip v,bms_product p,bms_user u where p.id = #{productId} and v.id=u.vip_id  and vp.vip_id = u.vip_id and vp.product_id = #{productId} and u.id=#{userId}")
    VipProductVo getVipProductVoByProductIdAndUserId(@Param("productId") Integer productId, @Param("userId") Integer userId);
}
