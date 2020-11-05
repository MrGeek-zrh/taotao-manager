package com.taotao.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.taotao.common.pojo.EasyUIGraidItemCatResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.ItemDescMapper;
import com.taotao.mapper.ItemMapper;
import com.taotao.pojo.Item;
import com.taotao.pojo.ItemDesc;
import com.taotao.service.ItemCatService;
import com.taotao.service.ItemDescService;
import com.taotao.service.ItemParamDataService;
import com.taotao.service.ItemParamService;
import com.taotao.service.ItemService;

/**
 * 商品控制器
* <p>Title: ItemController.java<／p>
* <p>Description: <／p>
* <p>Copyright: Copyright (c) 2020<／p>
* <p>Company: CUIT<／p>
* @author MrGeek
* @date Jul 6, 2020
* @version 1.0
 */
@Controller
@RequestMapping("/item")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private ItemCatService itemCatService;
	
	@Autowired
	private ItemParamService itemParamService;
	
	@Autowired
	private ItemDescService itemDescService;
	
	@Autowired
	private ItemParamDataService itemParamDataService;
	
	@Autowired
	private ItemMapper itemMapper;
	
	@Autowired
	private ItemDescMapper itemDescMapper;
	
	@RequestMapping("/param/showItemParam/{itemId}")
	public ModelAndView showItemParam(@PathVariable Long itemId) {
		
		ModelAndView modelAndView = new ModelAndView();
		
		String html = itemService.getItemParamHtml(itemId);
		modelAndView.addObject("html", html);
		modelAndView.setViewName("itemParam");
		return modelAndView;
	}
	
	/**
	 * 根据商品id删除商品信息 3：代表删除
	 * @param itemId
	 * @return
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult deleteItem(Long ids) {
		return itemService.deleteItem(ids);
	}
	
	/**
	 * 根据商品id下架商品
	 * @param ids	商品id
	 * @return
	 */
	@RequestMapping(value="/instock",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult instockItem(Long ids) {
		return itemService.instockItem(ids);
	}
	
	/**
	 * 根据商品id上架商品
	 * @param ids	商品id
	 * @return
	 */
	@RequestMapping(value="/reshelf",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult reshelfItem(Long ids) {
		return itemService.reshelfItem(ids);
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	@ResponseBody
	public boolean updataItem(Item item ,String desc) {
		
		item.setUpdated(new Date());
		item.setCreated(itemService.getItemById(item.getId()).getCreated());
		item.setStatus((byte)1);
		int count1 = itemService.updateItem(item);
		
		ItemDesc itemDesc = new ItemDesc();
		itemDesc.setItemId(item.getId());
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(item.getCreated());
		itemDesc.setUpdated(item.getUpdated());
		int count2 = itemDescService.updateItemDesc(itemDesc);
		
		if (count1>0&&count2>0) {
			return true;
		}
		return false;
		
	}
	
	/**
	 * 根据商品参数id删除商品参数
	 * @param itemParamId	商品参数id
	 * @return	TaotaoResult
	 */
	@RequestMapping(value="/param/delete",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult deleteParam(Long ids) {
		System.out.println(ids);
		return itemParamService.deleteParam(ids);
	}
	
	/**
	 * 获取商品规格信息
	 * @param itemId 商品id
	 * @return	封装有商品规格信息的自定义类
	 */
	@RequestMapping("/param/item/query/{itemId}")
	@ResponseBody
	public TaotaoResult getItemParamData(@PathVariable Long itemId) {
		return itemParamDataService.getItemParamData(itemId);
	}
	
	/**
	 * 根据商品id获取商品描述信息
	 * @param itemId	商品id
	 * @return
	 */
	@RequestMapping("/desc/{itemId}")
	@ResponseBody
	public ItemDesc getDesc(@PathVariable Long itemId) {
		return itemDescService.getItemDesc(itemId);
	}

	/**
	 * 添加商品
	 * @return
	 */
	@RequestMapping(value="/item-add",method=RequestMethod.GET)
	public String addItem() {
		return "item-add";
	}
	
	/**
	 * 获取商品列表
	 * @return
	 */
	@RequestMapping(value="/item-list",method=RequestMethod.GET)
	public String getItemList() {
		return "item-list";
	}
	
	
	/**
	 * 分页控制器
	 * @param page easyui自动传过来的
	 * @param rows easyui自动传过来的
	 * @return 某一页的json 数据
	 */
	@RequestMapping(value="/item_list",method=RequestMethod.POST)
	@ResponseBody
	public Map<Object, Object> getItemListContent(Integer page,Integer rows){
		
		//开始获取四大属性
		int pageIndex =1;
		if (page!=null) {
			pageIndex = page;
		}
		int pageSize =15;
		if (rows!=null) {
			pageSize = rows;
		}
		
		HashMap<Object, Object>map = (HashMap<Object, Object>) itemService.findListByPageIndexAndPageSize(pageIndex, pageSize); 
//		List<Item>list = (List<Item>) map.get(rows);
//		for (Item item : list) {
//			System.out.println(item.getId());
//		}
		return map;
	}
	
	
	/**
	 * 获取商品的规格参数
	 * @return
	 */
	@RequestMapping(value="/item-param-list",method=RequestMethod.GET)
	public String getItemParamList() {
		return "item-param-list";
	}
	
	/**
	 * 选择类目功能的数据来源
	 * @param parentId 页面传来的id,即你选中的标签的id
	 * @return json数据
	 */
	@RequestMapping(value="/selectItemCat",method=RequestMethod.POST)
	@ResponseBody
	public List<EasyUIGraidItemCatResult> selectItemCat(@RequestParam(value="id",defaultValue="0") Long parentId){
		List<EasyUIGraidItemCatResult>list = itemCatService.findListByParentId(parentId);
		return list;
	}
	
	
	/**
	 * 保存商品信息
	 * @param item 包含有商品信息的实体类
	 * @param desc	商品描述
	 * @return
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult saveItem(Item item ,String desc,String itemParams) {
		TaotaoResult taotaoResult = itemService.createItem(item, desc,itemParams);
		return taotaoResult;
	}
	
	/**
	 * ItemParam分页控制器
	 * @param page easyui自动传过来的
	 * @param rows easyui自动传过来的
	 * @return 某一页的json 数据
	 */
	@RequestMapping(value="/param/list",method=RequestMethod.POST)
	@ResponseBody
	public Map<Object, Object>getItemParamList(Integer page ,Integer rows){
		//开始获取四大属性
		int pageIndex =1;
		if (page!=null) {
			pageIndex = page;
		}
		int pageSize =10;
		if (rows!=null) {
			pageSize = rows;
		}
		
		HashMap<Object, Object>map = (HashMap<Object, Object>) itemParamService.findItemParamListByPageIndexAndPageSize(pageIndex, pageSize);
		return map;
	}
	
	/**
	 * 控制商品品类，保证不会被重复添加
	 * @param id 商品品类id 
	 * @return boolean : true: 添加过了  false: 没有添加
	 */
	@RequestMapping("/param/isAdd/{id}")
	@ResponseBody
	public boolean isAdd(@PathVariable Long id) {
		boolean flag = itemParamService.isAdd(id);
		return flag;
	}
	
	/**
	 * 将添加的商品规格参数写到tb_item_param数据库中
	 * @param id 商品品类id 
	 * @return boolean : true: 添加过了  false: 没有添加
	 */
	@RequestMapping(value="/param/isAdd/{id}",method=RequestMethod.POST)
	@ResponseBody
	public boolean addItemParam(@PathVariable Long id,String paramData) {
		boolean flag = itemParamService.addItemParam(id, paramData);
		return flag;
	}
	
}
