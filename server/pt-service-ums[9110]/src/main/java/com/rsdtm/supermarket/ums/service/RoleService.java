package com.rsdtm.supermarket.ums.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.rsdtm.supermarket.common.enums.ExceptionEnum;
import com.rsdtm.supermarket.common.exception.PtException;
import com.rsdtm.supermarket.common.vo.PageResult;
import com.rsdtm.supermarket.mbg.model.UmsMenu;
import com.rsdtm.supermarket.mbg.model.UmsRole;
import com.rsdtm.supermarket.mbg.vo.UmsRoleVo;
import com.rsdtm.supermarket.ums.dao.MenuDao;
import com.rsdtm.supermarket.ums.dao.RoleDao;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService {
    @Autowired
    RoleDao roleDao;

    @Autowired
    MenuDao menuDao;

    @Transactional
    public int addRole(UmsRole umsRole) {
        if (StringUtils.isEmpty(umsRole.getName())) {
            throw new PtException(ExceptionEnum.ROLE_NAME_NOT_NULL);
        }
        if (StringUtils.isEmpty(umsRole.getNameZh()))
            throw new PtException(ExceptionEnum.ROLE_ZH_NAME_NOT_NULL);
        umsRole.setId(null);
        return roleDao.insert(umsRole);
    }

    @Transactional
    public int deleteRoleById(Integer id) {
        if (!roleDao.existsWithPrimaryKey(id))
            throw new PtException(ExceptionEnum.ROLE_NOT_EXIST);
        return roleDao.deleteByPrimaryKey(id);
    }

    @Transactional
    public int updateRole(UmsRole umsRole) {
        if (umsRole.getId() == null)
            throw new PtException(ExceptionEnum.ROLE_ID_NOT_NULL);
        if (!roleDao.existsWithPrimaryKey(umsRole.getId()))
            throw new PtException(ExceptionEnum.ROLE_NOT_EXIST);
        return roleDao.updateByPrimaryKeySelective(umsRole);
    }

    public UmsRoleVo getRoleByID(Integer id) {
        //TODO
        return null;
    }

    @Transactional
    public int addRoleMenu(Integer roleId, Integer menuId) {
        checkRoleMenu(roleId, menuId);
        if (roleDao.isExistRoleMenu(roleId,menuId) == 1)
            throw new PtException(ExceptionEnum.ROLE_MENU_IS_EXIST);
        return roleDao.addRoleMenu(roleId,menuId);
    }

    @Transactional
    public int deleteRoleMenu(Integer roleId, Integer menuId) {
        checkRoleMenu(roleId, menuId);
        if (roleDao.isExistRoleMenu(roleId,menuId) != 1)
            throw new PtException(ExceptionEnum.ROLE_MENU_NOT_EXIST);
        return roleDao.deleteRoleMenu(roleId,menuId);
    }

    private void checkRoleMenu(Integer roleId, Integer menuId) {
        if (!roleDao.existsWithPrimaryKey(roleId))
            throw new PtException(ExceptionEnum.ROLE_NOT_EXIST);
        if (!menuDao.existsWithPrimaryKey(menuId))
            throw new PtException(ExceptionEnum.MENU_NOT_EXIST);
    }

    private UmsRoleVo builder(UmsRole umsRole){
        UmsRoleVo temp = new UmsRoleVo();
        BeanUtils.copyProperties(umsRole,temp);
        List<UmsMenu> menu = menuDao.getByRoleId(umsRole.getId());
        temp.setMenus(menu);
        return temp;
    }

    public PageResult<UmsRoleVo> getRoleByPage(int page, int rows) {
        PageHelper.startPage(page,rows);

        Page<UmsRole> umsRoles = (Page<UmsRole>) roleDao.selectAll();
        List<UmsRoleVo> collect = umsRoles.stream().map(this::builder).collect(Collectors.toList());
        PageResult<UmsRoleVo> pageResult = PageResult.restPage(collect);
        pageResult.setTotalPage(umsRoles.getPages());
        pageResult.setTotal(umsRoles.getTotal());
        pageResult.setPageNum(page);
        pageResult.setPageSize(rows);
        return pageResult;


    }

    public List<UmsRole> getAll() {
        return this.roleDao.selectAll();
    }
}
