package com.rsdtm.supermarket.ums.dao;

import com.rsdtm.supermarket.mbg.model.UmsUser;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserDao extends Mapper<UmsUser> {

    //查询用户名是否存在
    @Select("select count(1) from ums_user where name = #{name}")
    Integer isNameExist(String name);

    //根据角色ID查询用户
    @Select("select id,name from ums_user where id in " +
            "(select user_id from ums_user_role where role_id = #{id})")
    List<UmsUser> queryByRoleId(Integer id);


    @Select("select count(1) from ums_user_role where user_id = #{userId} and role_id=#{roleId}")
    int isExistUserRole(@Param("userId") Integer userId, @Param("roleId") Integer roleId);

    @Insert("insert into ums_user_role(user_id,role_id) values(#{userId},#{roleId})")
    int addUserRole(@Param("userId") Integer userId, @Param("roleId") Integer roleId);

    @Delete("delete from ums_user_role where user_id = #{userId} and role_id=#{roleId}")
    int deleteUserRole(@Param("userId") Integer userId, @Param("roleId") Integer roleId);

    @Delete("delete from ums_user_role where user_id = #{userId}")
    void deleteRoleByUserId(@Param("userId") Integer id);
}
