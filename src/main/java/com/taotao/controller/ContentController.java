package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUIGraidItemCatResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.pojo.Content;
import com.taotao.service.ContentCategoryService;
import com.taotao.service.ContentService;

/**
 * 网站内容管理页面
* <p>Title: ContentController.java<／p>
* <p>Description: <／p>
* <p>Copyright: Copyright (c) 2020<／p>
* <p>Company: CUIT<／p>
* @author MrGeek
* @date Jul 20, 2020
* @version 1.0
 */
@RequestMapping("/content")
@Controller
public class ContentController {
	
	@Autowired
	private ContentCategoryService contentCategoryService;
	
	@Autowired
	private ContentService contentService;
	
	@Value("${REST_BASE_URL}")
	String REST_BASE_URL;
	@Value("${REST_CONTENT_SYNC_URL}")
	String REST_CONTENT_SYNC_URL;
	
	@RequestMapping(value = "/save",method = RequestMethod.POST)
	@ResponseBody
	public TaotaoResult save(Content content) {
		TaotaoResult result = contentService.saveContent(content);
		try {
			HttpClientUtil.doGet(REST_BASE_URL+REST_CONTENT_SYNC_URL+content.getCategoryId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 返回商品目录数据
	* <p>Title: ContentCategoryList<／p>
	* <p>Description: <／p>
	* @param parentId
	* @return
	 */
	@RequestMapping("/category/list")
	@ResponseBody
	public List<EasyUIGraidItemCatResult> ContentCategoryList(@RequestParam(value = "id",defaultValue = "0") Long parentId) {
		return contentCategoryService.getContentCatList(parentId);
	}

	/**
	 * 新增商品类目功能实现
	* <p>Title: createNode<／p>
	* <p>Description: <／p>
	* @param parentId
	* @param name
	* @return
	 */
	@RequestMapping("/category/create")
	@ResponseBody
	public TaotaoResult createNode(Long parentId,String name) {
		TaotaoResult taotaoResult = contentCategoryService.insertCategory(parentId, name);
		return taotaoResult;
	}
	
}
