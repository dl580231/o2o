package com.nuc.o2o.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nuc.o2o.dao.ShopCategoryDao;
import com.nuc.o2o.entity.ShopCategory;
import com.nuc.o2o.service.ShopCategoryService;

@Service
@Transactional
public class ShopCategoryServiceImpl implements ShopCategoryService {
	@Autowired
	private ShopCategoryDao shopCategoryDao;

	@Override
	public List<ShopCategory> getShopCategory(ShopCategory shopCategory) {
		return shopCategoryDao.queryShopCategory(shopCategory);
	}

}
