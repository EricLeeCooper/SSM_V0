package com.panda.service;

import java.util.List;
import java.util.Map;

import com.panda.entity.MAV;

public interface MAVsService {

	List<MAV> findPartMAVs(int userId,int rows, int page, String name,String filetype);

	List<MAV> findAllMAVs(int id);

	List<MAV> findPartMAVs(int userId,String name, String address);

	MAV findMAVByUserId(int userId);

	void addNewMAV(Map<String, Object> map);

	void updateMAV(MAV mav);

	void deleteMAV(int id);

	MAV findMAVById(int id);

}
