package com.taotao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.ItemParamMapper;
import com.taotao.pojo.ItemParam;
import com.taotao.service.ItemParamDataService;

/**
 * 商品规格Service实现类
* <p>Title: ItemParamDataServiceImpl.java<／p>
* <p>Description: <／p>
* <p>Copyright: Copyright (c) 2020<／p>
* <p>Company: CUIT<／p>
* @author MrGeek
* @date Jul 17, 2020
* @version 1.0
 */
@Service
public class ItemParamDataServiceImpl implements ItemParamDataService {

	@Autowired
	private ItemParamMapper itemParamMapper;
	
	/**
	 * 根据商品规格id，获取商品规格信息，并将获取到的itemParam实体类封装在自定义的TaotaoResult 的data属性中，并返回
	 * @param itemId 商品id
	 * @return 包含有商品规格信息的自定义类
	 */
	@Override
	public TaotaoResult getItemParamData(Long itemCatId) {
		 List<ItemParam>list = itemParamMapper.selectByItemCatId(itemCatId);
		if (!list.isEmpty()) {
			for (ItemParam itemParam : list) {
				return TaotaoResult.ok(itemParam);
			}
		}
		return TaotaoResult.ok();
	}

}
