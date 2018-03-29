package com.panda.service;

import java.util.List;

import com.panda.entity.Func;
import com.panda.entity.Permission;
import com.panda.entity.Role;
import com.panda.entity.User;

public interface SystemService {
	List<Func> findFuncsById(int id);

	int findAllPermissions();

	List<Permission> findPagePermissions(String name, String plevel, int page, int rows);

	int findPartPermissions(String name, String plevel);

	List<User> findPageUsers(String name, String plevel, int page, int rows);

	int findAllUsers();

	int findPartUsers(String name, String plevel);

	List<Role> findPageRoles(String name, String level, int page, int rows);

	int findPartRoles(String name, String level);

	Permission getRoorPermission();

	List<Permission> findPersById(int id);

	List<Permission> findPersonalPersById(int mid);

	int findPersonalPer(int rid, String mid);

	void addRolePers(int rid, String mid);

	void changePers(int rid, String ids);
}
