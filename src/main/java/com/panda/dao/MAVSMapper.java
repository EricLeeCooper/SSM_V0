package com.panda.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.panda.entity.MAV;

public interface MAVSMapper {

	List<MAV> findPartMAVs(@Param("map")Map<String, Object> map);

	List<MAV> findAllMAVs(@Param("userId")int userId);

	List<MAV> findPartMAV(@Param("map")Map<String, Object> map);

	MAV findMAVByUserId(@Param("userId")int userId);

	void addNewMAV(@Param("map")Map<String, Object> map);

	void updateMAV(@Param("pojo")MAV mav);

	void deleteMAV(@Param("id")int id);

	MAV findMAVById(@Param("id")int id);
	
}
