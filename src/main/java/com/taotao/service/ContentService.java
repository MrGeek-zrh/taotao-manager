package com.taotao.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.Content;

/**
 * 商品内容Service
* <p>Title: ContentService<／p>
* <p>Description: <／p>
* <p>Company: CUIT<／p> 
* @author MrGeek
* @date 2020-07-29_15:53:16
 */
public interface ContentService {

	public TaotaoResult saveContent(Content content);
}
