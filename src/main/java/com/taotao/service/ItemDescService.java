package com.taotao.service;

import org.springframework.stereotype.Service;

import com.taotao.pojo.ItemDesc;

/**
 * 商品描述Service
* <p>Title: ItemDescService.java<／p>
* <p>Description: <／p>
* <p>Copyright: Copyright (c) 2020<／p>
* <p>Company: CUIT<／p>
* @author MrGeek
* @date Jul 16, 2020
* @version 1.0
 */
public interface ItemDescService {
	
	public ItemDesc getItemDesc(Long itemId);
	
	public int updateItemDesc (ItemDesc itemDesc);

}
