package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.GoopperGroup;

/**
 * 小组/班级管理Mapper接口
 * 
 * @author CHENPrime
 * @date 2024-05-28
 */
public interface GoopperGroupMapper 
{
    /**
     * 查询小组/班级管理
     * 
     * @param id 小组/班级管理主键
     * @return 小组/班级管理
     */
    public GoopperGroup selectGoopperGroupById(Long id);

    /**
     * 查询小组/班级管理列表
     * 
     * @param goopperGroup 小组/班级管理
     * @return 小组/班级管理集合
     */
    public List<GoopperGroup> selectGoopperGroupList(GoopperGroup goopperGroup);

    /**
     * 新增小组/班级管理
     * 
     * @param goopperGroup 小组/班级管理
     * @return 结果
     */
    public int insertGoopperGroup(GoopperGroup goopperGroup);

    /**
     * 修改小组/班级管理
     * 
     * @param goopperGroup 小组/班级管理
     * @return 结果
     */
    public int updateGoopperGroup(GoopperGroup goopperGroup);

    /**
     * 删除小组/班级管理
     * 
     * @param id 小组/班级管理主键
     * @return 结果
     */
    public int deleteGoopperGroupById(Long id);

    /**
     * 批量删除小组/班级管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteGoopperGroupByIds(String[] ids);

    int selectGoopperGroupStudentCountById(Long id);
}
