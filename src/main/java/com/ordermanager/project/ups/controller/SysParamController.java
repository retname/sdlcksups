package com.ordermanager.project.ups.controller;

import com.ordermanager.framework.web.controller.BaseController;
import com.ordermanager.framework.web.domain.AjaxResult;
import com.ordermanager.framework.web.page.TableDataInfo;
import com.ordermanager.project.ups.domain.SysParam;
import com.ordermanager.project.ups.service.ISysParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Administrator on 2021/5/21.
 */
@Controller
@RequestMapping("/sysparam")
public class SysParamController extends BaseController {
    @Autowired
    private ISysParam iSysParam;

    @GetMapping("/view")
    public ModelAndView apiLogView() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/sysparam/view/sys_param_list.html");
        return mv;
    }

    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list() {
        startPage();
        List<SysParam> list = iSysParam.selectSysParamList();
        return getDataTable(list);
    }

    /**
     * 修改用户
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
        SysParam sysParam = iSysParam.selectSysParamById(id);
        mmap.put("sysParam",sysParam);
        return "/sysparam/view/sys_param_edit";
    }

    /**
     * 修改用户
     */
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysParam sysParam)
    {
       return toAjax(iSysParam.updateSysParamById(sysParam));
    }
}
