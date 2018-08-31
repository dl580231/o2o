package com.nuc.o2o.service;

import org.springframework.web.multipart.MultipartFile;

import com.nuc.o2o.dto.ShopExecution;
import com.nuc.o2o.entity.Shop;

public interface ShopService {
	/**
	 * 根据shop的信息，查询指定页码和数量的shop数据
	 * @param shop
	 * @param pageIndex
	 * @param rowSize
	 * @return
	 */
	public ShopExecution getShopList(Shop shop, int pageIndex, int rowSize);

	/**
	 * 新增店铺信息
	 * 
	 * @param shop
	 * @param imageFile
	 * @return
	 */
	public ShopExecution addShop(Shop shop, MultipartFile imageFile);

	/**
	 * 根据店铺ID返回店铺信息
	 * 
	 * @param shopId
	 * @return
	 */
	public Shop getShopById(Long shopId);

	/**
	 * 修改店铺信息
	 * 
	 * @param shop
	 * @param imgFile
	 * @return
	 */
	public ShopExecution modifyShopInfo(Shop shop, MultipartFile imgFile);
}
