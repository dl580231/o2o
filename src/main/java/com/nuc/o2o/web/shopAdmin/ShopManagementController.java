package com.nuc.o2o.web.shopAdmin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nuc.o2o.dto.ShopExecution;
import com.nuc.o2o.entity.Area;
import com.nuc.o2o.entity.PersonInfo;
import com.nuc.o2o.entity.Shop;
import com.nuc.o2o.entity.ShopCategory;
import com.nuc.o2o.enums.ShopStateEnum;
import com.nuc.o2o.service.AreaService;
import com.nuc.o2o.service.ShopCategoryService;
import com.nuc.o2o.service.ShopService;
import com.nuc.o2o.utils.CodeUtils;

@Controller
@RequestMapping("/shopadmin")
public class ShopManagementController {
	@Autowired
	private ShopService shopService;
	@Autowired
	private ShopCategoryService shopCategoryService;
	@Autowired
	private AreaService areaService;

	@ResponseBody
	@RequestMapping(value = "/getshopmanagementinfo", method = RequestMethod.GET)
	public Map<String, Object> getShopManagementInfo(HttpServletRequest request,
			@RequestParam(value = "shopId") Long shopId) {
		Map<String, Object> model = new HashMap<>();
		if (shopId != null && shopId > 0) {
			model.put("redirect", false);
			Shop currentShop = new Shop();
			currentShop.setShopId(shopId);
			request.getSession().setAttribute("currentShop", currentShop);
		} else {
			Object currentShopObject = request.getSession().getAttribute("currentShop");
			if (currentShopObject != null) {
				Shop currentShop = (Shop) currentShopObject;
				shopId = currentShop.getShopId();
				model.put("redirect", false);
				model.put("shopId", shopId);
			} else {
				model.put("redirect", true);
				model.put("url", "/o2o/shop/shoplist");
			}
		}
		return model;
	}

	/**
	 * 根据session中存储得user信息获得user下的shop列表
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getshoplist", method = RequestMethod.GET)
	public Map<String, Object> getShopList(HttpServletRequest request) {
		Map<String, Object> model = new HashMap<>();
		// user信息要从session中获取，由于还没有实现登陆，手动设置user信息
		PersonInfo user = new PersonInfo();
		user.setUserId(1l);
		user.setName("小明");
		request.getSession().setAttribute("user", user);
		// user信息要从session中获取
		PersonInfo uInfo = (PersonInfo) request.getSession().getAttribute("user");
		Shop shop = new Shop();
		shop.setOwner(uInfo);
		try {
			ShopExecution se = shopService.getShopList(shop, 0, 100);
			if (se.getShopStateEnum().getState() == ShopStateEnum.SUCCESS.getState()) {
				model.put("success", true);
				model.put("user", user);
				model.put("shopList", se.getShopList());
			} else {
				model.put("success", false);
				model.put("errorMsg", "获取店铺列表信息失败");
			}
		} catch (Exception e) {
			model.put("success", false);
			model.put("errorMsg", e.getMessage().toString());
		}
		return model;
	}

	/**
	 * 根据表单传来的信息修改店铺信息
	 * 
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/modifyshopinfo", method = RequestMethod.POST)
	public Map<String, Object> modifyShopInfo(HttpServletRequest request,
			@RequestParam(value = "shopImg", required = false) MultipartFile shopImg) {
		Map<String, Object> model = new HashMap<String, Object>();
		// 0.判断验证码
		boolean verifyCodeResult = CodeUtils.checkVerifyCode(request);
		if (!verifyCodeResult) {
			model.put("success", false);
			model.put("errorMsg", "验证码输入错误,请重新输入验证码:");
			return model;
		}
		// 1.处理前端传来的参数
		String shopInfo = request.getParameter("shop");
		ObjectMapper mapper = new ObjectMapper();
		Shop shop = null;
		try {
			shop = (Shop) mapper.readValue(shopInfo, Shop.class);
		} catch (Exception e) {
			model.put("success", "false");
			model.put("errorMsg", "shop信息转换失效");
			return model;
		}
		// 2.调用service层修改店铺信息
		if (shop == null || shop.getShopId() == 0) {
			model.put("success", false);
			model.put("errorMsg", "shop信息或shopId为空");
			return model;
		} else {
			try {
				ShopExecution shopExecution = shopService.modifyShopInfo(shop, shopImg);
				if (shopExecution.getShopStateEnum().getState() == ShopStateEnum.SUCCESS.getState()) {
					model.put("success", true);
				} else {
					model.put("success", false);
					model.put("errorMsg", "店铺信息修改失败");
				}
			} catch (Exception e) {
				model.put("success", false);
				model.put("errorMsg", "修改店铺信息失败" + e.getMessage());
				return model;
			}
			// 3.返回结果
			return model;
		}
	}

	/**
	 * 根据shopId返回shop信息和列表信息，用于渲染用户修改店铺信息的页面
	 * 
	 * @param request
	 * @return
	 */

	@ResponseBody
	@RequestMapping(value = "/getshopinfobyid", method = RequestMethod.GET)
	public Map<String, Object> getShopInfoById(@RequestParam("shopId") Long shopId) {
		Map<String, Object> model = new HashMap<>();
		Shop shop = null;
		try {
			List<Area> areaList = areaService.getAreaList();
			shop = shopService.getShopById(shopId);
			model.put("success", true);
			model.put("shop", shop);
			model.put("areaList", areaList);
		} catch (Exception e) {
			model.put("errorMsg", "获取店铺信息失败" + e.getMessage());
		}
		return model;
	}

	/**
	 * 获取店铺区域和类别信息填充前端页面
	 * 
	 * @return model(json)
	 */

	@ResponseBody
	@RequestMapping(value = "/getshopinitinfo", method = RequestMethod.GET)
	public Map<String, Object> getShopInitInfo() {
		Map<String, Object> modelMap = new HashMap<>();
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

	/**
	 * 根据表单信息进行店铺注册
	 * 
	 * @param request
	 * @param shopImg
	 * @return
	 */

	@ResponseBody
	@RequestMapping(value = "/shopregister", method = RequestMethod.POST)
	public Map<String, Object> shopRegister(HttpServletRequest request,
			@RequestParam("shopImg") MultipartFile shopImg) {
		Map<String, Object> model = new HashMap<String, Object>();
		// 0.判断验证码
		boolean verifyCodeResult = CodeUtils.checkVerifyCode(request);
		if (!verifyCodeResult) {
			model.put("success", false);
			model.put("errorMsg", "验证码输入错误");
			return model;
		}
		// 1.处理前端传来的参数
		/*
		 * MultipartFile shopImg = null; MultipartResolver MultipartResolver = new
		 * MultipartResolver( request.getSession().getServletContext()); if
		 * (MultipartResolver.isMultipart(request)) { MultipartHttpServletRequest
		 * httpServletRequest = (MultipartHttpServletRequest) request; shopImg =
		 * (MultipartFile) httpServletRequest.getFile("shopImg"); } else {
		 * model.put("success", false); model.put("errorMsg", "图片文件失效"); return model; }
		 */
		if (shopImg == null) {
			model.put("success", false);
			model.put("errorMsg", "图片文件失效");
			return model;
		}
		String shopInfo = request.getParameter("shop");
		ObjectMapper mapper = new ObjectMapper();
		Shop shop = null;
		try {
			shop = (Shop) mapper.readValue(shopInfo, Shop.class);
		} catch (Exception e) {
			model.put("success", "false");
			model.put("errorMsg", "shop信息转换失效");
			return model;
		}
		// 获取个人信息之后要用session做
		PersonInfo owner = (PersonInfo) request.getSession().getAttribute("user");
		shop.setOwner(owner);
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
					List<Shop> shopList = (List<Shop>) request.getSession().getAttribute("shopList");
					if (shopList == null) {
						shopList = new ArrayList<Shop>();
					}
					shopList.add(shopExecution.getShop());
					request.getSession().setAttribute("shopList", shopList);
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
