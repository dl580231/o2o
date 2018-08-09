package com.nuc.o2o.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

public class ImageUtils {
	private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
	private static Random random = new Random();
	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");

	public static String generateThumbnail(File thumbnail, String targetAddr) throws IOException {
		// 生成图片随机名称
		String realFileName = getRandomFileName();
		// 获得图片的扩展名
		String extension = getFileExtension(thumbnail);
		// 当传入的目标路径不存在时，手动创建目录
		mkdir(targetAddr);
		// 生成相对路径
		String relativeAddr = targetAddr + realFileName + extension;
		// 绝对路径
		String absolutePath = PathUtils.getImageBasePath() + relativeAddr;
		File destFile = new File(absolutePath);
		Thumbnails.of(thumbnail).size(200, 200)
				.watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/watermark.jpg")), 0.25f)
				.outputQuality(0.8f).toFile(destFile);
		return absolutePath;
	}

	/**
	 * 如果传来的targetAddr目录不存在则创建
	 * 
	 * @param targetAddr
	 */
	private static void mkdir(String targetAddr) {
		File file = new File(PathUtils.getImageBasePath() + targetAddr);
		if (!file.exists()) {
			file.mkdirs();
		}
	}

	/**
	 * 获得文件的扩展名
	 * 
	 * @param thumbnail
	 * @return
	 */
	private static String getFileExtension(File thumbnail) {
		String originalFilename = thumbnail.getPath();
		String extension = originalFilename.substring(originalFilename.indexOf("."));
		return extension;
	}

	/**
	 * 生成一个随机文件名，为当前的年月日小时分钟秒钟+五位随机数
	 * 
	 * @return
	 */
	private static String getRandomFileName() {
		String date = simpleDateFormat.format(new Date());
		int num = 1000 + random.nextInt(8999);
		return date + num;
	}
	
	//单元测试
	public static void main(String args[]) throws IOException {
		Thumbnails.of(new File("C:\\Users\\lenovo\\Desktop\\img\\sunzi.jpg")).size(1000, 1000)
				.watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/watermark.jpg")), 0.25f)
				.outputQuality(0.8f).toFile(new File("C:\\Users\\lenovo\\Desktop\\img\\sunzis.jpg"));
	}
}