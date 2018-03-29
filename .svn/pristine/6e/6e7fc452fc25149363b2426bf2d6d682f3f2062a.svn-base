package com.panda.controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.panda.entity.Count;
import com.panda.entity.FileUpload;
import com.panda.entity.GameScore;
import com.panda.entity.User;
import com.panda.service.CountService;
import com.panda.util.ValidateCode;

@Controller
@RequestMapping
public class PandaController {
	@Resource
	private CountService service;
	@RequestMapping(value="/show.login.do",method={RequestMethod.POST,RequestMethod.GET})
	public String login(Map<String, Object> map,HttpSession sessions,User user,HttpServletRequest request){
		String code = (String) sessions.getAttribute("validateCode");
        String submitCode = WebUtils.getCleanParam(request, "validateCode");
        if (StringUtils.isEmpty(submitCode) || !StringUtils.equals(code,submitCode.toLowerCase())) {
            return "redirect:/";
        }
		Subject subject= SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
		try{
			subject.login(token);
			User suser = service.findUserPassword(user.getUsername());
			Count count = service.serchOneByUserId(suser.getId());
			FileUpload file = service.findUploadByUserId(suser.getId());
			map.put("user", suser);
			map.put("file", file);
			map.put("count", count);
			sessions.setAttribute("userid", suser.getId());
			System.out.println(sessions.getMaxInactiveInterval());
			return "admin";
		}catch (Exception e) {
			e.printStackTrace();
			return "false";
		}
	}
//	注册用户
	@RequestMapping(value="/show.regist.do",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView regist(Map<String, Object> map,User user){
		map.put("user_name", user.getUsername());
		map.put("user_email",user.getEmail());
		map.put("user_password", user.getPassword());
		String idcode = user.getIdent();
		String ident = null;
		if("teac".equals(idcode)) {
			ident = "教师";
		}else if("stud".equals(idcode)) {
			ident = "学生";
		}else {
			ident = "游客";
		}
		map.put("user_ident", ident);
		map.put("user_identnum", user.getIdentnum());
		service.addUser(map);
		ModelAndView mv = new ModelAndView();
		mv.addObject("user", map);
		mv.setViewName( "jsp/registSucess");
		return mv;
	}
//	获取session信息，查看是否过期
	@RequestMapping(value="/getSessionInfor.do",method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String getSessionInfor(HttpSession sessions,Map<String, Object> map,HttpServletRequest request){
		String isPast = "1";//0代表session已经过期，1代表未过期
		JSONObject jo = new JSONObject();
		/*if(null!=request.getSession(false)){   
		   if(request.getSession(false).isNew()){ 
			   System.out.println("session已经过期");
			   isPast = "0";
		   }else{   
				System.out.println("session未过期");   
				isPast = "1";
		   }   
		}else{
			isPast = "0";
		}*/
		try{
			int userid = (Integer) sessions.getAttribute("userid");
			System.out.println("当前用户id:"+userid);
			System.out.println("当前session的ID : "+request.getSession(true).getId());
		}catch(Exception a){
			isPast = "0";
			System.out.println("当前session已经过期！"+a.getMessage());
		}
		jo.put("ifTimeout",isPast);
		return jo.toString();
	}
//	转入注册页面
	@RequestMapping(value="/regist.do",method={RequestMethod.POST,RequestMethod.GET})
	public String registPage(Map<String, Object> map){
			return "jsp/registUser";
	}
//	游戏页眉
	@RequestMapping(value="/show.gamePage.do",
			method={RequestMethod.POST,RequestMethod.GET},
			produces={"text/html;charset=UTF-8;","application/json;"})
	public  @ResponseBody String gamePage(Map<String, Object> map){
		 JSONObject jo = new JSONObject();
		 jo.put("address", "jsp/gamepage");
		 jo.put("title", "小游戏");
		return jo.toString();
		
	}
//	转入转账页面
	@RequestMapping(value="/translate.do",method={RequestMethod.POST,RequestMethod.GET},
			produces={"application/text;charset=UTF-8"})
	public @ResponseBody Object translate(Map<String, Object> map,HttpSession sessions){
		int userId = (Integer)sessions.getAttribute("userid");
		JSONArray ja = new JSONArray();
		List<User> friends = service.findFriendsByUserId(userId);
		JSONObject jo = new JSONObject();
		for(User user:friends){
			jo.put("username", user.getName());
			jo.put("fid", user.getId());
			ja.add(jo);
		}
		return ja.toString();
	}
//	转账
	@RequestMapping(value="/show.translate.do",method={RequestMethod.POST,RequestMethod.GET},
			produces={"application/text;charset=UTF-8"})
	public @ResponseBody Object translateMoney(Map<String, Object> map,HttpSession sessions,
			@RequestParam("userId")String userId,@RequestParam("money")String money){
		int wuserId = (Integer)sessions.getAttribute("userid");
		int fuserId = Integer.valueOf(userId);
		int realMoney = service.findCountByUserId(wuserId);
		int tmoney = Integer.valueOf(money);
		JSONObject jo = new JSONObject();
		if(realMoney>=tmoney){
			service.translateCount(wuserId,fuserId,tmoney);
			jo.put("message","转账成功" );
		}else{
			jo.put("message","转账失败" );
		}
		return jo.toString();
	}
//	用户管理列表
	@RequestMapping(value="/userManage.do",method={RequestMethod.POST,RequestMethod.GET})
	public String userManage(Map<String, Object> map){
		List<User> list = service.getAllUsers();
		map.put("list", list);
		return "managerPage/userList";
	}
//	退出
	@RequestMapping(value="/logout.do")
	public String logout(){
		Subject subject= SecurityUtils.getSubject();
		subject.logout();
		return "index";
	}

	@RequestMapping(value="/test.do",method={RequestMethod.POST,RequestMethod.GET})
	public void test(){
		
		System.out.println("123");
	}
//	转入注册页面
	@RequestMapping(value="/loginTestCode.do",method={RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody Object loginTestCode(Map<String, Object> map,HttpSession sessions,
			@RequestParam("code")String submitCode,HttpServletRequest request){
		String code = (String) sessions.getAttribute("validateCode");
        JSONObject jo = new JSONObject();
        if (StringUtils.isEmpty(submitCode) || !StringUtils.equals(code,submitCode.toLowerCase())) {
            jo.put("message", "wrong");
        }else{
        	jo.put("message", "correct");
        }
		return jo.toString();
	}
	 /**
     * 生成验证码
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/validateCode.do")
    public void validateCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Cache-Control", "no-cache");
        String verifyCode = ValidateCode.generateTextCode(ValidateCode.TYPE_NUM_ONLY, 4, null);
        request.getSession().setAttribute("validateCode", verifyCode);
        response.setContentType("image/jpeg");
        BufferedImage bim = ValidateCode.generateImageCode(verifyCode, 90, 30, 3, true, Color.WHITE, Color.BLACK, null);
        ImageIO.write(bim, "JPEG", response.getOutputStream());
    }
    @RequestMapping(value = "/recodescore.do")
    public void recodescore(HttpServletRequest request,HttpSession sessions, HttpServletResponse response) {
    	String score = request.getParameter("score");
    	System.out.println(score);
    	int scoreInt = Integer.valueOf(score);
    	int maxScore = service.getLargestScore();
    	if(scoreInt > maxScore){
    		int userId = (Integer)sessions.getAttribute("userid");
    		service.addScoreRecode(scoreInt,userId);
    	}
    }
    @RequestMapping(value = "/getLargestScore.do")
    public @ResponseBody Object getLargestScore(HttpServletRequest request,HttpSession sessions, HttpServletResponse response) {
    	int score = service.getLargestScore();
    	JSONObject jo = new JSONObject();
    	jo.put("score", score);
    	return jo.toString();
    }
    @RequestMapping(value = "/show.scoreList.do",method={RequestMethod.POST,RequestMethod.GET},
			produces={"application/text;charset=UTF-8"})
    public @ResponseBody Object getscoreList(HttpServletRequest request,HttpSession sessions,Map<String, Object> map,
    		@RequestParam("page")int page,@RequestParam("rows")int rows) {
    	JSONArray jsa = new JSONArray();
    	List<GameScore> partsScore = service.getScoreListByPR(page,rows);
    	for(GameScore a:partsScore){
    		JSONObject jo = new JSONObject();
    		jo.put("id",a.getId());
    		jo.put("userName", a.getUserName());
    		jo.put("score", a.getScore());
    		jo.put("time",String.valueOf(a.getTime()));
    		jsa.add(jo);
    	}
    	List<GameScore> score = service.getScoreList();
    	map.put("total", score.size());
    	map.put("rows", jsa);
    	return JSONObject.fromObject(map).toString();
    }
}
