/**
 * 
 */
package com.foxconn.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.foxconn.service.UserDetailService;
import com.foxconn.pojo.UserDetail;

/**
 * @author F3219058
 * 
 */

@Controller
@RequestMapping("/sysMgt.do")
public class SysMgtController {

	@Autowired
	@Resource(name = "userDetailServiceImpl")
	private UserDetailService userService;

	@RequestMapping(params = "action=userMgt")
	public String userMgt(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String userNO = request.getParameter("userNO");
		String role = request.getParameter("role");
		String dept = request.getParameter("dept");
		Map<String, String> map = new HashMap<String, String>();
		map.put("userNo", userNO);
		map.put("roleID", role);
		map.put("infoID", dept);
		// UserDetail user=new UserDetail();
		List<UserDetail> userList = userService.SelectUser(map);
		model.addAttribute("userList", userList);
		return "../sysMgt/userMgt";
	}
}
