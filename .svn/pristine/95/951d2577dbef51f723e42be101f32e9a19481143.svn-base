package com.panda.service.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.panda.dao.CountMapper;
import com.panda.entity.Count;
import com.panda.entity.FileUpload;
import com.panda.entity.GameScore;
import com.panda.entity.Student;
import com.panda.entity.User;
import com.panda.service.CountService;
@Service("countService")
public class CountServiceImpl implements CountService {
	@Resource
	private CountMapper countMapper;
	public void addInfo(Map<String, Object> map) {
		// TODO Auto-generated method stub
		countMapper.addInfo(map);
	}
	public Count serchOne(int id) {
		// TODO Auto-generated method stub
		return this.countMapper.serchOne(id);
	}
	public void addUser(Map<String, Object> map) {
		this.countMapper.addUser(map);
		
	}
	public User findUserPassword(String username) {
		// TODO Auto-generated method stub
		return this.countMapper.findUserPassword(username);
	}
	public Count serchOneByUserId(int user_id) {
		// TODO Auto-generated method stub
		return this.countMapper.serchOneByUserId(user_id);
	}
	public Set<String> getRolesByUsername(String username) {
		
		return this.countMapper.getRolesByUsername(username);
	}
	public Set<String> getPersByUsername(String username) {
		// TODO Auto-generated method stub
		return this.countMapper.getPersByUsername(username);
	}
	public User getUserByUsername(String username) {
		// TODO Auto-generated method stub
		User user=this.countMapper.findUserPassword(username);
		return user;
	}
	public User getNewUser() {
		// TODO Auto-generated method stub
		return this.countMapper.getNewUser();
	}
	
	public void addCountInfo(int userId) {
		// TODO Auto-generated method stub
		this.countMapper.addCountInfo(userId);
	}
	
	public void addRoleInfo(int userId) {
		// TODO Auto-generated method stub
		this.countMapper.addRoleInfo(userId);
	}
	
	public int findCountByUserId(int userId) {
		// TODO Auto-generated method stub
		return this.countMapper.findCountByUserId(userId);
	}
	
	public void translateCount(int userId, int tuserId, int tmoney) {
		Map<String, Object> map1 = new HashMap<String, Object>();
		Map<String, Object> map2 = new HashMap<String, Object>();
		map1.put("userId", userId);
		map1.put("tmoney", tmoney);
		map2.put("tuserId", tuserId);
		map2.put("tmoney", tmoney);
		this.countMapper.decreaseCount(map1);
		System.out.println("账户金额已减");
		this.countMapper.raiseCount(map2);
	}
	
	public int findUserIdByUsername(String username) {
		// TODO Auto-generated method stub
		return this.countMapper.findUserIdByUsername(username);
	}
	
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return this.countMapper.getAllUsers();
	}
	
	public FileUpload findUploadByUserId(int userId) {
		// TODO Auto-generated method stub
		return this.countMapper.findUploadByUserId(userId);
	}
	
	public void updateNewFile(Map<String, Object> map) {
		// TODO Auto-generated method stub
		this.countMapper.updateNewFile(map);
	}
	
	public void addNewFile(Map<String, Object> map) {
		// TODO Auto-generated method stub
		this.countMapper.addNewFile(map);
	}
	public List<Student> findAllStudent() {
		return this.countMapper.findAllStudent();
	}
	public List<Student> findPartStudent(int rows, int page,String name,String address) {
		Map<String,Object> map = new HashMap<String,Object>();
		int start = (page-1)*rows;
		map.put("start", start);
		map.put("rows", rows);
		map.put("name", name);
		map.put("address", address);
		return this.countMapper.findPartStudent(map);
	}
	public List<Student> findPartStudents(String name, String address) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("name", name);
		map.put("address", address);
		return this.countMapper.findPartStudents(map);
	}
	public void addStudent(Student student) {
		// TODO Auto-generated method stub
		this.countMapper.addStudent(student);
	}
	public void deleteStudent(int id) {
		// TODO Auto-generated method stub
		this.countMapper.deleteStudent(id);
	}
	public Student getStudentById(int id) {
		// TODO Auto-generated method stub
		return this.countMapper.getStudentById(id);
	}
	public void updateSaveStudent(Student student) {
		// TODO Auto-generated method stub
		this.countMapper.updateSaveStudent(student);
	}
	public User findUserById(int id) {
		// TODO Auto-generated method stub
		return this.countMapper.findUserById(id);
	}
	
	public String findIdentiByUid(int id) {
		// TODO Auto-generated method stub
		return this.countMapper.findIdentiByUid(id);
	}
	
	public String findFile(int id) {
		return this.countMapper.findPicture(id);
	}
	
	public List<User> findFriendsByUserId(int userId) {
		// TODO Auto-generated method stub
		return this.countMapper.findFriendsByUserId(userId);
	}
	
	public void addScoreRecode(int scoreInt, int userId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("scoreInt", scoreInt);
		map.put("userId", userId);
		this.countMapper.addScoreRecode(map);
	}
	
	public int getLargestScore() {
		// TODO Auto-generated method stub
		return this.countMapper.getLargestScore();
	}
	
	public List<GameScore> getScoreList() {
		// TODO Auto-generated method stub
		return  this.countMapper.getScoreList();
	}
	
	public List<GameScore> getScoreListByPR(int page, int rows) {
		int start = (page-1)*rows;
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("start", start);
		map.put("rows", rows);
		return this.countMapper.getScoreListByPR(map);
	}
	
	public List<Student> exportStudents(String ids) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("ids", ids);
		return this.countMapper.exportStudents(map);
	}
	
	public void expToExcel(OutputStream out, String[] headNames,
			String fileName, List<Student> student) throws IOException {
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet(" ");
		//设置数据样式
		XSSFCellStyle Style01 = (XSSFCellStyle) wb.createCellStyle();
		Style01.setAlignment(HorizontalAlignment.CENTER); // 居中
//		//设置标题样式
		XSSFCellStyle Style02 = (XSSFCellStyle) wb.createCellStyle();
		Style02.setAlignment(HorizontalAlignment.CENTER);
		Font Font01 = wb.createFont();
		Font01.setColor(Font.COLOR_RED);
		Style02.setFont(Font01);
		XSSFCellStyle Style = (XSSFCellStyle)wb.createCellStyle(); // 样式对象
		Style.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直
		Style.setAlignment(HorizontalAlignment.CENTER);// 水平
		
		Row row1 = sheet.createRow(0);   //--->创建一行
		// 四个参数分别是：起始行，起始列，结束行，结束列
		sheet.addMergedRegion(new CellRangeAddress(0, (short) 0, 0, (short) 14));
		row1.setHeightInPoints(25);
		Cell cell1 = row1.createCell((short)0);   //--->创建一个单元格
	    cell1.setCellStyle(Style);
		cell1.setCellValue("学生信息");
		
		if (headNames.length>0) {
			// 先制作表头
			Row row = sheet.createRow(1);
			for (int i=0;i<headNames.length;i++ ) {
				Cell cell=row.createCell(i);
				cell.setCellStyle(Style02);
				cell.setCellValue(headNames[i]);
			}
		}
		// 将数据据行写入excel
		for (int i=0;i<student.size();i++) {
			Row row = sheet.createRow(i+2);
			row.createCell(0).setCellValue(String.valueOf(student.get(i).getId()));
			row.createCell(1).setCellValue(student.get(i).getName());
			row.createCell(2).setCellValue(String.valueOf(student.get(i).getAge()));
			row.createCell(3).setCellValue(student.get(i).getPhone());
			row.createCell(4).setCellValue(student.get(i).getAddress());
		}
		// 自适应列宽
		for (int i = 0; i <5; i++) {
			int l = sheet.getColumnWidth(i) / 256;
			for (int j = 0; j <= sheet.getLastRowNum(); j++) {
				Row r = sheet.getRow(j);
				if (r.getCell(i) != null) {
					int c = r.getCell(i).getStringCellValue().getBytes().length;
					if (c > l) {
						l = c;
					}
				}
			}
			sheet.setColumnWidth(i, l * 256 + 1000);
		}
		wb.write(out);
		out.close();
	}
	
	public void downLoadMasterplate(OutputStream out) throws IOException {
		// TODO Auto-generated method stub
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet(" ");
		//设置数据样式
		XSSFCellStyle Style01 = (XSSFCellStyle) wb.createCellStyle();
		Style01.setAlignment(HorizontalAlignment.CENTER); // 居中
//		//设置标题样式
		XSSFCellStyle Style02 = (XSSFCellStyle) wb.createCellStyle();
		Style02.setAlignment(HorizontalAlignment.CENTER);
		Font Font01 = wb.createFont();
		Font01.setColor(Font.COLOR_RED);
		Style02.setFont(Font01);
		XSSFCellStyle Style = (XSSFCellStyle)wb.createCellStyle(); // 样式对象
		Style.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直
		Style.setAlignment(HorizontalAlignment.CENTER);// 水平
		
		Row row1 = sheet.createRow(0);   //--->创建一行
		// 四个参数分别是：起始行，起始列，结束行，结束列
		sheet.addMergedRegion(new CellRangeAddress(0, (short) 0, 0, (short) 8));
		row1.setHeightInPoints(25);
		Cell cell1 = row1.createCell((short)0);   //--->创建一个单元格
	    cell1.setCellStyle(Style);
		cell1.setCellValue("学生信息");
		// 先制作表头
		Row row = sheet.createRow(1);
		Cell cell=row.createCell(0);
		cell.setCellStyle(Style02);
		cell.setCellValue("学生编号");
		Cell cell01=row.createCell(1);
		cell01.setCellStyle(Style02);
		cell01.setCellValue("学生姓名");
		Cell cell2=row.createCell(2);
		cell2.setCellStyle(Style02);
		cell2.setCellValue("学生年龄");
		Cell cell3=row.createCell(3);
		cell3.setCellStyle(Style02);
		cell3.setCellValue("学生电话");
		Cell cell4=row.createCell(4);
		cell4.setCellStyle(Style02);
		cell4.setCellValue("学生地址");
		wb.write(out);
		out.close();
	}
	public List<Student> uploadExcel(FileInputStream fs) throws IOException {
		XSSFWorkbook wb = new XSSFWorkbook(fs);
		//得到一个工作表
		XSSFSheet sheet = wb.getSheetAt(0);
        //获得表头
        Row rowHead = sheet.getRow(1);
        //判断表头是否正确
        if(rowHead.getPhysicalNumberOfCells() != 5){
        	System.out.println("-----错误------");
        }
        
        //获得数据的总行数
        int totalRowNum = sheet.getLastRowNum();
        
        //要获得属性
        String stuid = "";
        String name = "";
        int age = 0;
        String phone = "";
        String address = "";
        List<Student> students = new ArrayList<Student>();
       //获得所有数据
        for(int i = 2 ; i <= totalRowNum ; i++){
        	Student student = new Student();
            //获得第i行对象
            Row row = sheet.getRow(i);
            
            //获得获得第i行第0列的 String类型对象
            Cell cell = row.getCell((short)0);
            stuid = cell.getStringCellValue().toString();
            //获得获得第i行第1列的 String类型对象
            cell = row.getCell((short)1);
            name = cell.getStringCellValue().toString();
            student.setName(name);
            //获得一个数字类型的数据
            cell = row.getCell((short)2);
            age = Integer.valueOf(cell.getStringCellValue());
            student.setAge(age);
            //获得获得第i行第3列的 String类型对象
            cell = row.getCell((short)3);
            phone = cell.getStringCellValue().toString();
            student.setPhone(phone);
            //获得获得第i行第4列的 String类型对象
            cell = row.getCell((short)4);
            address = cell.getStringCellValue().toString();
            student.setAddress(address);
            students.add(student);
            System.out.println("编号："+stuid+",姓名："+name+",年龄："+age+",电话："+phone+",地址："+address);
        }
        return students;
	}

}
