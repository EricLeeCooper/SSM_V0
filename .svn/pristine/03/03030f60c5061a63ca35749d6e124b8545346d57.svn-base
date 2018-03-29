package com.panda.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.panda.dao.MAVSMapper;
import com.panda.entity.MAV;
import com.panda.service.MAVsService;
@Service("mvasService")
public class MAVsServiceImpl implements MAVsService {
	@Resource
	private MAVSMapper mavsMapper;

	
	public List<MAV> findPartMAVs(int userId,int rows, int page, String name,
			String fileType) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		int start = (page-1)*rows;
		map.put("start", start);
		map.put("rows", rows);
		map.put("name", name);
		map.put("fileType", fileType);
		map.put("userId", userId);
		return mavsMapper.findPartMAVs(map);
	}

	
	public List<MAV> findAllMAVs(int id) {
		// TODO Auto-generated method stub
		return mavsMapper.findAllMAVs(id);
	}

	
	public List<MAV> findPartMAVs(int userId,String name, String address) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("name", name);
		map.put("address", address);
		map.put("userId", userId);
		return mavsMapper.findPartMAV(map);
	}

	
	public MAV findMAVByUserId(int userId) {
		// TODO Auto-generated method stub
		return mavsMapper.findMAVByUserId(userId);
	}

	
	public void addNewMAV(Map<String, Object> map) {
		// TODO Auto-generated method stub
		mavsMapper.addNewMAV(map);
	}

	
	public void updateMAV(MAV mav) {
		// TODO Auto-generated method stub
		mavsMapper.updateMAV(mav);
	}

	
	public void deleteMAV(int id) {
		// TODO Auto-generated method stub
		mavsMapper.deleteMAV(id);
	}

	
	public MAV findMAVById(int id) {
		// TODO Auto-generated method stub
		return mavsMapper.findMAVById(id);
	}
	

}
