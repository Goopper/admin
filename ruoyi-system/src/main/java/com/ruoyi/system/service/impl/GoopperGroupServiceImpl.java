package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.GoopperGroupMapper;
import com.ruoyi.system.domain.GoopperGroup;
import com.ruoyi.system.service.IGoopperGroupService;
import com.ruoyi.common.core.text.Convert;

/**
 * 小组/班级管理Service业务层处理
 * 
 * @author CHENPrime
 * @date 2024-05-28
 */
@Service
public class GoopperGroupServiceImpl implements IGoopperGroupService 
{
    @Autowired
    private GoopperGroupMapper goopperGroupMapper;

    /**
     * 查询小组/班级管理
     * 
     * @param id 小组/班级管理主键
     * @return 小组/班级管理
     */
    @Override
    public GoopperGroup selectGoopperGroupById(Long id)
    {
        return goopperGroupMapper.selectGoopperGroupById(id);
    }

    /**
     * 查询小组/班级管理列表
     * 
     * @param goopperGroup 小组/班级管理
     * @return 小组/班级管理
     */
    @Override
    public List<GoopperGroup> selectGoopperGroupList(GoopperGroup goopperGroup)
    {
        return goopperGroupMapper.selectGoopperGroupList(goopperGroup);
    }

    /**
     * 新增小组/班级管理
     * 
     * @param goopperGroup 小组/班级管理
     * @return 结果
     */
    @Override
    public int insertGoopperGroup(GoopperGroup goopperGroup)
    {
        goopperGroup.setCreateTime(DateUtils.getNowDate());
        return goopperGroupMapper.insertGoopperGroup(goopperGroup);
    }

    /**
     * 修改小组/班级管理
     * 
     * @param goopperGroup 小组/班级管理
     * @return 结果
     */
    @Override
    public int updateGoopperGroup(GoopperGroup goopperGroup)
    {
        return goopperGroupMapper.updateGoopperGroup(goopperGroup);
    }

    /**
     * 批量删除小组/班级管理
     * 
     * @param ids 需要删除的小组/班级管理主键
     * @return 结果
     */
    @Override
    public int deleteGoopperGroupByIds(String ids)
    {
        return goopperGroupMapper.deleteGoopperGroupByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除小组/班级管理信息
     * 
     * @param id 小组/班级管理主键
     * @return 结果
     */
    @Override
    public int deleteGoopperGroupById(Long id)
    {
        return goopperGroupMapper.deleteGoopperGroupById(id);
    }
}
