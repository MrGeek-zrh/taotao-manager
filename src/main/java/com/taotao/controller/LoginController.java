package com.taotao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 登录控制器
* <p>Title: LoginController.java<／p>
* <p>Description: <／p>
* <p>Copyright: Copyright (c) 2020<／p>
* <p>Company: CUIT<／p>
* @author MrGeek
* @date Jul 6, 2020
* @version 1.0
 */
@Controller
public class LoginController {
	
	@RequestMapping("/index")
	public String showIndex() {
		return "index";
	}

}
