package com.imooc.o2o.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.imooc.o2o.BaseTest;
import com.imooc.o2o.entity.Area;

public class AreaServiceTest extends BaseTest{
	@Autowired
	private AreaService areaService;
	@Test
	public void testGetAreaList(){
		List<Area> areaList=areaService.getAreaList();
		assertEquals("Î÷Ô·",areaList.get(0).getAreaName());
	}
}
