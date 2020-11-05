package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.EasyUIGraidItemCatResult;
import com.taotao.common.pojo.TaotaoResult;

/**
 * 商品内容目录管理 Service
* <p>Title: ContentCategoryService<／p>
* <p>Description: <／p>
* <p>Company: CUIT<／p> 
* @author MrGeek
* @date 2020-07-29_13:02:48
 */
public interface ContentCategoryService {

	List<EasyUIGraidItemCatResult> getContentCatList(Long parentId);
	TaotaoResult insertCategory(Long parentId,String name);
	
}
