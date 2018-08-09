package com.nuc.o2o.service.serviceImpl;

import java.io.File;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nuc.o2o.dao.ShopDao;
import com.nuc.o2o.dto.ShopExecution;
import com.nuc.o2o.entity.Shop;
import com.nuc.o2o.enums.ShopStateEnum;
import com.nuc.o2o.expections.ShopOperationException;
import com.nuc.o2o.service.ShopService;
import com.nuc.o2o.utils.ImageUtils;
import com.nuc.o2o.utils.PathUtils;

@Service
@Transactional
public class ShopServiceImpl implements ShopService {
	@Autowired
	private ShopDao shopDao;

	@Override
	public ShopExecution addShop(Shop shop, File imageFile) {

		try {
			// 1.判断shop是否为空
			if (shop == null) {
				return new ShopExecution(ShopStateEnum.NULL_SHOP);
			}

			// 2.补充shop对象的信息
			shop.setCreateTime(new Date());
			shop.setLastEditTime(new Date());
			shop.setEnableStatus(0);

			// 3.向数据库处理shop信息
			// 向数据库插入店铺信息
			int result = shopDao.insertShop(shop);
			if (result < 1) {
				throw new ShopOperationException("创建店铺失败");
			}

			try {
				// 获取shopId，生成图片名称，将图片存到服务器，将图片路径信息存储到shop中
				if (imageFile != null) {
					// 获取店铺图片存储的路径
					String imagStorePath = PathUtils.getShopImagPath(shop.getShopId());
					String shopImag = ImageUtils.generateThumbnail(imageFile, imagStorePath);
					shop.setShopImg(shopImag);
				}
			} catch (Exception e) {
				throw new ShopOperationException("store shopImag to service error" + e.getMessage());
			}

			// 将图片信息更新到数据库
			result = shopDao.updateShop(shop);
			if (result < 1) {
				throw new ShopOperationException("更新店铺图片信息失败");
			}
		} catch (Exception e) {
			throw new ShopOperationException("add shop erro " + e.getMessage());
		}
		return new ShopExecution(ShopStateEnum.CHECK, shop);
	}

}