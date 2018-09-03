package com.nuc.o2o.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nuc.o2o.dao.AreaDao;
import com.nuc.o2o.entity.Area;
import com.nuc.o2o.service.AreaService;

@Service
@Transactional
public class AreaServiceImpl implements AreaService{

	@Autowired
	private AreaDao areaDao;
	
	@Override
	public List<Area> getAreaList() {
		return areaDao.queryArea();
	}

	
	
}
