package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.GoopperUser;

/**
 * 用户管理Mapper接口
 * 
 * @author CHENPrime
 * @date 2024-05-28
 */
public interface GoopperUserMapper 
{
    /**
     * 查询用户管理
     * 
     * @param id 用户管理主键
     * @return 用户管理
     */
    public GoopperUser selectGoopperUserById(Long id);

    /**
     * 查询用户管理列表
     * 
     * @param goopperUser 用户管理
     * @return 用户管理集合
     */
    public List<GoopperUser> selectGoopperUserList(GoopperUser goopperUser);

    /**
     * 新增用户管理
     * 
     * @param goopperUser 用户管理
     * @return 结果
     */
    public int insertGoopperUser(GoopperUser goopperUser);

    /**
     * 修改用户管理
     * 
     * @param goopperUser 用户管理
     * @return 结果
     */
    public int updateGoopperUser(GoopperUser goopperUser);

    /**
     * 删除用户管理
     * 
     * @param id 用户管理主键
     * @return 结果
     */
    public int deleteGoopperUserById(Long id);

    /**
     * 批量删除用户管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteGoopperUserByIds(String[] ids);

    List<GoopperUser> selectGoopperTeacherList();

    GoopperUser selectGoopperUserByNumber(Long number);

    int selectTeacherGroupCount(Long id);
}
