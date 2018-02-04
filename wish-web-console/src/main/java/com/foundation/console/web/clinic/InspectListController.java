package com.foundation.console.web.clinic;

import com.foundation.common.persistence.Page;
import com.foundation.common.utils.IdGen;
import com.foundation.common.utils.StringUtils;
import com.foundation.console.common.BaseController;
import com.foundation.dao.entity.clinic.InspectItem;
import com.foundation.dao.entity.clinic.InspectList;
import com.foundation.service.clinic.biz.IInspectItemBiz;
import com.foundation.service.clinic.biz.IInspectListBiz;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @Title InspectListController
 * @Package com.foundation.console.web
 * @Description 检验单控制层
 * @Author cuiyaohua
 * @date 2016-10-20 14:32
 */
@Controller
@RequestMapping(value = "/admin/clinic/inspect/list")
public class InspectListController extends BaseController {
    @Autowired
    private IInspectListBiz inspectListBiz;

    @Autowired
    private IInspectItemBiz inspectItemBiz;

//    @RequiresPermissions("sys:list:item:view")
    @RequestMapping(value = {"page", ""})
    public String page(HttpServletRequest request, HttpServletResponse response, Model model){
        try {
            HashMap<String, Object> map = Maps.newHashMap();
            String inspectName = request.getParameter("inspectName");
            if (StringUtils.isNotEmpty(inspectName)) map.put("inspectName", inspectName);
            String pageSize = request.getParameter("pageSize");
            if (StringUtils.isEmpty(pageSize)) pageSize = "20";
            Page<InspectList> page = new Page<InspectList>(request, response, Integer.parseInt(pageSize));
            inspectListBiz.queryPage(map, page);
            model.addAttribute("page", page);
            model.addAttribute("inspectName", inspectName);
        } catch (Exception e) {
            logger.error("InspectListController.page()===出现异常", e);
        }
        return "modules/clinic/inspect/list/page";
    }

//    @RequiresPermissions("sys:list:item:view")
    @RequestMapping(value = {"read"})
    public String read(String id, Model model){
        try {
            InspectList inspectList = inspectListBiz.queryById(id);
            model.addAttribute("entity", inspectList);
            if (inspectList != null && inspectList.getInspectItems().size() > 0) {
                StringBuilder itemIds = new StringBuilder();
                for (InspectItem inspectItem :  inspectList.getInspectItems()) {
                    itemIds.append(inspectItem.getId());
                    itemIds.append(",");
                }
                model.addAttribute("itemIds", itemIds.toString().substring(0, itemIds.length()-1));
            }
            List<InspectItem> inspectItems = inspectItemBiz.queryAll(null);
            model.addAttribute("items", inspectItems);
        } catch (Exception e) {
            logger.error("InspectListController.read()===出现异常", e);
        }
        return "modules/clinic/inspect/list/read";
    }

//    @RequiresPermissions("sys:list:item:view")
    @RequestMapping(value = {"create"})
    public String create(InspectList inspectList, String itemIds,  RedirectAttributes redirectAttributes){
        try {
            inspectList.setId(IdGen.uuid());
            inspectList.setCreateDate(new Date());
            inspectListBiz.save(inspectList, itemIds);
            addMessage(redirectAttributes, "保存检验单'" + inspectList.getInspectName() + "'成功");
        } catch (Exception e) {
            logger.error("InspectListController.create()===出现异常", e);
        }
        return "redirect:" + adminPath + "/clinic/inspect/list";
    }

//    @RequiresPermissions("sys:list:item:view")
    @RequestMapping(value = {"update"})
    public String update(InspectList inspectList, String itemIdsNew, String itemIdsDel, RedirectAttributes redirectAttributes){
        try {
            inspectListBiz.update(inspectList, itemIdsNew, itemIdsDel);
            addMessage(redirectAttributes, "修改检验单'" + inspectList.getInspectName() + "'成功");
        } catch (Exception e) {
            logger.error("InspectListController.create()===出现异常", e);
        }
        return "redirect:" + adminPath + "/clinic/inspect/list";
    }
}
