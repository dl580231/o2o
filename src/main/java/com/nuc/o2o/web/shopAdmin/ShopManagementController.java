package com.nuc.o2o.web.shopAdmin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nuc.o2o.dto.ShopExecution;
import com.nuc.o2o.entity.Area;
import com.nuc.o2o.entity.Shop;
import com.nuc.o2o.entity.ShopCategory;
import com.nuc.o2o.enums.ShopStateEnum;
import com.nuc.o2o.service.AreaService;
import com.nuc.o2o.service.ShopCategoryService;
import com.nuc.o2o.service.ShopService;

@Controller
@RequestMapping("shopadmin")
public class ShopManagementController {
	@Autowired
	private ShopService shopService;
	@Autowired
	private ShopCategoryService shopCategoryService;
	@Autowired
	private AreaService areaService;

	@RequestMapping(value="getshopinitinfo",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getShopInitInfo(){
		Map<String, Object> modelMap=new HashMap<>();
		try {
			List<Area> areaList = areaService.getAreaList();
			List<ShopCategory> shopCategoryList = shopCategoryService.getShopCategory(new ShopCategory());
			modelMap.put("areaList", areaList);
			modelMap.put("shopCategoryList", shopCategoryList);
			modelMap.put("success", true);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errorMsg", e.getMessage().toString());
		}
		return modelMap;
	}
	
	@RequestMapping(value = "shopRegister", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> shopRegister(HttpServletRequest request) {
		// 1.处理前端传来的参数
		Map<String, Object> model = new HashMap<String, Object>();
		CommonsMultipartFile shopImg = null;
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		if (commonsMultipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest httpServletRequest = (MultipartHttpServletRequest) request;
			shopImg = (CommonsMultipartFile) httpServletRequest.getFile("shopImg");
		} else {
			model.put("success", false);
			model.put("errorMsg", "图片文件失效");
			return model;
		}
		String shopInfo = request.getParameter("shopInfo");
		ObjectMapper mapper = new ObjectMapper();
		Shop shop = null;
		try {
			shop = (Shop) mapper.readValue(shopInfo, Shop.class);
		} catch (Exception e) {
			model.put("success", "false");
			model.put("errorMsg", "shop信息转换失效");
			return model;
		}
		// 2.调用service层向数据库插入店铺信息
		if (shop == null || shopImg == null) {
			model.put("success", false);
			model.put("errorMsg", "shop信息为空");
			return model;
		} else {
			try {
				ShopExecution shopExecution = shopService.addShop(shop, shopImg);
				if (shopExecution.getShopStateEnum().getState() == ShopStateEnum.CHECK.getState()) {
					model.put("success", true);
				} else {
					model.put("success", false);
					model.put("errorMsg", ShopStateEnum.CHECK.getStateInfo());
				}
			} catch (Exception e) {
				model.put("success", false);
				model.put("errorMsg", "添加店铺信息失败" + e.getMessage());
				return model;
			}
			// 3.返回结果
			return model;
		}
	}
}
