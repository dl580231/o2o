package com.nuc.o2o.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nuc.o2o.dao.ShopCategoryDao;
import com.nuc.o2o.entity.ShopCategory;
import com.nuc.o2o.service.ShopCategoryService;

@Service
public class ShopCategoryServiceImpl implements ShopCategoryService {
	@Autowired
	private ShopCategoryDao shopCategoryDao;

	@Override
	public List<ShopCategory> getShopCategory(ShopCategory shopCategory) {
		return shopCategoryDao.queryShopCategory(shopCategory);
	}

}
