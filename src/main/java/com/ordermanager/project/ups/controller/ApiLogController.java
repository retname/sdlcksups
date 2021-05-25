package com.ordermanager.project.ups.controller;

import com.ordermanager.framework.web.controller.BaseController;
import com.ordermanager.framework.web.page.TableDataInfo;
import com.ordermanager.project.ups.domain.ApiLog;
import com.ordermanager.project.ups.service.ICreateOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping(value = "/apilog")
public class ApiLogController extends BaseController {
    @Autowired
    private ICreateOrder iCreateOrder;

    @GetMapping("/view")
    public ModelAndView apiLogView() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/order/view/api_log_list.html");
        return mv;
    }

    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ApiLog apiLog) {
        startPage();
        List<ApiLog> list = iCreateOrder.selectApiLogList(apiLog);
        return getDataTable(list);
    }
}
