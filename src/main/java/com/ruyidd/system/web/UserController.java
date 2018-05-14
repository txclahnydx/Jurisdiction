package com.ruyidd.system.web;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruyidd.common.web.BaseController;
import com.ruyidd.system.entity.UserEntity;
import com.ruyidd.system.service.IUserService;
import com.ruyidd.utils.RestResult;


@Controller
@RequestMapping("/system/user")
public class UserController extends BaseController {
	
	private final static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	
	@Autowired
	private IUserService userService;
	
	@RequestMapping(value="/userIndex",method=RequestMethod.GET)
	public String userIndex() {
		return "1111";
	}
	
	@RequestMapping(value="/userTest",method=RequestMethod.GET)
	public String userTest() {
		List<UserEntity> userList = new ArrayList<>();
		UserEntity user1 = new UserEntity();
		user1.setLoginName("test3");
		
		UserEntity user2 = new UserEntity();
		user2.setLoginName("test4");
		
		userList.add(user1);
		userList.add(user2);
		
		userService.insert(userList);
		return "OK";
	}
	
	@RequestMapping(value="/loadUserList",method=RequestMethod.GET,produces="application/json;charset=utf-8")
	@ResponseBody
	public RestResult getUserByUserId() {
		logger.info("getUserByUserId:start");
		return RestResult.success().setData(userService.loadUserList());
	}

}
