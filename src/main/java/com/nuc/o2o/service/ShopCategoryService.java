package com.nuc.o2o.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nuc.o2o.dao.ShopCategoryDao;
import com.nuc.o2o.entity.ShopCategory;

public interface ShopCategoryService {
	public List<ShopCategory> getShopCategory(ShopCategory shopCategory);
}
