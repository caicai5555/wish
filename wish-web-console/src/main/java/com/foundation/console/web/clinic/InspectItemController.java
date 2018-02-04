package com.foundation.console.web.clinic;

import com.foundation.common.persistence.Page;
import com.foundation.common.utils.IdGen;
import com.foundation.common.utils.StringUtils;
import com.foundation.console.common.BaseController;
import com.foundation.dao.entity.archive.DistArchiveIndicator;
import com.foundation.dao.entity.clinic.InspectItem;
import com.foundation.service.clinic.biz.IInspectItemBiz;
import com.foundation.service.dist.biz.IDistArchiveIndicatorBiz;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @Title InspectItemController
 * @Package com.foundation.console.web
 * @Description 检验单的控制层
 * @Author cuiyaohua
 * @date 2016-10-20 14:32
 */
@Controller
@RequestMapping(value = "/admin/clinic/inspect/item")
public class InspectItemController extends BaseController {
    @Autowired
    private IInspectItemBiz inspectItemBiz;
    @Autowired
    private IDistArchiveIndicatorBiz distArchiveIndicatorBiz;

//    @RequiresPermissions("sys:list:item:view")
    @RequestMapping(value = {"page", ""})
    public String page(HttpServletRequest request, HttpServletResponse response, Model model){
        try {
            HashMap<String, Object> map = Maps.newHashMap();
            String itemName = request.getParameter("itemName");
            if (StringUtils.isNotEmpty(itemName)) map.put("itemName", itemName);
            String pageSize = request.getParameter("pageSize");
            if (StringUtils.isEmpty(pageSize)) pageSize = "20";
            Page<InspectItem> page = new Page<InspectItem>(request, response, Integer.parseInt(pageSize));
            inspectItemBiz.queryPage(map, page);
            model.addAttribute("page", page);
            model.addAttribute("itemName", itemName);
        } catch (Exception e) {
            logger.error("InspectItemController.page()===出现异常", e);
        }
        return "modules/clinic/inspect/item/page";
    }

//    @RequiresPermissions("sys:list:item:view")
    @RequestMapping(value = {"read"})
    public String read(String id, Model model){
        try {
            List<DistArchiveIndicator> indicatorList = distArchiveIndicatorBiz.queryAll();
            model.addAttribute("indicators", indicatorList);
            if (StringUtils.isNotEmpty(id)) {
                InspectItem inspectItem = inspectItemBiz.queryById(id);
                model.addAttribute("entity", inspectItem);
            }
        } catch (Exception e) {
            logger.error("InspectItemController.read()===出现异常", e);
        }
        return "modules/clinic/inspect/item/read";
    }

//    @RequiresPermissions("sys:list:item:view")
    @RequestMapping(value = {"create"})
    public String create(InspectItem inspectItem, RedirectAttributes redirectAttributes){
        try {
            DistArchiveIndicator indicator = distArchiveIndicatorBiz.queryById(inspectItem.getIndexId());
            inspectItem.setId(IdGen.uuid());
            inspectItem.setEnglishName(indicator.getCode());
            inspectItem.setItemName(indicator.getName());
            inspectItem.setUnits(indicator.getUnit());
            inspectItem.setMax(indicator.getMaxValue());
            inspectItem.setMin(indicator.getMinValue());
            inspectItem.setIndexName(indicator.getName());
            inspectItem.setCreateDate(new Date());
            inspectItemBiz.save(inspectItem);
            addMessage(redirectAttributes, "保存检验项'" + inspectItem.getItemName() + "'成功");
        } catch (Exception e) {
            logger.error("InspectItemController.create()===出现异常", e);
        }
        return "redirect:" + adminPath + "/clinic/inspect/item";
    }

//    @RequiresPermissions("sys:list:item:view")
    @RequestMapping(value = {"update"})
    public String update(InspectItem inspectItem, RedirectAttributes redirectAttributes){
        try {
            inspectItemBiz.update(inspectItem);
            addMessage(redirectAttributes, "修改检验项'" + inspectItem.getItemName() + "'成功");
        } catch (Exception e) {
            logger.error("InspectItemController.create()===出现异常", e);
        }
        return "redirect:" + adminPath + "/clinic/inspect/item";
    }
}
