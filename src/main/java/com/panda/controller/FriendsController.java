package com.panda.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.panda.entity.User;
import com.panda.service.FriendsService;

@Controller
@RequestMapping("/friends")
public class FriendsController {
	@Resource
	private FriendsService service;
//	private HttpSession session =null;
//	好友列表页眉
	@RequestMapping(value="/friendsPage.do",
			method={RequestMethod.POST,RequestMethod.GET},
			produces={"text/html;charset=UTF-8;","application/json;"})
	public  @ResponseBody String friendsListPage(Map<String, Object> map){
		 JSONObject jo = new JSONObject();
		 jo.put("address", "friends/friendsList");
		 jo.put("title", "好友列表");
		return jo.toString();
		
	}

//	好友列表详情
	@RequestMapping(value="/friendsList.do",
			method={RequestMethod.POST,RequestMethod.GET},
			produces={"application/text;charset=UTF-8"})
	public @ResponseBody Object friendsList(Map<String, Object> map,
			HttpServletRequest req,HttpSession sessions,
			@RequestParam("page")int page,@RequestParam("rows")int rows){
		String name=req.getParameter("username");
		String address = req.getParameter("useraddress");
		String addf = req.getParameter("addf");
		List<User> friendsList=null;
		int userId = (Integer)sessions.getAttribute("userid");
		if(!"addf".equals(addf)){
			friendsList = service.findPartFriends(userId,rows,page,name,address);
		}else{
			friendsList = service.findPartFriend(rows,page,name,address);
		}
		JSONArray json = new JSONArray();
        for(User a : friendsList){
            JSONObject jo = new JSONObject();
            jo.put("id", a.getId());
            jo.put("name", a.getName());
            jo.put("age", a.getAge());
            jo.put("phone", a.getPhone());
            jo.put("address", a.getAddress());
            json.add(jo);
        }
        if(!"addf".equals(addf)){
	    	 if((name == null || name == "") && (address == null || address == "")){
	         	List<User> friendLists = service.findAllFriends(userId);
	         	map.put("total", friendLists.size());  
	         } else {
	         	List<User> friendListPart = service.findPartFriends(userId,name,address);
	         	map.put("total", friendListPart.size());  
	         }
        }else{
        	List<User> friendListPart = service.findPartUser(name,address);
         	map.put("total", friendListPart.size()); 
        }
        map.put("rows", json);
		return JSONObject.fromObject(map).toString();
	}
//	
//	删除好友信息
		@RequestMapping(value="/deleteFriend.do",
			method={RequestMethod.POST,RequestMethod.GET})
	public void deleteFriend(Map<String, Object> map,HttpSession sessions,
			HttpServletRequest req,@RequestParam("id")int id){
			int userId = (Integer)sessions.getAttribute("userid");
			service.deleteFriend(userId,id);
	}
//添加好友
	@RequestMapping(value="/addFriend.do",
			method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody Object addFriend(Map<String, Object> map,HttpSession sessions,
			HttpServletRequest req,@RequestParam("id")int id){
		int userId = (Integer)sessions.getAttribute("userid");
		JSONObject jo = new JSONObject();
		if(userId == id){
			jo.put("message", "me");
		}else{
			User user = service.findFriendOfMine(userId,id);
			if(user == null){
				jo.put("message", "yes"); 
			}else{
				jo.put("message", "no");
			}
		}
		return jo.toString();
	}
//确认添加
	@RequestMapping(value="/addFriendToMe.do",
			method={RequestMethod.POST,RequestMethod.GET})
	public void addFriendTo(Map<String, Object> map,HttpSession sessions,
			HttpServletRequest req,@RequestParam("id")int id){
		int userId = (Integer)sessions.getAttribute("userid");
		service.addFriend(userId, id);
	}
}
