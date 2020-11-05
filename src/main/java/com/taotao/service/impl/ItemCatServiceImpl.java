package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.EasyUIGraidItemCatResult;
import com.taotao.mapper.ItemCatMapper;
import com.taotao.pojo.ItemCat;
import com.taotao.service.ItemCatService;

/**
 * 商品类目实现类
* <p>Title: ItemCatServiceImpl.java<／p>
* <p>Description: <／p>
* <p>Copyright: Copyright (c) 2020<／p>
* <p>Company: CUIT<／p>
* @author MrGeek
* @date Jul 6, 2020
* @version 1.0
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {
	
	@Autowired
	private ItemCatMapper itemCatMapper;

	@Override
	public List<EasyUIGraidItemCatResult> findListByParentId(Long parentId) {
		
		List<ItemCat>list = itemCatMapper.selectByParentId(parentId);
		
		List<EasyUIGraidItemCatResult>list2 = new ArrayList<>();
		EasyUIGraidItemCatResult easyUIGraidItemCatResult;
		
		for(ItemCat itemCat : list) {
			easyUIGraidItemCatResult = new EasyUIGraidItemCatResult();
			easyUIGraidItemCatResult.setId(itemCat.getId());
			easyUIGraidItemCatResult.setText(itemCat.getName());
			easyUIGraidItemCatResult.setState(itemCat.getIsParent()?"closed":"open");
			list2.add(easyUIGraidItemCatResult);
		}
		
		
		return list2;
	}

}
