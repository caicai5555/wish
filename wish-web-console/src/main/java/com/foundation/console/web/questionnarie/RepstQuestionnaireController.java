package com.foundation.console.web.questionnarie;

import com.foundation.common.persistence.Page;
import com.foundation.common.utils.IdGen;
import com.foundation.common.utils.StringUtils;
import com.foundation.console.common.BaseController;
import com.foundation.dao.entity.followup.FollowupSingle;
import com.foundation.dao.entity.questionnaire.RepstQuestionnaire;
import com.foundation.dao.entity.questionnaire.RepstQuestionnaireRecord;
import com.foundation.dao.entity.questionnaire.RepstUserQuestion;
import com.foundation.service.followup.biz.IFollowupSingleBiz;
import com.foundation.service.questionnaire.QuestionnaireUtil;
import com.foundation.service.questionnaire.biz.IRepstQuestionnaireBiz;
import com.foundation.service.questionnaire.biz.IRepstQuestionnaireRecordBiz;
import com.foundation.service.usercenter.biz.IUserBiz;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;

/**
 * @Title RepstQuestionnaireController
 * @Package com.foundation.console.web
 * @Description 问卷的控制层
 * @Author cuiyaohua
 * @date 2016-10-20 14:32
 */
@Controller
@RequestMapping(value = "/admin/questionnaire")
public class RepstQuestionnaireController extends BaseController {
    @Autowired
    private IRepstQuestionnaireBiz repstQuestionnaireBiz;
    @Autowired
    private IRepstQuestionnaireRecordBiz repstQuestionnaireRecordBiz;
    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private IFollowupSingleBiz followupSingleBiz;

//    @RequiresPermissions("sys:list:item:view")
    @RequestMapping(value = {"page", ""})
    public String page(HttpServletRequest request, HttpServletResponse response, Model model){
        try {
            HashMap<String, Object> map = Maps.newHashMap();
            String questionnaireName = request.getParameter("questionnaireName");
            if (StringUtils.isNotEmpty(questionnaireName)) map.put("questionnaireName", questionnaireName);
            String pageSize = request.getParameter("pageSize");
            if (StringUtils.isEmpty(pageSize)) pageSize = "20";
            Page<RepstQuestionnaire> page = new Page<>(request, response, Integer.parseInt(pageSize));
            repstQuestionnaireBiz.queryPage(map, page);
            model.addAttribute("page", page);
            model.addAttribute("questionnaireName", questionnaireName);
        } catch (Exception e) {
            logger.error("RepstQuestionnaireController.page()===出现异常", e);
        }
        return "modules/questionnaire/page";
    }

//    @RequiresPermissions("sys:list:item:view")
    @RequestMapping(value = {"read"})
    public String read(String id, Model model){
        try {
            RepstQuestionnaire repstQuestionnaire = repstQuestionnaireBiz.queryDetailById(id);
            model.addAttribute("entity", repstQuestionnaire);
        } catch (Exception e) {
            logger.error("RepstQuestionnaireController.read()===出现异常", e);
        }
        return "modules/questionnaire/read";
    }

//    @RequiresPermissions("sys:list:item:view")
    @RequestMapping(value = {"create"}, method = RequestMethod.POST)
    public String create(RedirectAttributes redirectAttributes , MultipartFile file){
        String id = "";
        try {
            id  = QuestionnaireUtil.saveQuestionnaireByExcel(file.getInputStream(), repstQuestionnaireBiz);
            addMessage(redirectAttributes, "新增问卷成功");
        } catch (Exception e) {
            logger.error("RepstQuestionnaireController.create()===出现异常", e);
        }
        return "redirect:" + adminPath + "/questionnaire/read?id=" + id;
    }

    @RequestMapping(value = {"create"},method = RequestMethod.GET)
    public String createGet(RepstQuestionnaire questionnaire, RedirectAttributes redirectAttributes){
        return "modules/questionnaire/create";
    }

//    @RequiresPermissions("sys:list:item:view")
   /* @RequestMapping(value = {"update"})
    public String update(RepstQuestionnaire repstQuestionnaire, HttpServletRequest request,  RedirectAttributes redirectAttributes){
        try {

        } catch (Exception e) {
            logger.error("RepstQuestionnaireController.create()===出现异常", e);
        }
        return "redirect:" + adminPath + "/questionnaire/read?id=" + repstQuestionnaire.getId();
    }*/

    @RequestMapping(value = {"record"}, method = RequestMethod.GET)
    public String recordGet(String id, String detailId, Model model){
        try {
            RepstQuestionnaire repstQuestionnaire = repstQuestionnaireBiz.queryDetailById(id);
            model.addAttribute("entity", repstQuestionnaire);
            model.addAttribute("detailId", detailId);
        } catch (Exception e) {
            logger.error("RepstQuestionnaireController.create()===出现异常", e);
        }
        return "modules/questionnaire/record";
    }

    @RequestMapping(value = {"record"}, method = RequestMethod.POST)
    public String record(RepstQuestionnaireRecord repstQuestionnaireRecord, String detailId, RedirectAttributes redirectAttributes){
        try {
            repstQuestionnaireRecord.setId(IdGen.uuid());
            for (RepstUserQuestion userQuestion : repstQuestionnaireRecord.getRepstUserQuestionList()) {
                userQuestion.setId(IdGen.uuid());
                userQuestion.setQuestionnaireId(repstQuestionnaireRecord.getQuestionnaireId());
                userQuestion.setRecordId(repstQuestionnaireRecord.getId());
            }
            repstQuestionnaireRecordBiz.save(repstQuestionnaireRecord);
            // 具体 问卷业务的更新
            FollowupSingle entity = new FollowupSingle();
            entity.setId(detailId);
            entity.setRecordId(repstQuestionnaireRecord.getId());
            entity.setStatus("1");
            entity.setCompleteDate(new Date());
            followupSingleBiz.update(entity);
        } catch (Exception e) {
            logger.error("RepstQuestionnaireController.create()===出现异常", e);
        }
        return "redirect:" + adminPath + "/questionnaire/detail?id=" + repstQuestionnaireRecord.getId();
    }

    @RequestMapping(value = {"detail"})
    public String detail(String id, Model model){
        try {
            RepstQuestionnaireRecord repstQuestionnaireRecord = repstQuestionnaireRecordBiz.queryDetailById(id);
            model.addAttribute("entity", repstQuestionnaireRecord);
        } catch (Exception e) {
            logger.error("RepstQuestionnaireController.detail()===出现异常", e);
        }
        return "modules/questionnaire/detail";
    }
}
