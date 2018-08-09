package com.nuc.o2o.service;

import java.io.File;

import com.nuc.o2o.dto.ShopExecution;
import com.nuc.o2o.entity.Shop;

public interface ShopService {
	public ShopExecution addShop(Shop shop,File imageFile);
}
