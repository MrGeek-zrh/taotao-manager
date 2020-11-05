package com.taotao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 网站页面跳转Controller
* <p>Title: PageController.java<／p>
* <p>Description: <／p>
* <p>Copyright: Copyright (c) 2020<／p>
* <p>Company: CUIT<／p>
* @author MrGeek
* @date Jul 6, 2020
* @version 1.0
 */
@Controller
@RequestMapping("/page")
public class PageController {
	
	@RequestMapping("/content-add")
	public String contentAdd() {
		return "content-add";
	}
	
	@RequestMapping(value="/content-category",method=RequestMethod.GET)
	public String getContentCategory() {
		return "content-category";
	}

	
	@RequestMapping(value="/content",method=RequestMethod.GET)
	public String getContent() {
		return "/content";
	}
	
	@RequestMapping(value="/item-param-add",method=RequestMethod.GET)
	public String getItemParaAdd() {
		return "/item-param-add";
	}
	
	@RequestMapping("/item-edit")
	public String editItem() {
		return "/item-edit";
	}
	
}
