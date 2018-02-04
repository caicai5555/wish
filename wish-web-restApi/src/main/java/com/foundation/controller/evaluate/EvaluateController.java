package com.foundation.controller.evaluate;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.foundation.common.BaseController;
import com.foundation.common.bean.ResultModel;

/**
 * 
 * @ClassName: EvaluateController
 * @Description: 评估服务接口
 * @author chengchen
 * @date 2016年10月27日 上午9:46:35
 *
 */
@RestController
@RequestMapping("/evaluate")
public class EvaluateController extends BaseController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired(required = false)
	AmqpTemplate amqpTemplate;

	/**
	 * 
	* @Title: getUser 
	* @Description: 评估服务接口
	* @author chengchen
	* @date 2016年11月14日 上午11:07:36 
	* @param @param id
	* @param @param request
	* @param @return
	* @param @throws Exception    设定参数 
	* @return ResultModel    返回类型 
	* @throws
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public  @ResponseBody ResultModel getUser(@PathVariable("id") String id){
		ResultModel resultModel=new ResultModel();
		try{
			logger.info("评估服务begin id为："+id);
			amqpTemplate.convertAndSend("evaluate_queue_one_key", id);
		}catch (Exception e){
			logger.error("评估功能报错",e);
		}
		return resultModel;
	}
}
