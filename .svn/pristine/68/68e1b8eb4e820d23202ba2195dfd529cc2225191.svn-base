package com.panda.controller;

import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;

import java.io.File;
import java.io.IOException;
import java.util.Date;
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
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import com.panda.entity.MAV;
import com.panda.service.MAVsService;

@Controller
@RequestMapping("/vedio")
public class VedioController {
	@Resource
	private MAVsService service;
//	private HttpSession session =null;
//	列表页眉
	@RequestMapping(value="/vedioPage.do",
			method={RequestMethod.POST,RequestMethod.GET},
			produces={"text/html;charset=UTF-8;","application/json;"})
	public  @ResponseBody String vedioPage(Map<String, Object> map){
		 JSONObject jo = new JSONObject();
		 jo.put("address", "vedio/MAVList");
		 jo.put("title", "娱乐列表");
		return jo.toString();
		
	}

//	列表详情
	@RequestMapping(value="/vedioList.do",
			method={RequestMethod.POST,RequestMethod.GET},
			produces={"application/text;charset=UTF-8"})
	public @ResponseBody Object vedioList(Map<String, Object> map,
			HttpServletRequest req,HttpSession sessions,
			@RequestParam("page")int page,@RequestParam("rows")int rows){
		String name=req.getParameter("username");
		String fileType = req.getParameter("fileType");
		int userId = (Integer)sessions.getAttribute("userid");
		List<MAV> mavs = service.findPartMAVs(userId,rows,page,name,fileType);
		JSONArray json = new JSONArray();
        for(MAV a : mavs){
            JSONObject jo = new JSONObject();
            jo.put("id", a.getId());
            jo.put("name", a.getName());
            jo.put("size",a.getSize() );
            jo.put("length",a.getLength());
            jo.put("path",a.getPath());
            jo.put("fileType",a.getFileType());
            json.add(jo);
        }
        if((name == null || name == "") && (fileType == null || fileType == "")){
        	List<MAV> mavLists = service.findAllMAVs(userId);
        	map.put("total", mavLists.size());  
        } else {
        	List<MAV> mavListPart = service.findPartMAVs(userId,rows,page,name,fileType);
        	map.put("total", mavListPart.size());  
        }
        map.put("rows", json);
		return JSONObject.fromObject(map).toString();
	}

//	删除
	@RequestMapping(value="/deleteMAV.do",
			method={RequestMethod.POST,RequestMethod.GET})
	public void deleteStudent(Map<String, Object> map,
			HttpServletRequest req,@RequestParam("id")int id){
		service.deleteMAV(id);
	}
//	修改
	@RequestMapping(value="/updateMAV.do",
			method={RequestMethod.POST,RequestMethod.GET},
			produces={"application/json;charset=UTF-8"})
	public @ResponseBody Object updateStudent(Map<String, Object> map,
			HttpServletRequest req,@RequestParam("id")int id){
		 MAV a = service.findMAVById(id);
         JSONObject jo = new JSONObject();
         jo.put("id", a.getId());
         jo.put("name", a.getName());
         jo.put("size",a.getSize() );
         jo.put("length",a.getLength());
         jo.put("path",a.getPath());
         jo.put("fileType",a.getFileType());
         map.put("jo", jo);
         return jo.toString();
	}
	//	保存修改信息
	@RequestMapping(value="/updateSaveMAV.do",
			method={RequestMethod.POST,RequestMethod.GET})
	public void updateSaveStudent(Map<String, Object> map,
			HttpServletRequest req,MAV mav){
		service.updateMAV(mav);
	}
	
	//	上传文件
	@RequestMapping(value="/addMAV.do",method={RequestMethod.POST,RequestMethod.GET})
	public void newPicture(Map<String, Object> map,HttpSession sessions,
			HttpServletRequest req,@RequestParam("file") CommonsMultipartFile file)throws IOException, Exception{
		
		int userId = (Integer)sessions.getAttribute("userid");
		long  startTime=System.currentTimeMillis();
		String filename = file.getOriginalFilename();
		String fileType = null;
		String[] asname = filename.split(".");
		String lastName = asname[asname.length-1];
		if(lastName == "mp3"){
			fileType = "音乐";
		}else if(lastName == "mp4"){
			fileType = "视频";
		}else{
			return;
		}
		
	    System.out.println("文件名："+filename+",类型为"+fileType);
	    map.put("name", filename);
	    map.put("fileType", fileType);
	    System.out.println("文件大小："+file.getSize()/1024/1024+"M");
	    map.put("size", file.getSize()/1024/1024+"M");
      	String url="upload/"+new Date().getTime()+filename;
        	//springmvc自带transferTo方法实现
        	File newFile = new File("E:/"+url);
        	if(!newFile.exists()){
        		newFile.mkdirs();
        	}
        	file.transferTo(newFile);
    	    //获取影音文件时长
        	String length=null;
    	    if(filename.contains(".mp4") || filename.contains(".mp3")){
    	    	Encoder en = new Encoder();
    	    	MultimediaInfo mi =  en.getInfo(newFile);
    	    	length = mi.getDuration()/1000/60+"分"+mi.getDuration()/1000%60+"秒";
    	    	System.out.println("文件长度："+length);
    	    }
	        map.put("length", length);
    	    long  endTime=System.currentTimeMillis();
	        System.out.println("方法的运行时间："+String.valueOf(endTime-startTime)+"ms");
//	        String userName =  (String)SecurityUtils.getSubject().getPrincipal();
	        map.put("userId", userId);
	        System.out.println("文件路径："+url);
	        map.put("path", url);
//	        MAV mav = service.findMAVByUserId(userId);
//	        if(mav != null){
//	        	service.updateMAV(map);
//	        }else{
	        	 service.addNewMAV(map); 
//	        }
		}
}
