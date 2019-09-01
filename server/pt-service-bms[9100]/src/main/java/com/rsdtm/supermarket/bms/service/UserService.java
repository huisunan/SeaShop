package com.rsdtm.supermarket.bms.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.rsdtm.supermarket.bms.dao.UserDao;
import com.rsdtm.supermarket.bms.dao.VipDao;
import com.rsdtm.supermarket.common.enums.ExceptionEnum;
import com.rsdtm.supermarket.common.exception.PtException;
import com.rsdtm.supermarket.common.vo.PageResult;
import com.rsdtm.supermarket.mbg.model.BmsUser;
import com.rsdtm.supermarket.mbg.query.BmsUserParamVo;
import com.rsdtm.supermarket.mbg.vo.BmsUserVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

    @Autowired
    VipDao vipDao;

    @Transactional
    public int addUser(BmsUser bmsUser) {

        if (bmsUser.getVipId()==null)
            throw new PtException(ExceptionEnum.VIP_ID_NOT_NULL);

        bmsUser.setId(null);
        bmsUser.setCreateTime(new Date());
        return userDao.insert(bmsUser);
    }

    @Transactional
    public int deleteUserById(Integer id) {
        if (!userDao.existsWithPrimaryKey(id))
            throw new PtException(ExceptionEnum.USER_NOT_EXIST);
        return userDao.deleteByPrimaryKey(id);
    }

    @Transactional
    public int updateUser(BmsUser bmsUser) {
        if (bmsUser.getId() == null)
            throw new PtException(ExceptionEnum.USER_ID_NOT_NULL);
        if (!userDao.existsWithPrimaryKey(bmsUser.getId()))
            throw new PtException(ExceptionEnum.USER_NOT_EXIST);

        bmsUser.setCreateTime(null);
        return userDao.updateByPrimaryKeySelective(bmsUser);
    }

    public BmsUserVo getUserById(Integer id) {
        if (!userDao.existsWithPrimaryKey(id))
            throw new PtException(ExceptionEnum.USER_NOT_EXIST);
        return userDao.getBmsUserVoById(id);
    }

    public PageResult<BmsUserVo> getUserByPage(int page, int rows, BmsUserParamVo paramVo) {
        PageHelper.startPage(page,rows);
        Example example = new Example(BmsUser.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotEmpty(paramVo.getName()))
            criteria.andLike("name","%"+paramVo.getName()+"%");
        if (paramVo.getStartAge()!=null)
            criteria.andGreaterThanOrEqualTo("age",paramVo.getStartAge());
        if(paramVo.getEndAge()!=null)
            criteria.andLessThanOrEqualTo("age", paramVo.getEndAge());
        if (paramVo.getSex()!=null)
            criteria.andEqualTo("sex", paramVo.getSex());
        if (paramVo.getAddress()!=null)
            criteria.andLike("address","%"+ paramVo.getAddress()+"%");
        if (paramVo.getVipId()!=null)
            criteria.andEqualTo("vipId", paramVo.getVipId());
        if (paramVo.getStartPoint()!=null)
            criteria.andGreaterThanOrEqualTo("point",paramVo.getStartPoint());
        if (paramVo.getEndPoint()!=null)
            criteria.andLessThanOrEqualTo("point",paramVo.getEndPoint());
        if (paramVo.getStartTime()!=null)
            criteria.andGreaterThanOrEqualTo("createTime",paramVo.getStartTime());
        if (paramVo.getEndTime()!=null)
            criteria.andLessThanOrEqualTo("createTime",paramVo.getEndTime());

        Page<BmsUser> bmsUsers = (Page<BmsUser>) userDao.selectByExample(example);
        List<BmsUser> bmsUserList = bmsUsers.getResult();
        List<BmsUserVo> collect = bmsUserList.stream().map(this::build).collect(Collectors.toList());

        PageResult<BmsUserVo> bmsUserVoPageResult = PageResult.restPage(collect);
        bmsUserVoPageResult.setTotalPage(bmsUsers.getPages());
        bmsUserVoPageResult.setTotal(bmsUsers.getTotal());
        bmsUserVoPageResult.setPageNum(page);
        bmsUserVoPageResult.setPageSize(rows);

        return bmsUserVoPageResult;
    }

    public BmsUserVo build(BmsUser bmsUser){
        BmsUserVo temp = new BmsUserVo();
        BeanUtils.copyProperties(bmsUser,temp);
        if (bmsUser.getVipId()!=null)
            temp.setVipName(vipDao.selectByPrimaryKey(bmsUser.getVipId()).getType());
        return temp;
    }
}
