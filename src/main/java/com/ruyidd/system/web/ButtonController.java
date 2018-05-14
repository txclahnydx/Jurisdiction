package com.ruyidd.system.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruyidd.common.web.BaseController;
import com.ruyidd.system.entity.ButtonEntity;
import com.ruyidd.system.service.ButtonService;
import com.ruyidd.utils.RestResult;

@Controller
@RequestMapping("/system/button")
public class ButtonController extends BaseController{
	
	@Autowired
	private ButtonService buttonService;
	
	/**
	 * 	页面跳转:按钮设置主页面
	 */
	@RequestMapping("/index")
	public String buttonIndex() {
		return "system/button/buttonIndex";
	}
	
	/**
	 * 	页面跳转:按钮添加页面
	 */
	@RequestMapping("/add")
	public String buttonAdd() {
		int i = 1/0;
		System.out.println(i);
		return "system/button/buttonAdd";
	}
	
	/**
	 * 	页面跳转:按钮设置主页面
	 */
	@RequestMapping(value="/edit/{id}")
	public String buttonEdit(Model model,@PathVariable("id")Integer id) {
		ButtonEntity button = buttonService.getButtonEntityById(id);
		model.addAttribute("button", button);
		return "system/button/buttonEdit";
	}
	
	@ResponseBody
	@RequestMapping("/getButtonList")
	public Object getButtonList() {
		int i = 1/0;
		List<ButtonEntity> buttonList = buttonService.getButtonEntityList(null);
		return RestResult.success().setData(buttonList);
	}
	
	@ResponseBody
	@RequestMapping("/doAdd")
	public RestResult doAdd(ButtonEntity button) {
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("name", button.getName());
		conditions.put("field", button.getField());
		List<ButtonEntity> buttonList = buttonService.getButtonEntityList(conditions);
		if(buttonList!=null&&buttonList.size()>0) {
			return RestResult.failure("按钮名称或域值已存在");
		}
		button.setCreateId(1L);
		button.setCreateBy("supermanager");
		button.setCreateDate(new Date());
		buttonService.insert(button);
		return RestResult.success();
	}
	
	@ResponseBody
	@RequestMapping("/doEdit")
	public RestResult doEdit(ButtonEntity button) {
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("id", button.getId());
		conditions.put("name", button.getName());
		conditions.put("field", button.getField());
		List<ButtonEntity> buttonList = buttonService.getButtonEntityList(conditions);
		if(buttonList!=null&&buttonList.size()>0) {
			return RestResult.failure("按钮名称或域值已存在");
		}
		buttonService.update(button);
		return RestResult.success();
	}
	
//	@ResponseBody
//	@RequestMapping("/ids")
//	public RestResult doDel() {
//		List<Integer> idList = new ArrayList<>();
//		if(ids!=null&&ids.length()>0) {
//			
//		}
//	}

}
