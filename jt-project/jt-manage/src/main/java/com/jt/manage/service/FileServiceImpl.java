package com.jt.manage.service;

import com.jt.common.vo.PicUploadResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
	@Value("${image.localPath}")
	private String localPath;	//"E:/jt-upload/";
	@Value("${image.urlPath}")
	private String urlPath;		//http://image.jt.com

	@Override
	public PicUploadResult upload(MultipartFile uploadFile) {
		
		// 1. 校验文件类型 JPG|PNG|GIF ... 
		PicUploadResult result = new PicUploadResult();
		String fileName = uploadFile.getOriginalFilename();
		// 将字符全部转化为小写
		fileName = fileName.toLowerCase();	
		if(! fileName.matches("^.+\\.(jpg|png|gif)$")) {	//asd.jpg
			result.setError(1); // 不是图片
			return result;
		}
		// 2. 校验是否为恶意程序
		try {
			BufferedImage image = 
					ImageIO.read(uploadFile.getInputStream());
			int width = image.getWidth();
			int height = image.getHeight();
			if(width==0 || height==0) {	//图片特有的宽高
				result.setError(1);
				return result;
			}
			// 实现分文件上传
			// 3. 为了防止图片检索速度慢, 采用份文件存储 - yyyy/MM/dd/
			String dateDir = 
					new SimpleDateFormat("yyyy/MM/dd").format(new Date());
			//E:/jt-upload/2019/01/29
			String localPathDir = localPath +dateDir;
			File fileDir = new File(localPathDir);
			if(! fileDir.exists()) {	//判断文件是否存在
				fileDir.mkdirs();
	 		}
			// 4. 防止文件重名 UUID +随机数(3)
			String uuid = 
					UUID.randomUUID().toString().replace("-", "");
			int random = new Random().nextInt(1000); //[0,1000)
			String fileType = 
					fileName.substring(fileName.lastIndexOf("."));
			String realName = uuid +random +fileType;
			String realPath = localPathDir +"/" +realName;
			// 5. 实现文件上传
			uploadFile.transferTo(new File(realPath));
			
			// 定义url
			//String url = "https://img13.360buyimg.com/n1/s450x450_jfs/t1/3/15/4536/138660/5b997bf8Ed72ebce7/819dcf182d743897.jpg";
			String url = urlPath +dateDir +"/" +realName;
			result.setUrl(url);
		} catch (IOException e) {
			e.printStackTrace();
			result.setError(1);	//文件上传失败
			return result;
		}
		return result;
	}

}
