package com.imooc.o2o.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

public class ImageUtil {
	private static String basePath=Thread.currentThread().getContextClassLoader().getResource("").getPath();
	private static final SimpleDateFormat sDate_Format=new SimpleDateFormat("yyyyMMddHHmmss");
	private static final Random r=new Random();
	
	public static String generateThumbnail(File thumbnail,String targetAddr){
		String realFileName=getRandomFileName();
		String extension=getFileExtension(thumbnail);
		makeDirPath(targetAddr);
		String relativeAddr=targetAddr+realFileName+extension;
		File dest=new File(PathUtil.getImgBasePath()+relativeAddr);
		try{
			Thumbnails.of(thumbnail).size(200, 200)
			.watermark(Positions.BOTTOM_RIGHT,ImageIO.read(new File("C:/homeproject/o2o/src/main/resources/watermake.jpg")),0.25f).toFile(dest);
		}catch (IOException e) {
			e.printStackTrace();
		}
		return relativeAddr;
	}
	
	/**
	 * 创建目标路径所涉及到的目录
	 * @param targetAddr
	 */
	private static void makeDirPath(String targetAddr) {
		String realFileParentPath=PathUtil.getImgBasePath()+targetAddr;
		File dirPath=new File(realFileParentPath);
		if(!dirPath.exists()){
			dirPath.mkdirs();
		}
		
	}


	/**
	 * 获取输入文件流的扩展名
	 * @param thumbnail
	 * @return
	 */
	private static String getFileExtension(File cFile) {
		String originalFileName=cFile.getName();
		return originalFileName.substring(originalFileName.lastIndexOf("."));
	}



	/**
	 * 生成随机文件名，当前年月日小时分钟描述+五位随机数
	 * @return
	 */
	private static String getRandomFileName() {
		// 获取随机的五位数
		int rannum=r.nextInt(89999)+10000;
		String nowTimeStr=sDate_Format.format(new Date());
		return nowTimeStr+rannum;
	}

	public static void main(String[] args) throws IOException {
		Thumbnails.of(new File("C:/Users/Gorgeous/Desktop/win.jpg")).size(200, 200).watermark(Positions.BOTTOM_RIGHT,ImageIO.read(new File("C:/homeproject/o2o/src/main/resources/watermake.jpg")),0.25f).outputQuality(0.8f).toFile("C:/Users/Gorgeous/Desktop/winnew.jpg");;
	}
}
