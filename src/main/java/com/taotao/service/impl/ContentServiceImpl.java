package com.taotao.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.ContentMapper;
import com.taotao.pojo.Content;
import com.taotao.service.ContentService;

/**
 * 商品内容实现类
* <p>Title: ContentServiceImpl<／p>
* <p>Description: <／p>
* <p>Company: CUIT<／p> 
* @author MrGeek
* @date 2020-07-29_15:55:23
 */
@Service
public class ContentServiceImpl implements ContentService {
	
	@Autowired
	private ContentMapper contentMapper;

	/*
	 * 保存新添加的商品内容
	* <p>Title: saveContent<／p>
	* <p>Description: <／p>
	* @param content 添加的商品内容 没有包含id created updated
 	* @return TaotaoResult
	* @see com.taotao.service.ContentService#saveContent(com.taotao.pojo.Content)
	 */
	@Override
	public TaotaoResult saveContent(Content content) {
		content.setCreated(new Date());
		content.setUpdated(new Date());
		contentMapper.insert(content);
		return TaotaoResult.ok();
	}

}
