package com.panda.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.panda.entity.Func;
import com.panda.entity.Permission;
import com.panda.entity.Role;
import com.panda.entity.User;
import com.panda.service.SystemService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * 系统管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/system")
public class SystemController {
	@Resource
	private SystemService service;
	@RequestMapping(value="/systemInfo",
			method={RequestMethod.POST,RequestMethod.GET},
			produces={"text/html;charset=UTF-8;","application/json;"})
	public @ResponseBody String systemInfoPage() {
		 JSONObject jo = new JSONObject();
		 jo.put("address", "system/systemInfo");
		 jo.put("title", "系统信息");
		return jo.toString();
	}
	@RequestMapping(value="/roleManage",
			method={RequestMethod.POST,RequestMethod.GET},
			produces={"text/html;charset=UTF-8;","application/json;"})
	public @ResponseBody String roleManage() {
		JSONObject jo = new JSONObject();
		jo.put("address", "system/roleManage");
		jo.put("title", "角色管理");
		return jo.toString();
	}
	@RequestMapping(value="/userManage",
			method={RequestMethod.POST,RequestMethod.GET},
			produces={"text/html;charset=UTF-8;","application/json;"})
	public @ResponseBody String userManage() {
		JSONObject jo = new JSONObject();
		jo.put("address", "system/userManage");
		jo.put("title", "用户管理");
		return jo.toString();
	}
	@RequestMapping(value="/perManage",
			method={RequestMethod.POST,RequestMethod.GET},
			produces={"text/html;charset=UTF-8;","application/json;"})
	public @ResponseBody String perManage() {
		JSONObject jo = new JSONObject();
		jo.put("address", "system/perManage");
		jo.put("title", "权限管理");
		return jo.toString();
	}
	@RequestMapping(value="/perList",
			method={RequestMethod.POST,RequestMethod.GET},
			produces={"text/html;charset=UTF-8;","application/x-www-form-urlencoded"})
	public @ResponseBody Object perList(Map<String, Object> map,HttpServletRequest req,@RequestParam("page")int page,@RequestParam("rows")int rows) {
		String name=req.getParameter("name");
		String plevel = req.getParameter("plevel");
		List<Permission> plist = service.findPagePermissions(name,plevel,page,rows);
		JSONArray json = new JSONArray();
		for(Permission p:plist) {
			JSONObject jo = new JSONObject();
			jo.put("pname", p.getpName());
			jo.put("pcode", p.getpCode());
			jo.put("plevel", p.getPlevel());
			jo.put("pid", p.getPid());
			jo.put("id", p.getId());
			json.add(jo);
		}
		if((name == null || name == "") && (plevel == null || plevel == "")){
			int pAll = service.findAllPermissions();
			map.put("total", pAll);
		}else {
			int pPart = service.findPartPermissions(name,plevel);
			map.put("total", pPart);
		}
		map.put("rows", json);
		
		return JSONObject.fromObject(map).toString();
	}
	@RequestMapping(value="/userList",
			method={RequestMethod.POST,RequestMethod.GET},
			produces={"text/html;charset=UTF-8;","application/x-www-form-urlencoded"})
	public @ResponseBody Object userList(Map<String, Object> map,HttpServletRequest req,@RequestParam("page")int page,@RequestParam("rows")int rows) {
		String name=req.getParameter("name");
		String ident = req.getParameter("ident");
		List<User> ulist = service.findPageUsers(name,ident,page,rows);
		JSONArray json = new JSONArray();
		for(User u:ulist) {
			JSONObject jo = new JSONObject();
			jo.put("id", u.getId());
			jo.put("name", u.getName());
			jo.put("uname", u.getUsername());
			jo.put("address", u.getAddress());
			jo.put("email", u.getEmail());
			jo.put("ident", u.getIdent());
			jo.put("phone", u.getPhone());
			jo.put("password", u.getPassword());
			json.add(jo);
		}
		if((name == null || name == "") && (ident == null || ident == "")){
			int pAll = service.findAllUsers();
			map.put("total", pAll);
		}else {
			int pPart = service.findPartUsers(name,ident);
			map.put("total", pPart);
		}
		map.put("rows", json);
		
		return JSONObject.fromObject(map).toString();
	}
	@RequestMapping(value="/roleList",
			method={RequestMethod.POST,RequestMethod.GET},
			produces={"text/html;charset=UTF-8;","application/x-www-form-urlencoded"})
	public @ResponseBody Object roleList(Map<String, Object> map,HttpServletRequest req,@RequestParam("page")int page,@RequestParam("rows")int rows) {
		String name=req.getParameter("name");
		String level = req.getParameter("level");
		List<Role> rlist = service.findPageRoles(name,level,page,rows);
		JSONArray json = new JSONArray();
		for(Role r:rlist) {
			JSONObject jo = new JSONObject();
			jo.put("id", r.getId());
			jo.put("rname", r.getRolename());
			jo.put("rcode", r.getRolecode());
			jo.put("rlevel", r.getLevel());
			jo.put("pid", r.getPid());
			json.add(jo);
		}
		if((name == null || name == "") && (level == null || level == "")){
			int pAll = service.findAllUsers();
			map.put("total", pAll);
		}else {
			int pPart = service.findPartRoles(name,level);
			map.put("total", pPart);
		}
		map.put("rows", json);
		
		return JSONObject.fromObject(map).toString();
	}
	
	@RequestMapping(value="/functionList",
			method={RequestMethod.POST,RequestMethod.GET},
			produces={"text/html;charset=UTF-8;","application/json;"})
	public @ResponseBody String functionList() {
		JSONObject jo = new JSONObject();
		jo.put("address", "system/functionList");
		jo.put("title", "系统功能");
		return jo.toString();
	}
	@RequestMapping(value="/getFunctions",
			method={RequestMethod.POST,RequestMethod.GET},
			produces={"text/html;charset=UTF-8;","application/x-www-form-urlencoded"})
	public @ResponseBody Object funcsList(Map<String, Object> map,HttpSession sessions,HttpServletRequest req) {
		int id = Integer.valueOf(req.getParameter("id")==null?"0":req.getParameter("id"));
		List<Func> flist = service.findFuncsById(id);
		JSONArray json = new JSONArray();
		for(Func func:flist) {
			String flag = "open"; 
			JSONObject jo = new JSONObject();
			List<Func> sflist = service.findFuncsById(func.getId());
			if(sflist.size()>0) {
				flag = "closed";
			}
			jo.put("func_flag", flag);
			jo.put("id", func.getId());
			jo.put("func_name", func.getFuncName());
			jo.put("func_code", func.getFuncCode());
			jo.put("pid",func.getPid());
			json.add(jo);
		}
		map.put("value", json);
		map.put("size", flist.size());
		return JSONObject.fromObject(map).toString();
	}
	@RequestMapping(value="/getRootPermission",
			method={RequestMethod.POST,RequestMethod.GET},
			produces={"text/html;charset=UTF-8;","application/x-www-form-urlencoded"})
	public @ResponseBody Object getRootPermission(Map<String, Object> map,HttpSession sessions,HttpServletRequest req) {
		Permission rootPer = service.getRoorPermission();
		JSONObject jo = new JSONObject();
		jo.put("id", rootPer.getId());
		jo.put("pname", rootPer.getpName());
		jo.put("pcode", rootPer.getpCode());
		jo.put("pid", rootPer.getPid());
		jo.put("url",rootPer.getUrl());
		return jo.toString();
	}
	@RequestMapping(value="/getChildrenPers",
			method={RequestMethod.POST,RequestMethod.GET},
			produces={"text/html;charset=UTF-8;","application/x-www-form-urlencoded"})
	public @ResponseBody Object getChildrenPers(Map<String, Object> map,HttpSession sessions,HttpServletRequest req) {
		int id = Integer.valueOf(req.getParameter("id")==null?"0":req.getParameter("id"));
		List<Permission> flist = service.findPersById(id);
		JSONArray json = new JSONArray();
		for(Permission per:flist) {
			String flag = "open"; 
			JSONObject jo = new JSONObject();
			List<Permission> pflist = service.findPersById(per.getId());
			if(pflist.size()>0) {
				flag = "closed";
			}
			jo.put("per_flag", flag);
			jo.put("per_id", per.getId());
			jo.put("per_name", per.getpName());
			jo.put("per_code", per.getpCode());
			jo.put("per_url", per.getUrl());
			jo.put("pid",per.getPid());
			json.add(jo);
		}
		map.put("value", json);
		map.put("size", flist.size());
		return JSONObject.fromObject(map).toString();
	}
	@RequestMapping(value="/getPersonalPers",
			method={RequestMethod.POST,RequestMethod.GET},
			produces={"text/html;charset=UTF-8;","application/x-www-form-urlencoded"})
	public @ResponseBody Object getPersonalPers(Map<String, Object> map,HttpSession sessions,HttpServletRequest req) {
		int mid = Integer.valueOf(req.getParameter("id")==null?"0":req.getParameter("id")==""?"0":req.getParameter("id"));
		List<Permission> flist = service.findPersonalPersById(mid);
		JSONArray json = new JSONArray();
		for(Permission per:flist) {
			JSONObject jo = new JSONObject();
			jo.put("per_id", per.getId());
			jo.put("per_name", per.getpName());
			jo.put("per_code", per.getpCode());
			jo.put("per_url", per.getUrl());
			jo.put("pid",per.getPid());
			json.add(jo);
		}
		map.put("value", json);
		map.put("size", flist.size());
		return JSONObject.fromObject(map).toString();
	}
	@RequestMapping(value="/addPersonalPers",
			method={RequestMethod.POST,RequestMethod.GET},
			produces={"text/html;charset=UTF-8;","application/x-www-form-urlencoded"})
	public @ResponseBody Object addPersonalPers(Map<String, Object> map,HttpSession sessions,HttpServletRequest req) {
		JSONObject jo = new JSONObject();
		int rid = Integer.valueOf(req.getParameter("id")==null?"0":req.getParameter("id")==""?"0":req.getParameter("id"));
		String ids = req.getParameter("ids");
		service.changePers(rid, ids);
		jo.put("title", "分配权限操作完成！");
		return jo.toString();
	}
}
