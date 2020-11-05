package com.taotao.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.mapper.ContentMapper;
import com.taotao.pojo.Content;
import com.taotao.service.ContentService;
import com.taotao.service.RestService;

/**
 * 内容管理Controller
* <p>Title: RestController.java<／p>
* <p>Description: <／p>
* <p>Copyright: Copyright (c) 2020<／p>
* <p>Company: CUIT<／p>
* @author MrGeek
* @date 2020-10-13_13:07:49
* @version 1.0
 */
@Controller
@RequestMapping("/rest")
public class RestController {

	@Autowired
	private RestService restService;
	
	@RequestMapping(value = "/content",method = RequestMethod.GET)
	@ResponseBody
	public Map<Object, Object>getItemParamList(Integer page ,Integer rows,Long categoryId){
		
		//开始获取四大属性
		int pageIndex =1;
		if (page!=null) {
			pageIndex = page;
		}
		int pageSize =10;
		if (rows!=null) {
			pageSize = rows;
		}
		
		HashMap<Object, Object>map = (HashMap<Object, Object>)restService.getContentByCategoryId(pageIndex, pageSize, categoryId);
		return map;
	}
}
