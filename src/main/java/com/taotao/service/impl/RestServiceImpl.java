package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.ContentMapper;
import com.taotao.pojo.Content;
import com.taotao.service.RestService;

/**
 * 商品发布Service 实现类
* <p>Title: RestServiceImpl.java<／p>
* <p>Description: <／p>
* <p>Copyright: Copyright (c) 2020<／p>
* <p>Company: CUIT<／p>
* @author MrGeek
* @date 2020-10-13_14:10:40
* @version 1.0
 */
@Service
public class RestServiceImpl implements RestService {

	@Autowired
	private ContentMapper contentMapper;
	
	@Override
	public Map<Object, Object> getContentByCategoryId(Integer pageIndex,Integer pageSize,Long categoryId) {
		//设定分页是参数
		PageHelper.startPage(pageIndex, pageSize);
		
		//正常查询
		List<Content>list = contentMapper.findContentListByCategoryId(categoryId);
		
		//进行分页
		PageInfo<?> pageInfo = new PageInfo<>(list);
		
		//获取数据总条数
		long totalCount = pageInfo.getTotal();
		
		//对List集合以及totalCount 进行封装
		HashMap<Object, Object>map = new HashMap<>();
		
		map.put("total", totalCount);
		map.put("rows", list);
		
		return map;
	}

}
