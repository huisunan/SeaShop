package com.rsdtm.supermarket.ums.dao;

import com.rsdtm.supermarket.mbg.model.UmsMenu;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface MenuDao extends Mapper<UmsMenu> {
    @Select("select * from ums_menu where id in(select menu_id from ums_role_menu where role_id = #{id})")
    List<UmsMenu> getByRoleId(Integer id);
}
