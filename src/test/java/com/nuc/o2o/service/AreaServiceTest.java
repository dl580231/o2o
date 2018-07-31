package com.nuc.o2o.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.nuc.o2o.BaseTest;
import com.nuc.o2o.entity.Area;

public class AreaServiceTest extends BaseTest{

	@Autowired
	private AreaService areaService;
	@Test
	public void getAreaListTest() {
		List<Area> list = areaService.getAreaList();
		Assert.assertEquals("东区", list.get(0).getAreaName());
	}
}
