package com.foundation.console.web.followup;

import com.foundation.common.date.DateUtils;
import com.foundation.common.persistence.Page;
import com.foundation.common.utils.IdGen;
import com.foundation.common.utils.StringUtils;
import com.foundation.console.common.BaseController;
import com.foundation.dao.entity.followup.Followup;
import com.foundation.dao.entity.followup.FollowupSingle;
import com.foundation.dao.entity.questionnaire.RepstQuestionnaire;
import com.foundation.service.followup.biz.IFollowupBiz;
import com.foundation.service.followup.biz.IFollowupSingleBiz;
import com.foundation.service.questionnaire.biz.IRepstQuestionnaireBiz;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * @Title FollowupController
 * @Package com.foundation.console.web
 * @Description 随访的控制层
 * @Author cuiyaohua
 * @date 2016-10-20 14:32
 */
@Controller
@RequestMapping(value = "/admin/followup")
public class FollowupController extends BaseController {
    @Autowired
    private IFollowupBiz followupBiz;

    @Autowired
    private IFollowupSingleBiz followupSingleBiz;

    @Autowired
    private IRepstQuestionnaireBiz repstQuestionnaireBiz;

//    @RequiresPermissions("sys:list:item:view")
    @RequestMapping(value = {"page", ""})
    public String page(HttpServletRequest request, HttpServletResponse response, Model model){
        try {
            HashMap<String, Object> map = Maps.newHashMap();
            String pageSize = request.getParameter("pageSize");
            if (StringUtils.isEmpty(pageSize)) pageSize = "20";
            Page<Followup> page = new Page<Followup>(request, response, Integer.parseInt(pageSize));
            followupBiz.queryPage(map, page);
            model.addAttribute("page", page);
            Page<FollowupSingle> pageItem = new Page<>(request, response, Integer.parseInt(pageSize));
            followupSingleBiz.queryPage(map, pageItem);
            model.addAttribute("pageItem", pageItem);
        } catch (Exception e) {
            logger.error("FollowupController.page()===出现异常", e);
        }
        return "modules/followup/page";
    }

//    @RequiresPermissions("sys:list:item:view")
    @RequestMapping(value = {"read"})
    public String read(String id, Model model){
        try {
            Followup followup = null;
            if (StringUtils.isNotEmpty(id)) {
                followup = followupBiz.queryById(id);
            }else {
                followup = new Followup();
            }
            model.addAttribute("entity", followup);
            Page<RepstQuestionnaire> page = new Page<>(1, 100);
            repstQuestionnaireBiz.queryPage(null, page);
            model.addAttribute("questionnaires", page.getList());
        } catch (Exception e) {
            logger.error("FollowupController.read()===出现异常", e);
        }
        return "modules/followup/read";
    }

//    @RequiresPermissions("sys:list:item:view")
    @RequestMapping(value = {"create"})
    public String create(Followup followup, RedirectAttributes redirectAttributes){
        String id = IdGen.uuid();
        try {
            followup.setId(id);
            followupBiz.save(followup);
            addMessage(redirectAttributes, "新增随访计划成功");
        } catch (Exception e) {
            logger.error("FollowupController.create()===出现异常", e);
        }
        return "redirect:" + adminPath + "/followup/read?id=" + id;
    }

//    @RequiresPermissions("sys:list:item:view")
    @RequestMapping(value = {"update"})
    public String update(Followup followup, HttpServletRequest request,  RedirectAttributes redirectAttributes){
        try {
            String msg = "";
            String id = followup.getId();
            if (StringUtils.isEmpty(id)) {
                followup.setId(IdGen.uuid());
                followupBiz.save(followup);
                msg = "添加";
            }else {
                followupBiz.update(followup);
                msg = "修改";
            }
            addMessage(redirectAttributes, msg + "随访计划'" + followup.getName() + "'成功");
        } catch (Exception e) {
            logger.error("FollowupController.create()===出现异常", e);
        }
        return "redirect:" + adminPath + "/followup/page";
    }

    @RequestMapping(value = {"startup"})
    public String startup(Followup followup, Model model){
        try {
            followupBiz.update(followup);
            long times = followup.getTimes();
            String followupId = followup.getId();
            ArrayList<FollowupSingle> list = Lists.newArrayList();
            Date startTime = followup.getStartDate();
            for (int i = 0; i < times; i++) {
                FollowupSingle followupSingle = new FollowupSingle();
                followupSingle.setId(IdGen.uuid());
                followupSingle.setFollowupId(followupId);
                followupSingle.setFollowupDate(DateUtils.addDays(startTime, i*followup.getIntervals()));
                followupSingle.setQuestId(followup.getQuestId());
                followupSingle.setQuestName(followup.getQuestName());
                followupSingle.setName(followup.getName() + "-" + (i+1));
                followupSingle.setType(followup.getType()+"");
                followupSingle.setStatus("0");
                list.add(followupSingle);
            }
            followupSingleBiz.save(list);
        } catch (Exception e) {
            logger.error("FollowupController.startup()===出现异常", e);
        }
        return "redirect:" + adminPath + "/followup/page";
    }

    @RequestMapping(value = {"delete"})
    public String delete(String itemId, String followupId, Model model){
        try {
//            followupBiz.save(null);
        } catch (Exception e) {
            logger.error("FollowupController.startup()===出现异常", e);
        }
        return "modules/followup/page";
    }
}
