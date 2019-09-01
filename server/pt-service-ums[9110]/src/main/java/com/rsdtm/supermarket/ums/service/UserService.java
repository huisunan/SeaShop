package com.rsdtm.supermarket.ums.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.netflix.discovery.converters.Auto;
import com.rsdtm.supermarket.common.enums.ExceptionEnum;
import com.rsdtm.supermarket.common.exception.PtException;
import com.rsdtm.supermarket.common.vo.PageResult;
import com.rsdtm.supermarket.mbg.model.UmsUser;
import com.rsdtm.supermarket.mbg.query.UmsUserParamVo;
import com.rsdtm.supermarket.mbg.vo.UmsUserVo;
import com.rsdtm.supermarket.ums.dao.RoleDao;
import com.rsdtm.supermarket.ums.dao.UserDao;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

    @Autowired
    RoleDao roleDao;

    @Autowired
    BCryptPasswordEncoder encoder;
    @Transactional
    public int addUser(UmsUserParamVo umsUser) {
        if (StringUtils.isEmpty(umsUser.getName()))
            throw new PtException(ExceptionEnum.USER_NAME_NOT_NULL);
        if (StringUtils.isEmpty(umsUser.getPassword()))
            throw new PtException(ExceptionEnum.USER_PASSWORD_NOT_NULL);
        Integer nameExist = userDao.isNameExist(umsUser.getName());
        if (nameExist == 1)
            throw new PtException(ExceptionEnum.USER_NAME_EXIST);
        umsUser.setId(null);
        umsUser.setEnable((byte) 1);
        String encode = encoder.encode(umsUser.getPassword());
        umsUser.setPassword(encode);
        userDao.insert(umsUser);
        if (umsUser.getRoles()!=null){
            umsUser.getRoles().stream().forEach(rid->addUserRole(umsUser.getId(),rid));
//            umsUser.getRoles().stream().map(rid->addUserRole(umsUser.getId(),rid));
        }
        return 1;
    }

    @Transactional
    public int deleteUserById(Integer id) {
        if (!userDao.existsWithPrimaryKey(id))
            throw new PtException(ExceptionEnum.USER_NOT_EXIST);

        userDao.deleteRoleByUserId(id);

        return userDao.deleteByPrimaryKey(id);

    }

    @Transactional
    public int updateUser(UmsUser umsUser) {
        if (umsUser.getId() == null)
            throw new PtException(ExceptionEnum.USER_ID_NOT_NULL);
        if (StringUtils.isEmpty(umsUser.getName()))
            throw new PtException(ExceptionEnum.USER_NAME_NOT_NULL);
        if(userDao.isNameExist(umsUser.getName()) == 1)
            throw new PtException(ExceptionEnum.USER_NAME_EXIST);
        umsUser.setPassword(null);
        return userDao.updateByPrimaryKeySelective(umsUser);
    }

    private UmsUserVo builder( UmsUser umsUser){
        UmsUserVo temp = new UmsUserVo();
        BeanUtils.copyProperties(umsUser,temp);

        temp.setRoles(roleDao.queryByUserId(umsUser.getId()));
//        temp.setPassword(null);
        return temp;
    }

    public UmsUserVo getUserById(Integer id) {
        if (!userDao.existsWithPrimaryKey(id))
            throw new PtException(ExceptionEnum.USER_NOT_EXIST);
        UmsUser umsUser = userDao.selectByPrimaryKey(id);
        return builder(umsUser);
    }

    public PageResult<UmsUserVo> getUserByPage(int page, int rows, UmsUser umsUser) {
        PageHelper.startPage(page,rows);
        Example example = new Example(UmsUser.class);
        Example.Criteria criteria = example.createCriteria();

        if (StringUtils.isNotEmpty(umsUser.getName()))
            criteria.andLike("name","%"+ umsUser.getName()+"%");
        if (umsUser.getEnable() != null)
            criteria.andEqualTo("enable",umsUser.getEnable());
        if (umsUser.getStaffId() != null)
            criteria.andEqualTo("staffId", umsUser.getStaffId());

        example.excludeProperties("password");
        Page<UmsUser> umsUsers = (Page<UmsUser>) userDao.selectByExample(example);

        List<UmsUserVo> collect = umsUsers.stream().map(this::builder).collect(Collectors.toList());
        PageResult<UmsUserVo> result = PageResult.restPage(collect);

        result.setTotalPage(umsUsers.getPages());
        result.setTotal(umsUsers.getTotal());
        result.setPageNum(page);
        result.setPageSize(rows);

        return result;
    }

    public UmsUserVo getUserByName(String name) {
        if (StringUtils.isEmpty(name)) {
            throw new PtException(ExceptionEnum.USER_NAME_NOT_NULL);
        }
        if (userDao.isNameExist(name) != 1)
            throw new PtException(ExceptionEnum.USER_NOT_EXIST);
        UmsUser temp = new UmsUser();
        temp.setName(name);
        UmsUser umsUser = userDao.selectOne(temp);
//        umsUser.setPassword(null);

        return builder(umsUser);
    }

    @Transactional
    public int updateUserPassWord(Integer id, String oldPassword, String newPassword) {
        if (!userDao.existsWithPrimaryKey(id))
            throw new PtException(ExceptionEnum.USER_NOT_EXIST);
        UmsUser umsUser = userDao.selectByPrimaryKey(id);


        if (!encoder.matches(oldPassword,umsUser.getPassword())) {
            throw new PtException(ExceptionEnum.USER_PASSWORD_WRONG);
        }


        umsUser.setPassword(encoder.encode(newPassword));
        return userDao.updateByPrimaryKeySelective(umsUser);

    }

    @Transactional
    public int addUserRole(Integer userId, Integer roleId) {
        checkUserAndRole(userId, roleId);
        if (userDao.isExistUserRole(userId,roleId)>0)
            throw new PtException(ExceptionEnum.USER_ROLE_IS_EXIST);
        return userDao.addUserRole(userId,roleId);
    }

    private void checkUserAndRole(Integer userId, Integer roleId) {
        if (!userDao.existsWithPrimaryKey(userId))
            throw new PtException(ExceptionEnum.USER_NOT_EXIST);
        if (!roleDao.existsWithPrimaryKey(roleId))
            throw new PtException(ExceptionEnum.ROLE_NOT_EXIST);
    }

    @Transactional
    public int deleteUserRole(Integer userId, Integer roleId) {
        checkUserAndRole(userId, roleId);
        if (userDao.isExistUserRole(userId,roleId)!=1)
            throw new PtException(ExceptionEnum.USER_ROLE_IS_NOT_EXIST);
        return userDao.deleteUserRole(userId,roleId);
    }

    public UmsUser getByUsernameAndPassword(String username, String password) {
        UmsUser userByName = this.getUserByName(username);
        if (!encoder.matches(password,userByName.getPassword()))
            throw new PtException(ExceptionEnum.USER_NOT_EXIST);
        return userByName;
    }
}
