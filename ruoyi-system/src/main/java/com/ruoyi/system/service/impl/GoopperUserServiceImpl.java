package com.ruoyi.system.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
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
        checkUserCreation(goopperUser);
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
        checkUserCreation(goopperUser);
        return goopperUserMapper.updateGoopperUser(goopperUser);
    }

    private void checkUserCreation(GoopperUser goopperUser) {
        if (goopperUser.getRoleId() != 2 && goopperUser.getRoleId() != 3) {
            throw new ServiceException("角色只能是学生或老师");
        }
        if (goopperUser.getRoleId() == 1 && goopperUser.getGroupId() == null) {
            throw new ServiceException("学生请选择小组");
        }
        if (goopperUser.getRoleId() == 2 && goopperUser.getGroupId() != null) {
            throw new ServiceException("老师不能选择小组");
        }
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
        GoopperUser user = goopperUserMapper.selectGoopperUserById(id);
        if (user.getRoleId() == 2) {
            int count = goopperUserMapper.selectTeacherGroupCount(id);
            if (count > 0) {
                throw new ServiceException("老师存在小组，不能删除");
            }
        }
        return goopperUserMapper.deleteGoopperUserById(id);
    }

    @Override
    public List<GoopperUser> selectGoopperTeacherList() {
        return goopperUserMapper.selectGoopperTeacherList();
    }

    @Override
    public String importUser(List<GoopperUser> userList, boolean updateSupport, String loginName) {
        if (userList == null || userList.isEmpty())
        {
            throw new ServiceException("导入用户数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (GoopperUser user : userList)
        {
            try
            {
                if (user.getNumber() == null) {
                    throw new ServiceException("导入工号/学号不能为空");
                }
                if (user.getName() == null) {
                    throw new ServiceException("导入姓名不能为空");
                }
                if (user.getRoleId() == null) {
                    throw new ServiceException("导入角色不能为空");
                }
                if (user.getSex() == null) {
                    throw new ServiceException("导入性别不能为空");
                }
                if (user.getEmail() == null) {
                    throw new ServiceException("导入邮箱不能为空");
                }
                if (user.getRoleId() != 2 && user.getRoleId() != 3) {
                    throw new ServiceException("角色只能是学生或老师");
                }
                if (user.getSex() != 0 && user.getSex() != 1) {
                    throw new ServiceException("性别只能是男或女");
                }
                if (user.getRoleId() == 1 && user.getGroupId() == null) {
                    throw new ServiceException("创建学生时，请选择小组");
                }
                if (user.getRoleId() == 2 && user.getGroupId() != null) {
                    throw new ServiceException("老师不能选择小组");
                }
                // 验证是否存在这个用户
                GoopperUser u = goopperUserMapper.selectGoopperUserByNumber(user.getNumber());
                if (u == null)
                {
                    user.setCreateBy(loginName);
                    this.insertGoopperUser(user);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、账号 " + user.getName() + " 导入成功");
                }
                else if (updateSupport)
                {
                    user.setUpdateBy(loginName);
                    this.updateGoopperUserByNumber(user);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、账号 " + user.getName() + " 更新成功");
                }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、账号 " + user.getName() + " 已存在");
                }
            }
            catch (DuplicateKeyException e)
            {
                failureNum++;
                failureMsg.append("<br/>" + failureNum + "、账号 " + user.getName() + " 已存在");
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、账号 " + user.getName() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
            }
        }
        if (failureNum > 0)
        {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new ServiceException(failureMsg.toString());
        }
        else
        {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }

    @Override
    public void updateGoopperUserByNumber(GoopperUser user) {
        goopperUserMapper.updateGoopperUserByNumber(user);
    }
}
