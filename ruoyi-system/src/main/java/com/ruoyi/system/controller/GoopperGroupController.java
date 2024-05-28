package com.ruoyi.system.controller;

import java.util.Date;
import java.util.List;

import com.ruoyi.system.domain.GoopperUser;
import com.ruoyi.system.service.IGoopperUserService;
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
import com.ruoyi.system.domain.GoopperGroup;
import com.ruoyi.system.service.IGoopperGroupService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 小组/班级管理Controller
 * 
 * @author CHENPrime
 * @date 2024-05-28
 */
@Controller
@RequestMapping("/system/GoopperGroup")
public class GoopperGroupController extends BaseController
{
    private String prefix = "system/GoopperGroup";

    @Autowired
    private IGoopperGroupService goopperGroupService;

    @Autowired
    private IGoopperUserService goopperUserService;

    @RequiresPermissions("system:GoopperGroup:view")
    @GetMapping()
    public String GoopperGroup()
    {
        return prefix + "/GoopperGroup";
    }

    /**
     * 查询小组/班级管理列表
     */
    @RequiresPermissions("system:GoopperGroup:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(GoopperGroup goopperGroup)
    {
        startPage();
        List<GoopperGroup> list = goopperGroupService.selectGoopperGroupList(goopperGroup);
        return getDataTable(list);
    }

    /**
     * 导出小组/班级管理列表
     */
    @RequiresPermissions("system:GoopperGroup:export")
    @Log(title = "小组/班级管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(GoopperGroup goopperGroup)
    {
        List<GoopperGroup> list = goopperGroupService.selectGoopperGroupList(goopperGroup);
        ExcelUtil<GoopperGroup> util = new ExcelUtil<GoopperGroup>(GoopperGroup.class);
        return util.exportExcel(list, "小组/班级管理数据");
    }

    /**
     * 新增小组/班级管理
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
        // query teachers
        List<GoopperUser> teachers = goopperUserService.selectGoopperTeacherList();
        mmap.put("teachers", teachers);
        return prefix + "/add";
    }

    /**
     * 新增保存小组/班级管理
     */
    @RequiresPermissions("system:GoopperGroup:add")
    @Log(title = "小组/班级管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(GoopperGroup goopperGroup)
    {
        return toAjax(goopperGroupService.insertGoopperGroup(goopperGroup));
    }

    /**
     * 修改小组/班级管理
     */
    @RequiresPermissions("system:GoopperGroup:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        GoopperGroup goopperGroup = goopperGroupService.selectGoopperGroupById(id);
        mmap.put("goopperGroup", goopperGroup);
        // query teachers
        List<GoopperUser> teachers = goopperUserService.selectGoopperTeacherList();
        mmap.put("teachers", teachers);
        return prefix + "/edit";
    }

    /**
     * 修改保存小组/班级管理
     */
    @RequiresPermissions("system:GoopperGroup:edit")
    @Log(title = "小组/班级管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(GoopperGroup goopperGroup)
    {
        goopperGroup.setModifyTime(new Date());
        return toAjax(goopperGroupService.updateGoopperGroup(goopperGroup));
    }

    /**
     * 删除小组/班级管理
     */
    @RequiresPermissions("system:GoopperGroup:remove")
    @Log(title = "小组/班级管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(goopperGroupService.deleteGoopperGroupByIds(ids));
    }
}
