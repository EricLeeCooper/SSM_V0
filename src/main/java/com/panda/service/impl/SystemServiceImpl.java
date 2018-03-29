package com.panda.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.panda.dao.SystemMapper;
import com.panda.entity.Func;
import com.panda.entity.Permission;
import com.panda.entity.Role;
import com.panda.entity.User;
import com.panda.service.SystemService;
@Service("systemService")
public class SystemServiceImpl implements SystemService {
	@Resource
	private SystemMapper systemMapper;

	public List<Func> findFuncsById(int id) {
		// TODO Auto-generated method stub
		List<Func> list= systemMapper.findFuncsById(id);
		return list;
	}

	public int findAllPermissions() {
		// TODO Auto-generated method stub
		return systemMapper.findAllPermissions();
	}

	public List<Permission> findPagePermissions(String name, String plevel, int page, int rows) {
		Map<String,Object> map = new HashMap<String, Object>();
		if(plevel != null && plevel.trim() != "") {
			map.put("plevel", Integer.valueOf(plevel));
		}else {
			map.put("plevel",null);
		}
		map.put("name", name);
		map.put("start", rows*(page-1));
		map.put("end", rows);
		return systemMapper.findPagePermissions(map);
	}

	public int findPartPermissions(String name, String plevel) {
		Map<String,Object> map = new HashMap<String, Object>();
		if(plevel != null && plevel.trim() != "") {
			map.put("plevel", Integer.valueOf(plevel));
		}else {
			map.put("plevel",null);
		}
		map.put("name", name);
		// TODO Auto-generated method stub
		return systemMapper.findPartPermissions(map);
	}
	public int findAllUsers() {
		// TODO Auto-generated method stub
		return systemMapper.findAllUsers();
	}
	
	public List<User> findPageUsers(String name, String ident, int page, int rows) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("ident", ident);
		map.put("name", name);
		map.put("start", rows*(page-1));
		map.put("end", rows);
		return systemMapper.findPageUsers(map);
	}
	
	public int findPartUsers(String name, String plevel) {
		Map<String,Object> map = new HashMap<String, Object>();
		if(plevel != null && plevel.trim() != "") {
			map.put("plevel", Integer.valueOf(plevel));
		}else {
			map.put("plevel",null);
		}
		map.put("name", name);
		// TODO Auto-generated method stub
		return systemMapper.findPartUsers(map);
	}

	public List<Role> findPageRoles(String name, String level, int page, int rows) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("level", level);
		map.put("name", name);
		map.put("start", rows*(page-1));
		map.put("end", rows);
		return systemMapper.findPageRoles(map);
	}

	public int findPartRoles(String name, String level) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String, Object>();
		if(level != null && level.trim() != "") {
			map.put("level", Integer.valueOf(level));
		}else {
			map.put("level",null);
		}
		map.put("name", name);
		// TODO Auto-generated method stub
		return systemMapper.findPartRoles(map);
	}

	public Permission getRoorPermission() {
		// TODO Auto-generated method stub
		return systemMapper.getRoorPermission();
	}

	public List<Permission> findPersById(int id) {
		// TODO Auto-generated method stub
		return systemMapper.findPersById(id);
	}

	public List<Permission> findPersonalPersById(int mid) {
		// TODO Auto-generated method stub
		return systemMapper.findPersonalPersById(mid);
	}

	public int findPersonalPer(int rid, String mid) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("rid", rid);
		map.put("mid", mid);
		return systemMapper.findPersonalPer(map);
	}

	public void addRolePers(int rid, String mid) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("rid", rid);
		map.put("mid", mid);
		systemMapper.addRolePers(map);
	}

	public void changePers(int rid, String ids) {
		// TODO Auto-generated method stub
		String[] mids = ids.split(",");
		//先将此角色下的权限清空，然后重新赋值。
		systemMapper.updateRolePers0(rid);
		for(String mid:mids) {
			int rp = this.findPersonalPer(rid,mid);
			if(rp == 0) {
				this.addRolePers(rid,mid);
			}else {
				this.updateRolePers(rid,mid);
			}
		}
	}

	private void updateRolePers(int rid, String mid) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("rid", rid);
		map.put("mid", mid);
		systemMapper.updateRolePers1(map);
	}

}
