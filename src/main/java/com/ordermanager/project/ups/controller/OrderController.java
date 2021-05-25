package com.ordermanager.project.ups.controller;

import com.ordermanager.framework.web.controller.BaseController;
import com.ordermanager.framework.web.page.TableDataInfo;
import com.ordermanager.project.ups.domain.CreateOrder;
import com.ordermanager.project.ups.service.ICreateOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController extends BaseController {
    @Autowired
    private ICreateOrder iCreateOrder;

    @GetMapping("/view")
    public ModelAndView ordeView() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/order/view/orderlist.html");
        return mv;
    }

    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CreateOrder createOrder) {
        startPage();
        List<CreateOrder> list = iCreateOrder.selectCreateOrderList(createOrder);
        return getDataTable(list);
    }
}
