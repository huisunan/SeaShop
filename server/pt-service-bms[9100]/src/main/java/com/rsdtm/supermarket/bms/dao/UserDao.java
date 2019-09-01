package com.rsdtm.supermarket.bms.dao;

import com.rsdtm.supermarket.mbg.model.BmsUser;
import com.rsdtm.supermarket.mbg.vo.BmsUserVo;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

public interface UserDao extends Mapper<BmsUser> {

    @Select("select u.*,v.type vip_name  from bms_user u,bms_vip v where u.id = #{id} and v.id = u.vip_id")
    BmsUserVo getBmsUserVoById(Integer id);
}
