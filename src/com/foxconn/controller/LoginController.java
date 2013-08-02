package com.foxconn.controller;

import java.text.DateFormat;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.foxconn.pojo.Menu;
import com.foxconn.service.MenuService;

import com.foxconn.service.impl.UserDetailServiceImpl;
import com.foxconn.util.SystemContext;

@Controller
@RequestMapping("/login.do")
public class LoginController {

	@Autowired
	@Resource(name = "menuServiceImpl")
	private MenuService menuServiceImpl;

	// added by Cube temporary
	@Autowired
	/* @Resource(name = "userDetailServiceImpl") */
	private UserDetailServiceImpl uds;

	@RequestMapping(params = "action=home")
	public String home(Model model) {
		String strUserNO = SystemContext.getUserNO();
		String strRoleDesc = SystemContext.getRoleDesc();
		//String curDate= SystemContext.getSystemDate().toString();
		String curDate=DateFormat.getDateInstance(DateFormat.DEFAULT).format(SystemContext.getSystemDate());
		
	List<Menu> topMenu=	menuServiceImpl.getTopMenu(SystemContext.getRoleID(),"0");
		model.addAttribute("userNO", strUserNO);
		model.addAttribute("roleDesc", strRoleDesc);
		model.addAttribute("curDate", curDate);
		model.addAttribute("topMenu",topMenu);
		return "../frame/index";
	}

	// modified by Cube @130724
	// added the user id info
	@RequestMapping(params = "action=top")
	public String top(HttpServletRequest request, HttpServletResponse response,
			Model model) {
		//String strUserID = SystemContext.getUserId();
		String strUserNO = SystemContext.getUserNO();
		//String strRoleID = SystemContext.getRoleID();
		//String strRoleName = SystemContext.getRoleName();
		String strRoleDesc = SystemContext.getRoleDesc();
		model.addAttribute("userNO", strUserNO);
		model.addAttribute("roleDesc", strRoleDesc);
		return "../frame/top";
	}

	@RequestMapping(params = "action=left")
	public String left(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		return "../frame/left";
	}

	@RequestMapping(params = "action=main")
	public String getMain(Model model) {
		return "../frame/right";
	}

	@RequestMapping(params = "action=workPlaceField")
	public String jumpToWorkPlace(Model model) {
		return "workspace/workPlaceField";
	}

	@RequestMapping(params = "action=publicPlaceField")
	public String jumpToPublicPlace(Model model) {
		return "publicspace/publicPlaceField";
	}

	@RequestMapping(params = "action=setUpField")
	public String jumpToSetUpField(Model model) {
		return "managespace/setUpField";
	}

	// added by Cube @130705
	@ResponseBody
	@RequestMapping(params = "action=getMenu")
	public List<Menu> getMenu(HttpServletRequest request) {
		String parentID=request.getParameter("pid");
		return menuServiceImpl.getMenu(SystemContext.getRoleID(),parentID);
	}

	@RequestMapping(params = "action=leftMenu")
	public String leftMenu(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String parentID=request.getParameter("pid");
		model.addAttribute("parentID", parentID);
		return "../frame/leftMenu";
	}
}
