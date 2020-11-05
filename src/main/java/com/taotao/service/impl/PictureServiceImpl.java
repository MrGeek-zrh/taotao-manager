package com.taotao.service.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.common.pojo.PictureResult;
import com.taotao.common.utils.FastDFSClient;
import com.taotao.service.PictureService;

/**
 * 图片上传Service层
* <p>Title: PictureServiceImpl.java<／p>
* <p>Description: <／p>
* <p>Copyright: Copyright (c) 2020<／p>
* <p>Company: CUIT<／p>
* @author MrGeek
* @date Jul 15, 2020
* @version 1.0
 */
@Service
public class PictureServiceImpl implements PictureService {
	
	@Value("${IMAGE_SERVER_BASE_URL}")
	private String IMAGE_SERVER_BASE_URL;

	/**
	 * 上传图片
	 */
	@Override
	public PictureResult uploadPic(MultipartFile picFile) {
		
		PictureResult result = new PictureResult();
		
		//判断文件是否为空
		if (picFile.isEmpty()) {
			result.setError(1);
			result.setMessage("没有图片");
			return result;
		}
		
		//图片不为空，上传到服务器
		//获取图片的拓展名
		String OriginalFileName = picFile.getOriginalFilename(); 
		//去除拓展名的 . 
		String extnName = OriginalFileName.substring(OriginalFileName.lastIndexOf(".")+1);
		try {
			String url = FastDFSClient.uploadFile(picFile.getBytes(), extnName, null);
			url = IMAGE_SERVER_BASE_URL+url;
			//把结果响应给客户端
			result.setError(0);
			result.setUrl(url);
			return result;
		} catch (IOException e) {
			System.out.println("【错误】:PictureServiceImpl的uploadPic方法出错，原因为："+e.getMessage());
			e.printStackTrace();
		}
		
		result.setError(1);
		result.setMessage("上传失败");
		return result;
	}

}
