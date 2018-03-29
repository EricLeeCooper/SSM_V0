package com.panda.service;

import java.util.List;

import com.panda.entity.User;

public interface FriendsService {

	List<User> findPartFriends(int userId,int rows, int page, String name, String address);

	List<User> findAllFriends(int id);

	List<User> findPartFriends(int userId,String name, String address);

	List<User> findAllUsers(int rows, int page, String name, String address);

	List<User> findPartFriend(int rows, int page, String name, String address);

	List<User> findPartUser(String name, String address);

	void deleteFriend(int userId,int id);

	void addFriend(int userId, int id);

	User findFriendOfMine(int userId, int id);
	
}
