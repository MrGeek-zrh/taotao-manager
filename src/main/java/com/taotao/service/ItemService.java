package com.taotao.service;

import java.util.Map;


import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.Item;


/**
* <p>Title: ItemService.java<／p>
* <p>Description: 商品类的接口<／p>
* <p>Copyright: Copyright (c) 2020<／p>
* <p>Company: CUIT<／p>
* @author MrGeek
* @date Jul 6, 2020
* @version 1.0
 */
public interface ItemService {

	public Item getItemById(Long id) ;
	
	public Map<Object, Object> findListByPageIndexAndPageSize(Integer pageIndex,Integer pageSize);
	
	public TaotaoResult createItem(Item item ,String desc,String itemParams);
	
	public int updateItem(Item item);
	
	public TaotaoResult instockItem(Long itemId);
	
	public TaotaoResult reshelfItem(Long itemId);
	
	public TaotaoResult deleteItem(Long itemId);

	public String getItemParamHtml(Long itemId);
}
