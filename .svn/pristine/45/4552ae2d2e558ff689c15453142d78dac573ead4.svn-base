package com.panda.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.panda.entity.Count;
import com.panda.entity.FileUpload;
import com.panda.entity.GameScore;
import com.panda.entity.Student;
import com.panda.entity.User;

public interface CountMapper {
	void addInfo(@Param("map")Map<String, Object> map);
	Count serchOne(int id);
	void addUser(@Param("map")Map<String, Object> map);
	User findUserPassword(@Param("username")String username);
	Count serchOneByUserId(int user_id);
	Set<String> getRolesByUsername(String username);
	Set<String> getPersByUsername(String username);
	User getUserByUsername(@Param("username")String username);
	User getNewUser();
	void addCountInfo(@Param("userId")int userId);
	void addRoleInfo(@Param("userId")int userId);
	int findCountByUserId(@Param("userId")int userId);
	int findUserIdByUsername(@Param("username")String username);
	void decreaseCount(@Param("map")Map<String, Object> map);
	void raiseCount(@Param("map")Map<String, Object> map);
	List<User> getAllUsers();
	FileUpload findUploadByUserId(@Param("userId")int userId);
	void updateNewFile(@Param("map")Map<String, Object> map);
	void addNewFile(@Param("map")Map<String, Object> map);
	List<Student> findAllStudent();
	List<Student> findPartStudent(@Param("map")Map<String, Object> map);
	List<Student> findPartStudents(@Param("map")Map<String, Object> map);
	void addStudent(@Param("pojo")Student student);
	void deleteStudent(@Param("id")int id);
	Student getStudentById(@Param("id")int id);
	void updateSaveStudent(@Param("pojo")Student student);
	User findUserById(@Param("id")int id);
	String findIdentiByUid(@Param("id")int id);
	String findPicture(@Param("id") int id);
	List<User> findFriendsByUserId(@Param("id")int id);
	void addScoreRecode(Map<String, Object> map);
	int getLargestScore();
	List<GameScore> getScoreList();
	List<GameScore> getScoreListByPR(@Param("map")Map<String, Object> map);
	List<Student> exportStudents(@Param("map")Map<String, Object> map);
}
