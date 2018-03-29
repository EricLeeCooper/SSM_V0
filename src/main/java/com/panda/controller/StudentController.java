package com.panda.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

//import com.panda.entity.Count;
import com.panda.entity.Count;
import com.panda.entity.FileUpload;
import com.panda.entity.Student;
import com.panda.entity.User;
//import com.panda.entity.User;
import com.panda.service.CountService;

@Controller
@RequestMapping
public class StudentController {
	@Resource
	private CountService service;
//	private HttpSession session =null;
//	学生列表页眉
	@RequestMapping(value="/show.studentListPage.do",
			method={RequestMethod.POST,RequestMethod.GET},
			produces={"text/html;charset=UTF-8;","application/json;"})
	public  @ResponseBody String studentListPage(Map<String, Object> map){
		 JSONObject jo = new JSONObject();
		 jo.put("address", "student/studentList");
		 jo.put("title", "学生列表");
		return jo.toString();
		
	}
//	个人信息页眉
	@RequestMapping(value="/show.personalPage.do",
			method={RequestMethod.POST,RequestMethod.GET},
			produces={"text/html;charset=UTF-8;","application/json;"})
	public  @ResponseBody String personalPage(Map<String, Object> map){
		JSONObject jo = new JSONObject();
		jo.put("address", "jsp/success");
		jo.put("title", "个人信息");
		return jo.toString();
		
	}
//	个人信息详情
	@RequestMapping(value="/show.personalInfo.do",
			method={RequestMethod.POST,RequestMethod.GET},
			produces={"text/html;charset=UTF-8;","application/json;"})
	public  @ResponseBody Object personalInfo(Map<String, Object> map,HttpSession sessions){
		int id = (Integer)sessions.getAttribute("userid");
		Count count = service.serchOneByUserId(id);
		User user = service.findUserById(id);
		String userIdenti = service.findIdentiByUid(id);
		String fileurl = service.findFile(id);
		map.put("count", count.getCount()); 
		map.put("userIdenti", userIdenti);
		map.put("user", user.getName()); 
		map.put("fileurl", fileurl);
		return JSONObject.fromObject(map).toString();
	}
//	学生列表详情
	@RequestMapping(value="/show.studentList.do",
			method={RequestMethod.POST,RequestMethod.GET},
			produces={"application/text;charset=UTF-8"})
	public @ResponseBody Object studentList(Map<String, Object> map,
			HttpServletRequest req,
			@RequestParam("page")int page,@RequestParam("rows")int rows){
		String name=req.getParameter("username");
		String address = req.getParameter("useraddress");
		List<Student> studentList = service.findPartStudent(rows,page,name,address);
		JSONArray json = new JSONArray();
        for(Student a : studentList){
            JSONObject jo = new JSONObject();
            jo.put("id", a.getId());
            jo.put("name", a.getName());
            jo.put("age", a.getAge());
            jo.put("phone", a.getPhone());
            jo.put("address", a.getAddress());
            json.add(jo);
        }
        if((name == null || name == "") && (address == null || address == "")){
        	List<Student> studentLists = service.findAllStudent();
        	map.put("total", studentLists.size());  
        } else {
        	List<Student> studentListPart = service.findPartStudents(name,address);
        	map.put("total", studentListPart.size());  
        }
        map.put("rows", json);
		return JSONObject.fromObject(map).toString();
	}
//	添加学生
	@RequestMapping(value="/show.addStudent.do",
			method={RequestMethod.POST,RequestMethod.GET})
	public void addStudent(Map<String, Object> map,
			HttpServletRequest req,Student student){
		service.addStudent(student);
	}
//	删除学生信息
	@RequestMapping(value="/show.deleteStudent.do",
			method={RequestMethod.POST,RequestMethod.GET})
	public void deleteStudent(Map<String, Object> map,
			HttpServletRequest req,@RequestParam("id")int id){
		service.deleteStudent(id);
	}
//	修改学生信息
	@RequestMapping(value="/show.updateStudent.do",
			method={RequestMethod.POST,RequestMethod.GET},
			produces={"application/json;charset=UTF-8"})
	public @ResponseBody Object updateStudent(Map<String, Object> map,
			HttpServletRequest req,@RequestParam("id")int id){
		 Student student = service.getStudentById(id);
		 JSONObject jo = new JSONObject();
         jo.put("id", student.getId());
         jo.put("name", student.getName());
         jo.put("age", student.getAge());
         jo.put("phone", student.getPhone());
         jo.put("address", student.getAddress());
         map.put("jo", jo);
         return jo.toString();
	}
	//	保存修改学生信息
	@RequestMapping(value="/show.updateSaveStudent.do",
			method={RequestMethod.POST,RequestMethod.GET})
	public void updateSaveStudent(Map<String, Object> map,
			HttpServletRequest req,Student student){
		service.updateSaveStudent(student);
	}
	
	//	上传图片
	@RequestMapping(value="/newPicture.do",method={RequestMethod.POST,RequestMethod.GET})
	public void newPicture(Map<String, Object> map,HttpServletRequest req,@RequestParam("file") CommonsMultipartFile file)throws IOException{
		  long  startTime=System.currentTimeMillis();
	        System.out.println("fileName："+file.getOriginalFilename());
        	String url="upload/"+new Date().getTime()+file.getOriginalFilename();
        	//字节流实现
	        /*try {
	            //获取输出流
	            OutputStream os=new FileOutputStream("E:/"+url);
	            //获取输入流 CommonsMultipartFile 中可以直接得到文件的流
	            InputStream is=file.getInputStream();
	            int temp;
	            //一个一个字节的读取并写入
	            while((temp=is.read())!=(-1))
	            {
	                os.write(temp);
	            }
	           os.flush();
	           os.close();
	           is.close();
	         
	        } catch (FileNotFoundException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }*/
        	//springmvc自带transferTo方法实现
        	File newFile = new File("E:/"+url);
        	if(!newFile.exists()){
        		newFile.mkdirs();
        	}
        	file.transferTo(newFile);
	        long  endTime=System.currentTimeMillis();
	        System.out.println("方法的运行时间："+String.valueOf(endTime-startTime)+"ms");
	        String userName =  (String)SecurityUtils.getSubject().getPrincipal();
	        int userId = service.findUserPassword(userName).getId();
	        map.put("userId", userId);
	        System.out.println(url);
	        map.put("url", url);
	        FileUpload fileUpload = service.findUploadByUserId(userId);
	        if(fileUpload != null){
	        	service.updateNewFile(map);
	        }else{
	        	 service.addNewFile(map);
	        }
		}
	@RequestMapping(value="/show/exportStudent.do",
			method={RequestMethod.POST,RequestMethod.GET})
	public void exportStudent(Map<String, Object> map,
			HttpServletRequest req,HttpServletResponse response,String ids){
		try {
			System.out.println(ids);
			List<Student> students = service.exportStudents(ids);
			String[] headNames = new String[] { "编号", "姓名", "年龄","电话","地址"};
			String fileName="学生信息.xlsx";		
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-disposition", "attachment;filename="
					+ URLEncoder.encode(fileName, "utf-8"));
			OutputStream out=response.getOutputStream();
			service.expToExcel(out, headNames,fileName, students);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping(value="/show/downloadStudent.do",
			method={RequestMethod.POST,RequestMethod.GET})
	public void downloadStudent(Map<String, Object> map,
			HttpServletRequest req,HttpServletResponse response){
		try {
			String fileName="学生信息.xlsx";		
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-disposition", "attachment;filename="
					+ URLEncoder.encode(fileName, "utf-8"));
			OutputStream out=response.getOutputStream();
			service.downLoadMasterplate(out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping(value="/show/uploadStudent.do",
			method={RequestMethod.POST,RequestMethod.GET})
	public void uploadStudent(Map<String, Object> map,
			HttpServletRequest req,HttpServletResponse response,
			@RequestParam("file") CommonsMultipartFile file){
		try {
			System.out.println("fileName："+file.getOriginalFilename());
	        String url="uploadExcel/"+new Date().getTime()+file.getOriginalFilename();
			File newFile = new File("E:/"+url);
        	if(!newFile.exists()){
        		newFile.mkdirs();
        	}
        	file.transferTo(newFile);
        	FileInputStream fs = new FileInputStream(newFile);
        	List<Student> students = service.uploadExcel(fs);
        	for(Student student:students){
        		service.addStudent(student);
        	}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
