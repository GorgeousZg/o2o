package com.imooc.o2o.util;

public class PathUtil {
	private static String seperator=System.getProperty("file.separator");

	public static String getImgBasePath() {
		String os = System.getProperty("os.name");
		System.out.println("os-->"+os);
		String basePath = "";
		if (os.toLowerCase().startsWith("win")) {
			basePath = "C:/projectdev/image";
		} else {
			basePath = "/home/xiangze/image";
		}
		basePath = basePath.replace("/", seperator);
		return basePath;
	}

	public static String getShopImagePath(Long shopId) {
		String imagePath = "/upload/item/shop/" + shopId + "/";
		System.out.println(imagePath.replace("/", seperator));
		return imagePath.replace("/", seperator);
	}
}
