package com.nuc.o2o.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nuc.o2o.entity.Area;
import com.nuc.o2o.service.AreaService;


@Controller
@RequestMapping("/superadmin")
public class AreaController {
	@Autowired
	private AreaService areaService;
	private Logger looger=LoggerFactory.getLogger(this.getClass());
	
	@ResponseBody
	@RequestMapping(value="/listarea",method=RequestMethod.GET)
	public Map<String, Object>listArea(){
		looger.info("项目启动");
		long startTime = System.currentTimeMillis();
		Map<String, Object> map = new HashMap<String, Object>();
		List<Area> list = new ArrayList<Area>();
		try {
			list=areaService.getAreaList();
		}catch(Exception e) {
			e.printStackTrace();
			looger.error(e.toString());
			map.put("success", false);
			map.put("errorMessage", e.toString());
		}
		map.put("rows", list);
		map.put("total",list.size());
		long endTime = System.currentTimeMillis();
		looger.debug("项目执行时间：[{}ms]",endTime-startTime);
		looger.info("项目结束");
		return map;
		
	}
}
