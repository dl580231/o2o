package com.nuc.o2o.utils;

public class PathUtils {
	private static String seperator=System.getProperty("file.separator");
	/**
	 * 返回服务器上存储图片的根路径，根据不同的系统去获得（windows或linux或mac...）
	 * @return
	 */
	public static String getImageBasePath() {
		String os = System.getProperty("os.name");
		String basePath="";
		if(os.toLowerCase().startsWith("win")) {
			basePath="F:/ssm整合项目/image/";
		}
		else {
			basePath="/home/dl/image";
		}
		System.out.println(seperator);
		return basePath.replace("/",seperator);
	}
	public static String getShopImagPath(long shopId) {
		String ImagPath="/uplod/item/shop/"+shopId+"/";
		return ImagPath.replace("/", seperator);
	}
	
	/*public static void main(String []args) {
		String path = new PathUtils().getImageBasePath();
		System.out.println(path);
	}*/
}
