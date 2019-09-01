package com.rsdtm.supermarket.ums.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.rsdtm.supermarket.common.enums.ExceptionEnum;
import com.rsdtm.supermarket.common.exception.PtException;
import com.rsdtm.supermarket.common.vo.PageResult;
import com.rsdtm.supermarket.mbg.model.UmsMenu;
import com.rsdtm.supermarket.mbg.query.UmsMenuParamVo;
import com.rsdtm.supermarket.mbg.vo.UmsMenuVo;
import com.rsdtm.supermarket.ums.dao.MenuDao;
import com.rsdtm.supermarket.ums.dao.RoleDao;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuService {
    @Autowired
    MenuDao menuDao;

    @Autowired
    RoleDao roleDao;

    @Transactional
    public int addMenu(UmsMenuParamVo umsMenu) {
        //TODO 参数校验
        umsMenu.setId(null);
        menuDao.insert(umsMenu);

        if (umsMenu.getRoles()!=null){

            Arrays.asList(umsMenu.getRoles()).forEach(r->{
                if (roleDao.isExistRoleMenu(r,umsMenu.getId())==0)
                    roleDao.addRoleMenu(r,umsMenu.getId());
            });
        }

        return 1;
    }

    @Transactional
    public int deleteMenu(Integer id) {
        checkExist(id);
        return menuDao.deleteByPrimaryKey(id);
    }

    private void checkExist(Integer id) {
        if (!menuDao.existsWithPrimaryKey(id))
            throw new PtException(ExceptionEnum.MENU_NOT_EXIST);
    }

    @Transactional
    public int updateMenu(UmsMenuParamVo umsMenu) {
        if (umsMenu.getId() == null)
            throw new PtException(ExceptionEnum.MENU_ID_NOT_NULL);

        if (!menuDao.existsWithPrimaryKey(umsMenu.getId()))
            throw new PtException(ExceptionEnum.MENU_NOT_EXIST);

        if (umsMenu.getRoles()!=null)
        {
            roleDao.deleteAllRoleMenu(umsMenu.getId());

            Arrays.asList(umsMenu.getRoles()).forEach(r->{
                if (roleDao.isExistRoleMenu(r,umsMenu.getId())==0)
                    roleDao.addRoleMenu(r,umsMenu.getId());
            });
        }

        return menuDao.updateByPrimaryKeySelective(umsMenu);
    }

    public UmsMenuVo getMenuById(Integer id) {
        if (!menuDao.existsWithPrimaryKey( id))
            throw new PtException(ExceptionEnum.MENU_NOT_EXIST);

        return (UmsMenuVo) menuDao.selectByPrimaryKey(id);
    }

    private UmsMenuVo builder(UmsMenu umsMenu){
        UmsMenuVo temp = new UmsMenuVo();
        BeanUtils.copyProperties(umsMenu,temp);
        temp.setRoles(roleDao.queryByRoleId(temp.getId()));
        return temp;
    }

    public PageResult<UmsMenuVo> getMenuByPage(int page, int rows) {
        PageHelper.startPage(page,rows);
        Page<UmsMenu> umsMenus = (Page<UmsMenu>) menuDao.selectAll();
        List<UmsMenuVo> collect = umsMenus.stream().map(this::builder).collect(Collectors.toList());
        PageResult<UmsMenuVo> result = PageResult.restPage(collect);
        result.setTotalPage(umsMenus.getPages());
        result.setTotal(umsMenus.getTotal());
        result.setPageNum(page);
        result.setPageSize(rows);
        return result;
    }

    public List<UmsMenuVo> getAll() {
        List<UmsMenu> menus = menuDao.selectAll();
        return menus.stream().map(this::builder).collect(Collectors.toList());
    }
}
