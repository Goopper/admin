package com.ruoyi.system.controller;

import java.util.List;

import com.ruoyi.system.domain.GoopperGroup;
import com.ruoyi.system.service.IGoopperGroupService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.GoopperUser;
import com.ruoyi.system.service.IGoopperUserService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 用户管理Controller
 * 
 * @author CHENPrime
 * @date 2024-05-28
 */
@Controller
@RequestMapping("/system/GoopperUser")
public class GoopperUserController extends BaseController
{
    private String prefix = "system/GoopperUser";

    @Autowired
    private IGoopperUserService goopperUserService;

    @Autowired
    private IGoopperGroupService goopperGroupService;

    @RequiresPermissions("system:GoopperUser:view")
    @GetMapping()
    public String GoopperUser()
    {
        return prefix + "/GoopperUser";
    }

    /**
     * 查询用户管理列表
     */
    @RequiresPermissions("system:GoopperUser:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(GoopperUser goopperUser)
    {
        startPage();
        List<GoopperUser> list = goopperUserService.selectGoopperUserList(goopperUser);
        return getDataTable(list);
    }

    /**
     * 导出用户管理列表
     */
    @RequiresPermissions("system:GoopperUser:export")
    @Log(title = "用户管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(GoopperUser goopperUser)
    {
        List<GoopperUser> list = goopperUserService.selectGoopperUserList(goopperUser);
        ExcelUtil<GoopperUser> util = new ExcelUtil<GoopperUser>(GoopperUser.class);
        return util.exportExcel(list, "用户管理数据");
    }

    /**
     * 新增用户管理
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
        // query groups
        List<GoopperGroup> groups = goopperGroupService.selectGoopperGroupList(new GoopperGroup());
        GoopperGroup emptyGroup = new GoopperGroup();
        emptyGroup.setId(null);
        emptyGroup.setName("无");
        groups.add(0, emptyGroup);
        mmap.put("groups", groups);
        return prefix + "/add";
    }

    /**
     * 新增保存用户管理
     */
    @RequiresPermissions("system:GoopperUser:add")
    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(GoopperUser goopperUser)
    {
        if (goopperUser.getRoleId() == 1 && goopperUser.getGroupId() == null) {
            return AjaxResult.error("创建学生时，请选择小组");
        }
        return toAjax(goopperUserService.insertGoopperUser(goopperUser));
    }

    /**
     * 修改用户管理
     */
    @RequiresPermissions("system:GoopperUser:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        GoopperUser goopperUser = goopperUserService.selectGoopperUserById(id);
        mmap.put("goopperUser", goopperUser);
        // query groups
        List<GoopperGroup> groups = goopperGroupService.selectGoopperGroupList(new GoopperGroup());
        mmap.put("groups", groups);
        return prefix + "/edit";
    }

    /**
     * 修改保存用户管理
     */
    @RequiresPermissions("system:GoopperUser:edit")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(GoopperUser goopperUser)
    {
        return toAjax(goopperUserService.updateGoopperUser(goopperUser));
    }

    /**
     * 删除用户管理
     */
    @RequiresPermissions("system:GoopperUser:remove")
    @Log(title = "用户管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(goopperUserService.deleteGoopperUserByIds(ids));
    }

    /**
     * 导入用户
     */
    @Log(title = "用户管理", businessType = BusinessType.IMPORT)
    @RequiresPermissions("system:user:export")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<GoopperUser> util = new ExcelUtil<>(GoopperUser.class);
        List<GoopperUser> userList = util.importExcel(file.getInputStream());
        String message = goopperUserService.importUser(userList, updateSupport, getLoginName());
        return AjaxResult.success(message);
    }

    /**
     * 导入用户页面
     * @return
     */
    @RequiresPermissions("system:user:view")
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<GoopperUser> util = new ExcelUtil<>(GoopperUser.class);
        return util.importTemplateExcel("用户数据");
    }
}
