package com.taotao.service;

import java.util.List;
import java.util.Map;

import com.taotao.common.pojo.EasyUIGraidItemCatResult;
import com.taotao.pojo.ItemCat;

/**
 * 商品类目接口
* <p>Title: ItemCatService.java<／p>
* <p>Description: <／p>
* <p>Copyright: Copyright (c) 2020<／p>
* <p>Company: CUIT<／p>
* @author MrGeek
* @date Jul 6, 2020
* @version 1.0
 */
public interface ItemCatService {
	
	public List<EasyUIGraidItemCatResult> findListByParentId(Long parentId);

}
