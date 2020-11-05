package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.ItemParamResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.ItemCatMapper;
import com.taotao.mapper.ItemParamMapper;
import com.taotao.pojo.ItemParam;
import com.taotao.service.ItemParamService;

/**
 * 商品类目实现类
* <p>Title: ItemParamServiceImpl.java<／p>
* <p>Description: <／p>
* <p>Copyright: Copyright (c) 2020<／p>
* <p>Company: CUIT<／p>
* @author MrGeek
* @date Jul 16, 2020
* @version 1.0
 */
@Service
public class ItemParamServiceImpl implements ItemParamService {
	
	@Autowired
	private ItemParamMapper itemParamMapper;
	
	@Autowired
	private ItemCatMapper itemCatMapper;

	/**
	 * 根据pageIndex 和 pageSize 进行分页操作
	 * @param pageIndex 页码
	 * @param pageSize 页容量
	 * @return map :一个map 集合，该map 集合中包含：存有指定商品类目页的数据的List、数据总条数
	 */
	@Override
	public Map<Object, Object> findItemParamListByPageIndexAndPageSize(Integer pageIndex, Integer pageSize) {
		
		//设定分页是参数
		PageHelper.startPage(pageIndex, pageSize);
		
		//正常查询
		List<ItemParam>list = itemParamMapper.findItemParamList();
		
		System.out.println(list);
		
		//进行分页
		PageInfo<?> pageInfo = new PageInfo<>(list);
		
		List<ItemParamResult> list2 = new ArrayList<>();
		ItemParamResult itemParamResult = null;
		//遍历List集合，根据商品类目查询出商品类目的具体名称，并重新封装到实体类中， 再存到新的list 集合中
		for (ItemParam itemParam : list) {
			itemParamResult = new ItemParamResult();
			itemParamResult.setItemCatName(itemCatMapper.selectByPrimaryKey(itemParam.getItemCatId()).getName());
			itemParamResult.setId(itemParam.getId());
			itemParamResult.setItemCatId(itemParam.getItemCatId());
			itemParamResult.setParamData(itemParam.getParamData());
			itemParamResult.setCreated(itemParam.getCreated());
			itemParamResult.setUpdated(itemParam.getUpdated());
			list2.add(itemParamResult);
		}
		
		//获取数据总条数
		long totalCount = pageInfo.getTotal();
		
		//对List集合以及totalCount 进行封装
		HashMap<Object, Object>map = new HashMap<>();
		
		map.put("total", totalCount);
		map.put("rows", list2);
		
		return map;
	}

	/**
	 * 商品品类Service实现类
	 * 控制商品品类，保证不会被重复添加
	 * @param id 商品品类id 
	 * @return boolean : true: 添加过了  false: 没有添加
	 */
	@Override
	public boolean isAdd(Long id) {
		List<ItemParam>list  = itemParamMapper.selectByItemCatId(id);
		if (list.isEmpty()) {
			return false;
		}
		return true;
	}

	/**
	 * 将新添加的商品类目写到tb_item_param 表中
	 * @param id 商品品类id 
	 * @param paramData JSON字符串：商品的类目信息
	 * @return boolean : true: 添加成功  false: 添加失败
	 */
	@Override
	public boolean addItemParam(Long id, String paramData) {
		ItemParam itemParam = new ItemParam();
		itemParam.setItemCatId(id);
		itemParam.setParamData(paramData);
		itemParam.setCreated(new Date());
		itemParam.setUpdated(new Date());
		
		int count = itemParamMapper.insert(itemParam);
		
		if (count==0) {
			return false;
		}else {
			return true;
		}
		
	}

	/**
	 * 根据商品参数id删除商品参数
	 * @param itemId 商品参数id
	 * @return TaotaoResult	
	 */
	@Override
	public TaotaoResult deleteParam(Long itemParamId) {
		itemParamMapper.deleteByPrimaryKey(itemParamId);
		return TaotaoResult.ok();
	}


}
