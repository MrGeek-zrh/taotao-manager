package com.taotao.service;

import java.util.Map;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.ItemParam;

/**
 * 商品类目接口
* <p>Title: ItemParamService.java<／p>
* <p>Description: <／p>
* <p>Copyright: Copyright (c) 2020<／p>
* <p>Company: CUIT<／p>
* @author MrGeek
* @date Jul 16, 2020
* @version 1.0
 */
public interface ItemParamService {
	
	public Map<Object, Object> findItemParamListByPageIndexAndPageSize(Integer pageIndex,Integer pageSize);
	
	public boolean isAdd(Long id) ;
	
	public boolean addItemParam(Long id,String paramData) ;
	
	public TaotaoResult deleteParam(Long itemId);
	
}
