package com.ruyidd.system.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruyidd.common.web.BaseController;
import com.ruyidd.system.entity.MenuEntity;
import com.ruyidd.system.service.IMenuService;
import com.ruyidd.utils.RestResult;

@Controller
@RequestMapping("system/menu")
public class MenuController extends BaseController {
	
	@Autowired
	private IMenuService menuService;
	
	@RequestMapping("/index")
	public String menuIndex() {
		return "system/menu/menuIndex";
	}
	
	@ResponseBody
	@RequestMapping("/getMenuList")
	public RestResult getMenuList() {
		List<MenuEntity> menuList = menuService.getMenuEntityList(null);
		return RestResult.success().setData(menuList);
	}
	
	@ResponseBody
	@RequestMapping("/menuBoTree")
	public RestResult menuBoTree() {
		return null;
	}

}
