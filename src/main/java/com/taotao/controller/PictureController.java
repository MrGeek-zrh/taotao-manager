package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.taotao.common.pojo.PictureResult;
import com.taotao.common.utils.JsonUtil;
import com.taotao.service.PictureService;

/**
 * 图片上传控制器
* <p>Title: PictureController.java<／p>
* <p>Description: <／p>
* <p>Copyright: Copyright (c) 2020<／p>
* <p>Company: CUIT<／p>
* @author MrGeek
* @date Jul 15, 2020
* @version 1.0
 */
@Controller	
@RequestMapping("/pic")
public class PictureController {

	@Autowired
	private PictureService pictureService;
	
	@RequestMapping(value="/upload",method=RequestMethod.POST)
	@ResponseBody
	public String uploadFile(MultipartFile uploadFile) {
		
		if (uploadFile.isEmpty()) {
			System.out.println("为空");
		}
		PictureResult pictureResult = pictureService.uploadPic(uploadFile);
		
		//这里之所以手动来将数据转换成JSON，是为了解决浏览器兼容问题（直接用@ResponseBody 的话，与火狐不兼容）
//		String resultString = JsonUtil.ObjectToJSON(pictureResult);
		Gson gson = new Gson();
		 String jsonStr = gson.toJson(pictureResult);
		 return jsonStr;
	}
	
}
