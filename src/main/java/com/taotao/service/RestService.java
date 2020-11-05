package com.taotao.service;

import java.util.Map;

/**
 * 商品内容Service
* <p>Title: RestService.java<／p>
* <p>Description: <／p>
* <p>Copyright: Copyright (c) 2020<／p>
* <p>Company: CUIT<／p>
* @author MrGeek
* @date 2020-10-13_14:08:12
* @version 1.0
 */
public interface RestService {
	
	public Map<Object, Object> getContentByCategoryId(Integer pageIndex,Integer pageSize,Long categoryId);

}
