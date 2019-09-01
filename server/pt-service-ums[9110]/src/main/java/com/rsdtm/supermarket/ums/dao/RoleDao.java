package com.rsdtm.supermarket.ums.dao;

import com.rsdtm.supermarket.mbg.model.UmsRole;
import com.rsdtm.supermarket.mbg.vo.UmsRoleVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import javax.management.relation.Role;
import java.util.List;

public interface RoleDao extends Mapper<UmsRole> {
    @Select("select * from ums_role where id in (select role_id from ums_user_role where user_id = #{id})")
    List<UmsRole> queryByUserId(Integer id);

    @Select("select * from ums_role where id in (select role_id from ums_role_menu where menu_id = #{id})")
    List<UmsRole> queryByRoleId(Integer id);

    @Select("select count(1) from ums_role_menu where role_id = #{roleId} and menu_id = #{menuId}")
    int isExistRoleMenu(@Param("roleId") Integer roleId, @Param("menuId") Integer menuId);

    @Insert("insert into ums_role_menu(role_id,menu_id) values(#{roleId},#{menuId})")
    int addRoleMenu(@Param("roleId") Integer roleId, @Param("menuId") Integer menuId);

    @Delete("delete from ums_role_menu where role_id = #{roleId} and menu_id = #{menuId}")
    int deleteRoleMenu(@Param("roleId") Integer roleId, @Param("menuId") Integer menuId);

    @Delete("delete from ums_role_menu where  menu_id = #{menuId}")
    int deleteAllRoleMenu( @Param("menuId") Integer menuId);

}
