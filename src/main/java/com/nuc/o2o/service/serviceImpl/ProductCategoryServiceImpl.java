package com.nuc.o2o.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nuc.o2o.dao.ProductCategoryDao;
import com.nuc.o2o.dto.ProductCategoryExecution;
import com.nuc.o2o.entity.ProductCategory;
import com.nuc.o2o.enums.ProductCategoryStateEnum;
import com.nuc.o2o.exceptions.ProductCategoryOperationException;
import com.nuc.o2o.service.ProductCategoryService;

@Service
@Transactional
public class ProductCategoryServiceImpl implements ProductCategoryService {
	@Autowired
	private ProductCategoryDao dao;

	@Override
	public List<ProductCategory> getProductCatrgoryList(Long shopId) {
		List<ProductCategory> list = dao.queryProductCategoryList(shopId);
		return list;
	}

	@Override
	public ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategories)
			throws ProductCategoryOperationException {
		try {
			int result = dao.batchInsertProductCategory(productCategories);
			if (result < 1) {
				throw new ProductCategoryOperationException("增加商品类别信息失败");
			} else {
				return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS, productCategories);
			}
		} catch (ProductCategoryOperationException e) {
			throw new ProductCategoryOperationException("batchAddProductCategory error: " + e.getMessage());
		}

	}

	@Override
	public ProductCategoryExecution removeProductCategory(Long productCategoryId, Long shopId)
			throws ProductCategoryOperationException {
		// 1.删除商品类别下的商品（todo）
		// 2.删除商品类别
		try {
			int result = dao.deleteProductCategory(productCategoryId, shopId);
			if (result < 1) {
				throw new ProductCategoryOperationException("删除商品类别失败");
			} else {
				return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
			}
		} catch (ProductCategoryOperationException e) {
			throw new ProductCategoryOperationException("removeProductCategory error" + e.toString());
		}
	}

}
