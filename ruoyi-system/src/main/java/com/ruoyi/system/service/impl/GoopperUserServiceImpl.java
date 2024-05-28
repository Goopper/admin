package com.ruoyi.system.service.impl;

import java.util.Collections;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.GoopperUserMapper;
import com.ruoyi.system.domain.GoopperUser;
import com.ruoyi.system.service.IGoopperUserService;
import com.ruoyi.common.core.text.Convert;

/**
 * 用户管理Service业务层处理
 * 
 * @author CHENPrime
 * @date 2024-05-28
 */
@Service
public class GoopperUserServiceImpl implements IGoopperUserService 
{
    @Autowired
    private GoopperUserMapper goopperUserMapper;

    /**
     * 查询用户管理
     * 
     * @param id 用户管理主键
     * @return 用户管理
     */
    @Override
    public GoopperUser selectGoopperUserById(Long id)
    {
        return goopperUserMapper.selectGoopperUserById(id);
    }

    /**
     * 查询用户管理列表
     * 
     * @param goopperUser 用户管理
     * @return 用户管理
     */
    @Override
    public List<GoopperUser> selectGoopperUserList(GoopperUser goopperUser)
    {
        return goopperUserMapper.selectGoopperUserList(goopperUser);
    }

    /**
     * 新增用户管理
     * 
     * @param goopperUser 用户管理
     * @return 结果
     */
    @Override
    public int insertGoopperUser(GoopperUser goopperUser)
    {
        goopperUser.setCreateTime(DateUtils.getNowDate());
        return goopperUserMapper.insertGoopperUser(goopperUser);
    }

    /**
     * 修改用户管理
     * 
     * @param goopperUser 用户管理
     * @return 结果
     */
    @Override
    public int updateGoopperUser(GoopperUser goopperUser)
    {
        return goopperUserMapper.updateGoopperUser(goopperUser);
    }

    /**
     * 批量删除用户管理
     * 
     * @param ids 需要删除的用户管理主键
     * @return 结果
     */
    @Override
    public int deleteGoopperUserByIds(String ids)
    {
        return goopperUserMapper.deleteGoopperUserByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除用户管理信息
     * 
     * @param id 用户管理主键
     * @return 结果
     */
    @Override
    public int deleteGoopperUserById(Long id)
    {
        return goopperUserMapper.deleteGoopperUserById(id);
    }

    @Override
    public List<GoopperUser> selectGoopperTeacherList() {
        return goopperUserMapper.selectGoopperTeacherList();
    }
}
