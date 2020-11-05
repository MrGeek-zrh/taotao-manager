package com.taotao.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.IDUtil;
import com.taotao.mapper.ItemDescMapper;
import com.taotao.mapper.ItemMapper;
import com.taotao.mapper.ItemParamItemMapper;
import com.taotao.mapper.ItemParamMapper;
import com.taotao.pojo.Item;
import com.taotao.pojo.ItemDesc;
import com.taotao.pojo.ItemParamItem;
import com.taotao.service.ItemService;

import net.sf.json.JSONArray;


/**
 * 
* <p>Title: ItemServiceImpl.java<／p>
* <p>Description: 商品实现类<／p>
* <p>Copyright: Copyright (c) 2020<／p>
* <p>Company: CUIT<／p>
* @author MrGeek
* @date Jul 6, 2020
* @version 1.0
 */
@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemMapper itemMapper;
	
	@Autowired
	private ItemDescMapper itemDescMapper;
	
	@Autowired
	private ItemParamMapper itemParamMapper;
	
	@Autowired
	private ItemParamItemMapper itemParamItemMapper;
	
	/**
	 * 根据 id 获取Item 
	 * @param id
	 * @return Item 实体类
	 */
	@Override
	public Item getItemById(Long id) {
		return itemMapper.selectByPrimaryKey(id);
	}

	/**
	 * 根据pageIndex 和 pageSize 进行分页操作
	 * @param pageIndex 页码
	 * @param pageSize 页容量
	 * @return map :一个map 集合，该map 集合中包含：存有指定页的数据的List、数据总条数
	 */
	@Override
	public Map<Object, Object> findListByPageIndexAndPageSize(Integer pageIndex, Integer pageSize) {
		
		//设定分页是参数
		PageHelper.startPage(pageIndex, pageSize);
		
		//正常查询
		List<Item>list = itemMapper.findAllItem();
		
		//进行分页
		PageInfo<Item> pageInfo = new PageInfo<>(list);
		
		//获取数据总条数
		Long totalCount = pageInfo.getTotal();
		
		//进行分页操作
//		List<Item>list = itemMapper.findListByPageIndexAndPageSize(pageIndex, pageSize);
//		for (Item item : list) {
//			System.out.println(item.getId());
//		}
//		
//		//获取totalCount
//		List<Item>list2 = itemMapper.findAllItem();
//		int totalCount = list2.size();
		
		//对List集合以及totalCount 进行封装
		HashMap<Object, Object>map = new HashMap<>();
		map.put("total", totalCount);
		map.put("rows", list);
		
		return map;
	}

	/**
	 * 向数据库中添加商品信息
	 * @param item 存有商品标题、商品卖点、商品价格、商品数量、商品二维码、商品图片、商品类目的商品实体类
	 * 	（包含的属性没有被全部赋值，还有商品id、商品状态、商品上架时间、商品更新时间没有赋值）
	 * @param desc 商品描述信息
	 * @return 
	 */
	@Override
	public TaotaoResult createItem(Item item, String desc,String itemParams) {
		
		//为tb_item表录入信息
		//填写商品id
		item.setId(Long.parseLong(IDUtil.getInstance().GenerateOrder()));
		System.out.println(item.getId());
		
		//填写商品状态
		//1-正常，2-下架，3-删除
		item.setStatus((byte)1);
		
		//填写商品上架日期
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		item.setCreated(new Date());
		
		//填写商品更新日期
		item.setUpdated(new Date());
		
		//将封装好的数据填到表中
		itemMapper.insert(item);
		
		
		//为tb_item_desc 表录入信息
		ItemDesc itemDesc = new ItemDesc();
		
		//填写商品id
		itemDesc.setItemId(item.getId());
		
		//填写商品上架日期
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		itemDesc.setCreated(item.getCreated());
		
		//填写商品更新日期
		itemDesc.setUpdated(item.getUpdated());
		
		//填写商品描述
		itemDesc.setItemDesc(desc);

		//将封装好的数据写入tb_item_desc 中
		itemDescMapper.insert(itemDesc);
		
		if (itemParams!=null) {
			ItemParamItem itemParamItem = new ItemParamItem();
			itemParamItem.setItemId(item.getId());
			itemParamItem.setParamData(itemParams);
			itemParamItem.setCreated(item.getCreated());
			itemParamItem.setUpdated(item.getUpdated());
			itemParamItemMapper.insert(itemParamItem);
		}
		
		return TaotaoResult.ok();
	}

	/**
	 * 更新商品表
	 * @param 包含有商品信息的商品实体类
	 * @return int: 0代表更新失败，大于0 代表更新成功
	 */
	@Override
	public int updateItem(Item item) {
		int count = itemMapper.updateByPrimaryKey(item);
		return count;
	}

	/**
	 * 根据商品id下架商品 2代表下架
	 * @param itenId 商品id
	 * @return TaotaoResult
	 */
	@Override
	public TaotaoResult instockItem(Long itemId) {
		Item item = itemMapper.selectByPrimaryKey(itemId);
		item.setStatus((byte)2);
		itemMapper.updateByPrimaryKey(item);
		return TaotaoResult.ok();
	}

	/**
	 * 上架
	 */
	@Override
	public TaotaoResult reshelfItem(Long itemId) {
		Item item = itemMapper.selectByPrimaryKey(itemId);
		item.setStatus((byte)1);
		itemMapper.updateByPrimaryKey(item);
		return TaotaoResult.ok();
	}

	/**
	 * 根据商品id删除商品信息
	 */
	@Override
	public TaotaoResult deleteItem(Long itemId) {
		Item item = itemMapper.selectByPrimaryKey(itemId);
		item.setStatus((byte)3);
		itemMapper.updateByPrimaryKey(item);
		return TaotaoResult.ok();
	}

	/**
	 * 根据商品id，从tb_item_param_item表中获取paramData 的值，并生成一段相应的html代码，以便于以后在前端页面中展示paramData（以一个字符串的形式生成）
	 * @param 商品id
	 * @return 生成的HTML代码
	 */
	@Override
	public String getItemParamHtml(Long itemId) {
		//根据商品id获取ItemParamItem 实体类
		ItemParamItem itemParamItem = itemParamItemMapper.selectByItemId(itemId);
		
		//从实体类中获取商品的规格参数信息
		String string = itemParamItem.getParamData();
		
		//指定paramds 的数据类型
		Map<Object, Object>typeM = new HashMap<Object, Object>();
		typeM.put("params", HashMap.class);
		
		//将获取到的JSON字符串转换成Java对象：List<HashMap<String,Object>>
		//Object 是String类型或ArrayList<HashMap<String,String>>类型
		List<Map<Object, Object>>list = JSONArray.toList(JSONArray.fromObject(string), Map.class,typeM);
		
		//生成HTML代码
		StringBuffer sb = new StringBuffer();
		sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"1\" class=\"Ptable\">\n");
		sb.append("	<tbody>\n");
		for (Map<Object, Object> map3 : list) {
			sb.append("		<tr>\n");
			sb.append("			<th class=\"tdTitle\" colspan=\"2\">"+map3.get("group")+"</th>\n");
			sb.append("		</tr>\n");
			//取规格项
			ArrayList<HashMap<String, String>>list2 = (ArrayList<HashMap<String, String>>) map3.get("params");
			for (HashMap<String, String> hashMap : list2) {
				sb.append("		<tr>\n");
				sb.append("			<td class=\"tdTitle\">"+hashMap.get("k")+"</td>\n");
				sb.append("			<td>"+hashMap.get("v")+"</td>\n");
				sb.append("		</tr>\n");
			}
		}
		sb.append("	</tbody>\n");
		sb.append("</table>");
		return sb.toString();
	}

}
