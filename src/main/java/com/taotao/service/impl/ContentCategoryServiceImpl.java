package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.EasyUIGraidItemCatResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.ContentCategoryMapper;
import com.taotao.pojo.ContentCategory;
import com.taotao.service.ContentCategoryService;

@Service
/**
 * 商品内容目录管理 实现类
* <p>Title: ContentCategoryServiceImpl<／p>
* <p>Description: <／p>
* <p>Company: CUIT<／p> 
* @author MrGeek
* @date 2020-07-29_13:10:56
 */
public class ContentCategoryServiceImpl implements ContentCategoryService {

	@Autowired
	private ContentCategoryMapper contentCategoryMapper;
	
	/*
	 * 获取商品内容目录数据
	* <p>Title: getContentCatList<／p>
	* <p>Description: <／p>
	* @param parentId 父节点id
	* @return 符合要求的List集合
	* @see com.taotao.service.ContentCategoryService#getContentCatList(java.lang.Long)
	 */
	@Override
	public List<EasyUIGraidItemCatResult> getContentCatList(Long parentId) {
		List<ContentCategory> itemCatResults = contentCategoryMapper.getContentCatList(parentId);
		
		EasyUIGraidItemCatResult easyUIGraidItemCatResult;
		List<EasyUIGraidItemCatResult> list = new ArrayList<EasyUIGraidItemCatResult>();
		
		for (ContentCategory contentCategory : itemCatResults) {
			easyUIGraidItemCatResult = new EasyUIGraidItemCatResult();
			easyUIGraidItemCatResult.setId(contentCategory.getId());
			easyUIGraidItemCatResult.setText(contentCategory.getName());
			easyUIGraidItemCatResult.setState(contentCategory.getIsParent()?"closed":"open");
			list.add(easyUIGraidItemCatResult);
		}
		return list;
	}

	/*
	 * 添加目录
	* <p>Title: insertCategory<／p>
	* <p>Description: <／p>
	* @param parentId
	* @param name
	* @return
	* @see com.taotao.service.ContentCategoryService#insertCategory(java.lang.Long, java.lang.String)
	 */
	@Override
	public TaotaoResult insertCategory(Long parentId, String name) {
		//新建一个商品分类实体类，并赋值
		ContentCategory contentCategory = new ContentCategory();
		contentCategory.setCreated(new Date());
		contentCategory.setIsParent(false);
		contentCategory.setName(name);
		contentCategory.setParentId(parentId);
		//排列序号，表示同级类目的展现次序，如数值相等则按名称次序排列。取值范围:大于零的整数
		contentCategory.setSortOrder(1);
		contentCategory.setStatus(1);
		contentCategory.setUpdated(new Date());
		
		//将实体类添加到数据库tb_content_category 中
		int id = contentCategoryMapper.insert(contentCategory);
		
		//查询父节点
		ContentCategory parentNode = contentCategoryMapper.selectByPrimaryKey(parentId);
		if (!parentNode.getIsParent()) {
			parentNode.setIsParent(true);
			//更新父节点
			contentCategoryMapper.updateByPrimaryKey(parentNode);
		}
		
		//返回主键
		return TaotaoResult.ok(id);
	}

}
