package com.panda.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.panda.entity.Func;
import com.panda.entity.Permission;
import com.panda.entity.Role;
import com.panda.entity.User;

public interface SystemMapper {
	List<Func> findFuncsById(int id);

	int findAllPermissions();

	List<Permission> findPagePermissions(@Param("map")Map<String, Object> map);

	int findPartPermissions(@Param("map")Map<String, Object> map);

	int findAllUsers();

	List<User> findPageUsers(@Param("map")Map<String, Object> map);

	int findPartUsers(@Param("map")Map<String, Object> map);

	List<Role> findPageRoles(@Param("map")Map<String, Object> map);

	int findPartRoles(@Param("map")Map<String, Object> map);

	Permission getRoorPermission();

	List<Permission> findPersById(int id);

	List<Permission> findPersonalPersById(int mid);

	int findPersonalPer(@Param("map")Map<String, Object> map);

	Object addRolePers(@Param("map")Map<String, Object> map);

	void updateRolePers1(@Param("map")Map<String, Object> map);
	
	void updateRolePers0(int rid);
}
