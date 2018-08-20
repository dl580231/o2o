package com.nuc.o2o.web.shopAdmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/shop/", method=RequestMethod.GET)
public class ShopAdminController {
	@RequestMapping("/shopoperation")
	public String shopOperation() {
		return "/shop/shopoperation";
	}
}
