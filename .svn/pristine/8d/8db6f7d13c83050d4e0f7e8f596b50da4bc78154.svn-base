package com.panda.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.panda.entity.Count;
import com.panda.entity.FileUpload;
import com.panda.entity.GameScore;
import com.panda.entity.Student;
import com.panda.entity.User;

public interface CountService {
	public void addInfo(Map<String, Object> map);
	public Count serchOne(int id);
	public void addUser(Map<String, Object> map);
	public User findUserPassword(String username);
	public Count serchOneByUserId(int userid);
	public Set<String> getRolesByUsername(String username);
	public Set<String> getPersByUsername(String username);
	public User getUserByUsername(String username);
	public User getNewUser();
	public void addCountInfo(int userId);
	public void addRoleInfo(int userId);
	public int findCountByUserId(int userId);
	public void translateCount(int userId, int tuserId, int tmoney);
	public int findUserIdByUsername(String username);
	public List<User> getAllUsers();
	public FileUpload findUploadByUserId(int userId);
	public void updateNewFile(Map<String, Object> map);
	public void addNewFile(Map<String, Object> map);
	public List<Student> findAllStudent();
	public List<Student> findPartStudent(int rows, int page,String name,String address);
	public List<Student> findPartStudents(String name, String address);
	public void addStudent(Student student);
	public void deleteStudent(int id);
	public Student getStudentById(int id);
	public void updateSaveStudent(Student student);
	public User findUserById(int id);
	public String findIdentiByUid(int id);
	public String findFile(int id);
	public List<User> findFriendsByUserId(int userId);
	public void addScoreRecode(int scoreInt, int userId);
	public int getLargestScore();
	public List<GameScore> getScoreList();
	public List<GameScore> getScoreListByPR(int page, int rows);
	public List<Student> exportStudents(String ids);
	public void expToExcel(OutputStream out, String[] headNames,
			String fileName, List<Student> students) throws IOException;
	public void downLoadMasterplate(OutputStream out)throws IOException;
	public List<Student> uploadExcel(FileInputStream fs)throws IOException;
}
