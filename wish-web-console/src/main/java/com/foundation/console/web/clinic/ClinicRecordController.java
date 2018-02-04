package com.foundation.console.web.clinic;

import com.foundation.common.persistence.Page;
import com.foundation.common.utils.IdGen;
import com.foundation.common.utils.StringUtils;
import com.foundation.console.common.BaseController;
import com.foundation.dao.entity.clinic.ClinicRecord;
import com.foundation.dao.entity.clinic.InspectList;
import com.foundation.dao.entity.sys.User;
import com.foundation.mongo.entity.ClinicRecordDetail;
import com.foundation.service.clinic.biz.IClinicRecordBiz;
import com.foundation.service.clinic.biz.IInspectListBiz;
import com.foundation.service.usercenter.biz.IUserBiz;
import com.google.common.collect.Maps;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
import java.util.Map;

/**
 * @Title ClinicRecordController
 * @Package com.foundation.console.web
 * @Description 临床记录的控制层
 * @Author cuiyaohua
 * @date 2016-10-20 14:32
 */
@Controller
@RequestMapping(value = "/admin/clinic/record")
public class ClinicRecordController extends BaseController {
    @Autowired
    private IClinicRecordBiz clinicRecordBiz;
    @Autowired
    private IInspectListBiz inspectListBiz;
    @Autowired
    private IUserBiz userBiz;

//    @RequiresPermissions("sys:list:item:view")
    @RequestMapping(value = {"page", ""})
    public String page(HttpServletRequest request, HttpServletResponse response, Model model){
        try {
            HashMap<String, Object> map = Maps.newHashMap();
            String inspectName = request.getParameter("inspectName");
            if (StringUtils.isNotEmpty(inspectName)) map.put("inspectName", inspectName);
            String pageSize = request.getParameter("pageSize");
            if (StringUtils.isEmpty(pageSize)) pageSize = "20";
            Page<ClinicRecord> page = new Page<ClinicRecord>(request, response, Integer.parseInt(pageSize));
            clinicRecordBiz.queryPage(map, page);
            model.addAttribute("page", page);
            model.addAttribute("inspectName", inspectName);
        } catch (Exception e) {
            logger.error("ClinicRecordController.page()===出现异常", e);
        }
        return "modules/clinic/record/page";
    }

//    @RequiresPermissions("sys:list:item:view")
    @RequestMapping(value = {"read"})
    public String read(String id, Model model){
        try {
            ClinicRecord clinicRecord = clinicRecordBiz.queryById(id);
            model.addAttribute("entity", clinicRecord);
            ClinicRecordDetail recordDetail = clinicRecordBiz.queryDetailById(id);
            model.addAttribute("detail", recordDetail);
            List<User> userList = userBiz.findUser(new User());
            model.addAttribute("users", userList);
        } catch (Exception e) {
            logger.error("ClinicRecordController.read()===出现异常", e);
        }
        return "modules/clinic/record/read";
    }

//    @RequiresPermissions("sys:list:item:view")
    @RequestMapping(value = {"create"})
    public String create(ClinicRecord clinicRecord, RedirectAttributes redirectAttributes){
        String id = IdGen.uuid();
        try {
            clinicRecord.setId(id);
            clinicRecord.setStatus(0);
            InspectList inspectList = inspectListBiz.queryById(clinicRecord.getInspectId());
            clinicRecord.setInspectList(inspectList);
            clinicRecordBiz.save(clinicRecord);
            addMessage(redirectAttributes, "新增临床记录成功");
        } catch (Exception e) {
            logger.error("ClinicRecordController.create()===出现异常", e);
        }
        return "redirect:" + adminPath + "/clinic/record/read?id=" + id;
    }

//    @RequiresPermissions("sys:list:item:view")
    @RequestMapping(value = {"update"})
    public String update(ClinicRecord clinicRecord, HttpServletRequest request,  RedirectAttributes redirectAttributes){
        try {
            clinicRecord.setStatus(1);
            String[] inspectItemsValues = request.getParameterValues("inspectItems.i.value");
            String[] indicatorCodes = request.getParameterValues("itemEnglishName");
            String[] indicatorNames = request.getParameterValues("itemName");
            clinicRecordBiz.update(clinicRecord, inspectItemsValues, indicatorCodes, indicatorNames);
            addMessage(redirectAttributes, "修改临床记录'" + clinicRecord.getInspectName() + "'成功");
        } catch (Exception e) {
            logger.error("ClinicRecordController.create()===出现异常", e);
        }
        return "redirect:" + adminPath + "/clinic/record";
    }

    @RequestMapping(value = {"ready"})
    public String ready(Model model){
        try {
            List<InspectList> inspectLists = inspectListBiz.queryPage(null, new Page<InspectList>(1, 100)).getList();
            model.addAttribute("inspectLists", inspectLists);
           Map<String, Object> types = Maps.newLinkedHashMap();
            types.put("JY", "检验");
            types.put("ZD", "诊断");
            types.put("ZL", "诊疗");
            model.addAttribute("types", types);
        } catch (Exception e) {
            logger.error("ClinicRecordController.ready()===出现异常", e);
        }
        return "modules/clinic/record/ready";
    }
}
