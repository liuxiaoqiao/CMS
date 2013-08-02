package com.foxconn.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.foxconn.pojo.SelectBean;
import com.foxconn.service.UtilService;


/**
 * @author F3228761
 * @date : Jul 27, 2013 10:39:13 AM
 * @function：工具类
 */
@Controller
@RequestMapping("/util.do")
public class UtilController {

	@Autowired
	@Resource(name = "utilServiceImpl")
	private UtilService utilServiceImpl;

	//绑定文档来源下拉框（Ajax异步请求方式）
	@ResponseBody
	@RequestMapping(params="action=getContentSourceList")
	public List<SelectBean> getContentSourceList(
			){	
		return utilServiceImpl.getContentSourceList();
		}
	
	//审核者下拉框
	@ResponseBody
	@RequestMapping(params="action=getApproverList")
	public List<SelectBean> getApproverList(
			){	
		return utilServiceImpl.getApproverList();
		}
	
}
