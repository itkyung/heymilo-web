package com.heymilo.ui;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.heymilo.common.CommonUtils;
import com.heymilo.identity.UserService;
import com.heymilo.identity.entity.User;
import com.heymilo.ui.param.RegistParam;


@Controller
@RequestMapping(value="/login")
public class LoginController {
	@Autowired private UserService userService;
	private Log log = LogFactory.getLog(LoginController.class);
	
	@RequestMapping(value="/loginForm",method=RequestMethod.GET)
	public String loginForm(Model model){
		
		return "/login";
	}
	
	@RequestMapping(value="/registAction",method=RequestMethod.POST,produces = "application/json;charset=utf-8")
	@ResponseBody
	public String registAction(@ModelAttribute("registParam") RegistParam param, BindingResult bindingResult, Model model){
		
		try{
			if(param.getFacebookToken() == null){
				userService.registUser(param.getRegistLoginId(), param.getRegistPassword(), param.getRegistName());
			}else{
				userService.registUserWithFacebook(param.getFacebookToken(), param.getRegistLoginId(), param.getRegistName());
			}
		}catch(Exception e){
			log.error("Error ",e);
		}
		
		return CommonUtils.toJsonResult(true, IErrorCode.SUCCESS, null);
	}
	
	
	@RequestMapping(value="/existLoginId",method=RequestMethod.POST,produces = "application/json;charset=utf-8")
	@ResponseBody
	public String existLoginId(@RequestParam("loginId") String loginId) {
		User user = userService.findByLoginId(loginId);
		if(user != null){
			return CommonUtils.toJsonResult(true, IErrorCode.SUCCESS, null);
		}else{
			return CommonUtils.toJsonResult(false, IErrorCode.SUCCESS, null);
		}
	}
	
	@RequestMapping(value="/activateUser/{userId}")
	public String activateUser(@PathVariable("userId") Long userId,ModelMap model){
		try{
			User user = userService.activateUser(userId);
			model.addAttribute("success",true);
			model.addAttribute("user",user);
		}catch(Exception e){
			log.error("Error :",e);
			model.addAttribute("success",false);
		}
		
		return "activateSuccess";
	}
	
	@RequestMapping("/initUser")
	public String initUser(){
		userService.initAdmin();
		return "";
	}
}
