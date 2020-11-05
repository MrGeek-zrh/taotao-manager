package com.taotao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.mapper.ItemDescMapper;
import com.taotao.pojo.ItemDesc;
import com.taotao.service.ItemDescService;

/**
 * 商品描述接口
* <p>Title: ItemDescServiceImpl.java<／p>
* <p>Description: <／p>
* <p>Copyright: Copyright (c) 2020<／p>
* <p>Company: CUIT<／p>
* @author MrGeek
* @date Jul 16, 2020
* @version 1.0
 */
@Service
public class ItemDescServiceImpl implements ItemDescService {

	@Autowired
	private ItemDescMapper itemDescMapper;
	
	/**
	 * 根据商品id获取商品描述
	 * @param itemId : 商品id
	 * @return JSON字符串，商品的描述信息
	 */
	@Override
	public ItemDesc getItemDesc(Long itemId) {
		return itemDescMapper.selectByPrimaryKey(itemId);
	}

	/**
	 * 更新商品描述表
	 * @param itemDesc 包含有商品描述实体类
	 * @return int : 0表示更新失败 大于0 表示更新成功
	 */
	@Override
	public int updateItemDesc(ItemDesc itemDesc) {
		return itemDescMapper.updateByPrimaryKey(itemDesc);
	}

}
