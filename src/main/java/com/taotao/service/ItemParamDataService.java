package com.taotao.service;

import com.taotao.common.pojo.TaotaoResult;

/**
 * 商品规格信息Service
* <p>Title: ItemParamDataService.java<／p>
* <p>Description: <／p>
* <p>Copyright: Copyright (c) 2020<／p>
* <p>Company: CUIT<／p>
* @author MrGeek
* @date Jul 17, 2020
* @version 1.0
 */
public interface ItemParamDataService {

	public TaotaoResult getItemParamData(Long itemId);
	
}
