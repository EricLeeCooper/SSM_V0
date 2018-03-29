package com.panda.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.panda.entity.User;

public interface FriendsMapper {

	List<User> findPartUsers(@Param("map")Map<String, Object> map);

	List<User> findPartFriends(@Param("map")Map<String, Object> map);

	List<User> findAllFriends(@Param("userId")int userId);

	List<User> findAllUsers(@Param("map")Map<String, Object> map);

	List<User> findAlluser(@Param("map")Map<String, Object> map);

	void deleteFriend(@Param("map")Map<String, Object> map);

	void deleteDFriend(@Param("map")Map<String, Object> map);

	User findFriendOfMine(@Param("map")Map<String, Object> map);

	void addFriend(@Param("map")Map<String, Object> map);

	void addDFriend(@Param("map")Map<String, Object> map);
	
}
