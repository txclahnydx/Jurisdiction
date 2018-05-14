package com.ruyidd.system.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ruyidd.common.web.BaseController;

@Controller
@RequestMapping("/system/error")
public class ErrorController extends BaseController {
	
	@RequestMapping("/e/{errorCode}")
	public String processingErrors(@PathVariable("errorCode")String errorCode) {
		return "/public/error/"+errorCode;
	}
}
