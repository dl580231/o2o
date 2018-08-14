package com.nuc.o2o.service;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.nuc.o2o.dto.ShopExecution;
import com.nuc.o2o.entity.Shop;

public interface ShopService {
	public ShopExecution addShop(Shop shop,CommonsMultipartFile imageFile);
}
