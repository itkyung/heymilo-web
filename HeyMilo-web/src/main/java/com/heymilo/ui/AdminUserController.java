package com.heymilo.ui;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.heymilo.common.CommonUtils;
import com.heymilo.identity.ILogin;
import com.heymilo.identity.UserDTO;
import com.heymilo.identity.UserService;
import com.heymilo.identity.entity.UserStatus;
import com.heymilo.shop.entity.Category;
import com.heymilo.shop.entity.Product;
import com.heymilo.ui.param.UserSearchModel;

@Controller
public class AdminUserController {

	@Autowired
	private ILogin login;	
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/admin/listUsers", method = RequestMethod.GET)
	public String listUsers() throws IOException {
		return "admin/user/listUsers";
	}
	
	@RequestMapping(value = "/admin/searchUsers", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String searchUsers(@ModelAttribute UserSearchModel condition, BindingResult bindingResult) throws IOException{
		UserStatus status = null;
		if(condition.getStatus() != null && !"ALL".equals(condition.getStatus())){
			status = UserStatus.valueOf(condition.getStatus());
		}
		
		int _perPage = condition.getLength();
		
		condition.setStart(condition.getStart());
		condition.setLimit(_perPage);
		
		List<UserDTO> results = userService.searchUsers(condition.getKeyword(), status, condition.getStart(), condition.getLimit()); 
		
		int totalCount = userService.countUsers(condition.getKeyword(), status);
		
		int totalPage = 0;
		if(totalCount > 0){
			totalPage =  (totalCount % _perPage) == 0 ? totalCount/_perPage : (totalCount/_perPage)+1;
		}
		
		DatatableJson json = new DatatableJson(condition.getDraw(), totalCount, results.size(), results.toArray(), condition.getStart(), condition.getLimit());
		
		return CommonUtils.toJson(json);
	}
}
