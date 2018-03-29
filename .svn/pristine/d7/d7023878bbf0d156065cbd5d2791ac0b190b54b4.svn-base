package com.panda.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.panda.dao.FriendsMapper;
import com.panda.entity.User;
import com.panda.service.FriendsService;
@Service("friendsService")
public class FriendsServiceImpl implements FriendsService {
	@Resource
	private FriendsMapper friendsMapper;

	
	public List<User> findPartFriends(int userId,int rows, int page, String name,
			String address) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		int start = (page-1)*rows;
		map.put("start", start);
		map.put("rows", rows);
		map.put("name", name);
		map.put("address", address);
		map.put("userId", userId);
		return friendsMapper.findPartUsers(map);
	}

	
	public List<User> findAllFriends(int id) {
		// TODO Auto-generated method stub
		return friendsMapper.findAllFriends(id);
	}

	
	public List<User> findPartFriends(int userId,String name, String address) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("name", name);
		map.put("address", address);
		map.put("userId", userId);
		return friendsMapper.findPartFriends(map);
	}

	
	public List<User> findAllUsers(int rows, int page, String name,
			String address) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		int start = (page-1)*rows;
		map.put("start", start);
		map.put("rows", rows);
		map.put("name", name);
		map.put("address", address);
		return friendsMapper.findAllUsers(map);
	}


	
	public List<User> findPartFriend(int rows, int page, String name,
			String address) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		int start = (page-1)*rows;
		map.put("start", start);
		map.put("rows", rows);
		map.put("name", name);
		map.put("address", address);
		return friendsMapper.findPartUsers(map);
	}

	
	public List<User> findPartUser(String name, String address) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("name", name);
		map.put("address", address);
		return friendsMapper.findAlluser(map);
	}

	
	public void deleteFriend(int userId,int id) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", id);
		map.put("userId", userId);
		friendsMapper.deleteFriend(map);//删除我的好友列表中的某位好友
		friendsMapper.deleteDFriend(map);//将这位好友列表中的我删除
	}

	
	public void addFriend(int userId, int id) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", id);
		map.put("userId", userId);
		friendsMapper.addFriend(map);//在我的好友列表中添加好友
		friendsMapper.addDFriend(map);//在好友的列表中添加我的信息
	}

	
	public User findFriendOfMine(int userId, int id) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", id);
		map.put("userId", userId);
		return friendsMapper.findFriendOfMine(map);
	}

	

}
