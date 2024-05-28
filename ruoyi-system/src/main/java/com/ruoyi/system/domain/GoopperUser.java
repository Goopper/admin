package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户管理对象 user
 * 
 * @author CHENPrime
 * @date 2024-05-28
 */
public class GoopperUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long id;

    /** 用户姓名 */
    @Excel(name = "用户姓名")
    private String name;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String email;

    /** 用户密码 */
    private String password;

    /** 用户头像 */
    private String avatar;

    /** 用户性别 */
    @Excel(name = "用户性别")
    private Integer sex;

    /** 工号/学号 */
    @Excel(name = "工号/学号")
    private Long number;

    /** 角色 */
    @Excel(name = "角色")
    private Long roleId;

    /** 小组名称 */
    private String groupName;

    /** 小组ID */
    @Excel(name = "小组ID")
    private Long groupId;

    /**  */
    private Integer enable;

    /**  */
    private Integer accountNonExpired;

    /**  */
    private Integer accountNonLocked;

    /**  */
    private Integer credentialsNonExpired;

    /** 修改时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date modifyTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setPassword(String password) 
    {
        this.password = password;
    }

    public String getPassword() 
    {
        return password;
    }
    public void setAvatar(String avatar) 
    {
        this.avatar = avatar;
    }

    public String getAvatar() 
    {
        return avatar;
    }
    public void setSex(Integer sex) 
    {
        this.sex = sex;
    }

    public Integer getSex() 
    {
        return sex;
    }
    public void setNumber(Long number) 
    {
        this.number = number;
    }

    public Long getNumber() 
    {
        return number;
    }
    public void setRoleId(Long roleId) 
    {
        this.roleId = roleId;
    }

    public Long getRoleId() 
    {
        return roleId;
    }
    public void setGroupId(Long groupId) 
    {
        this.groupId = groupId;
    }

    public Long getGroupId() 
    {
        return groupId;
    }
    public void setEnable(Integer enable) 
    {
        this.enable = enable;
    }

    public Integer getEnable() 
    {
        return enable;
    }
    public void setAccountNonExpired(Integer accountNonExpired) 
    {
        this.accountNonExpired = accountNonExpired;
    }

    public Integer getAccountNonExpired() 
    {
        return accountNonExpired;
    }
    public void setAccountNonLocked(Integer accountNonLocked) 
    {
        this.accountNonLocked = accountNonLocked;
    }

    public Integer getAccountNonLocked() 
    {
        return accountNonLocked;
    }
    public void setCredentialsNonExpired(Integer credentialsNonExpired) 
    {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public Integer getCredentialsNonExpired() 
    {
        return credentialsNonExpired;
    }
    public void setModifyTime(Date modifyTime) 
    {
        this.modifyTime = modifyTime;
    }

    public Date getModifyTime() 
    {
        return modifyTime;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("email", getEmail())
            .append("password", getPassword())
            .append("avatar", getAvatar())
            .append("sex", getSex())
            .append("number", getNumber())
            .append("roleId", getRoleId())
            .append("groupId", getGroupId())
            .append("enable", getEnable())
            .append("accountNonExpired", getAccountNonExpired())
            .append("accountNonLocked", getAccountNonLocked())
            .append("credentialsNonExpired", getCredentialsNonExpired())
            .append("createTime", getCreateTime())
            .append("modifyTime", getModifyTime())
            .toString();
    }
}
