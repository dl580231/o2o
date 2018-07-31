package com.nuc.o2o.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.nuc.o2o.BaseTest;
import com.nuc.o2o.entity.Area;

public class AreaDaoTest extends BaseTest{

	@Autowired
	private AreaDao areaDao;
	@Test
	public void queryAreaTest(){
		List<Area> list = areaDao.queryArea();
		Assert.assertEquals(2,list.size());
	}
}
