package com.nuc.o2o.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;

import org.springframework.web.multipart.MultipartFile;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

public class ImageUtils {
	private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
	private static Random random = new Random();
	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");

	public static String generateThumbnail(MultipartFile thumbnail, String targetAddr) throws IOException {
		// 生成图片随机名称
		String realFileName = getRandomFileName();
		// 获得图片的扩展名
		String extension = getFileExtension(thumbnail.getOriginalFilename());
		// 当传入的目标路径不存在时，手动创建目录
		mkdir(targetAddr);
		// 生成相对路径
		String relativeAddr = targetAddr + realFileName + extension;
		// 绝对路径
		String absolutePath = PathUtils.getImageBasePath() + relativeAddr;
		File destFile = new File(absolutePath);
		Thumbnails.of(thumbnail.getInputStream()).size(200, 200)
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
	private static String getFileExtension(String thumbnail) {
		String extension = thumbnail.substring(thumbnail.indexOf("."));
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
	
	/**
	 * 删除传来的文件或目录
	 * @param imgFile
	 */
	public static void deleteFileOrPath(String imgPath) {
		if(imgPath == null) {
			return;
		}
		File fileOrPath = new File(imgPath);
		if(fileOrPath.exists()) {
			if(fileOrPath.isDirectory()) {
				File[] files = fileOrPath.listFiles();
				for (File file:files) {
					file.delete();
				}
			}
			fileOrPath.delete();
		}
	}
	//单元测试
	public static void main(String args[]) throws IOException {
		/*Thumbnails.of(new File("C:\\Users\\lenovo\\Desktop\\img\\sunzi.jpg")).size(1000, 1000)
				.watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/watermark.jpg")), 0.25f)
				.outputQuality(0.8f).toFile(new File("C:\\Users\\lenovo\\Desktop\\img\\sunzis.jpg"));*/
		ImageUtils.deleteFileOrPath("F:\\ssm整合项目\\image\\uplod\\item\\shop\\43");
	}
}
