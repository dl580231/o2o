package com.nuc.o2o.dao;

import java.util.List;

import com.nuc.o2o.entity.Area;

public interface AreaDao {

	/**
	 * 查询降序排列的所有区域信息
	 * @return areaList
	 */
	public List<Area> queryArea();
}
