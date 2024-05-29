package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.GoopperUser;

/**
 * 用户管理Service接口
 * 
 * @author CHENPrime
 * @date 2024-05-28
 */
public interface IGoopperUserService 
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
     * 批量删除用户管理
     * 
     * @param ids 需要删除的用户管理主键集合
     * @return 结果
     */
    public int deleteGoopperUserByIds(String ids);

    /**
     * 删除用户管理信息
     * 
     * @param id 用户管理主键
     * @return 结果
     */
    public int deleteGoopperUserById(Long id);

    List<GoopperUser> selectGoopperTeacherList();

    String importUser(List<GoopperUser> userList, boolean updateSupport, String loginName);
}
