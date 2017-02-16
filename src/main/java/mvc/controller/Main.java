package mvc.controller;

import mvc.dao.pojo.User;
import mvc.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;
import spring.aop.Man;
import spring.ioc.Soldier;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 * Created by dx on 17-1-17.
 */
@Controller
public class Main {
	
	private static Logger log = Logger.getLogger(Main.class);
	
	@Resource
	private UserService userService;
	
	@Resource
	Man man;
	
	@Resource
	Soldier soldier;
	
	@RequestMapping(value = "/aop")
	@ResponseBody
	public String aop() {
		
		man.doSomething();
		
		return "aop";
	}
	
	@RequestMapping(value = "/ioc")
	@ResponseBody
	public String ioc() {
		
		soldier.say();
		
		return "ioc";
	}
	
	@RequestMapping(value = "/test")
	@ResponseBody
	public String test() {
		return userService.selectUser(new User(0, "", "", "")).toString();
	}
	
	@RequestMapping(value = "/session")
	@ResponseBody
	public String session() {
		
		log.info("厉害了");
		
		try{
			userService.insertOneUser(new User(99, "中文007", "0", "0"));
		}catch (Exception e){
			log.info("捕获异常");
		}
		
		log.info("报错结束了");
		
		return "session";
	}
	
	@RequestMapping(value = "/selectCache")
	@ResponseBody
	public String selectCache(HttpServletRequest request) {
		
		log.info("测试查询缓存");
		
		int id = 0;
		
		if (request.getParameter("id") != null) {
			id = Integer.parseInt(request.getParameter("id"));
		}
		
		return userService.selectUser(new User(id, "", "", "")).toString();
	}
	
	@RequestMapping(value = "/removeCache")
	@ResponseBody
	public String removeCache(HttpServletRequest request) {
		
		log.info("测试清除缓存");
		
		int id = 0;
		
		if (request.getParameter("id") != null) {
			id = Integer.parseInt(request.getParameter("id"));
		}
		
		userService.clearUser(id);
		
		return "clear";
	}
	
	@RequestMapping(value = "/cache2")
	@ResponseBody
	public String cache2(HttpServletRequest request) {
		
		log.info("测试2级缓存");
		
		int id = 0;
		
		if (request.getParameter("id") != null) {
			id = Integer.parseInt(request.getParameter("id"));
		}
		
		userService.selectUserUseCache2(new User(id, "", "", ""));
		
		return "cache2";
	}
	
	@RequestMapping(value = "/view")
	public String view() {
		
		log.info("/views/index");
		
		return "/views/index";
	}
	
	@RequestMapping(value = "/postData")
	@ResponseBody
	public String postData(HttpServletRequest request, User user) {
		
		log.info("/postData::" + request.getParameter("id") +"--"+ request.getParameter("name"));
		
		log.info("/postData::" + user.toString());
		
		return "{\"id\": 2, \"name\": \"中文\"}";
	}
	
	@RequestMapping(value = "/postFile")
	@ResponseBody
	public String postFile(HttpServletRequest request,HttpServletResponse response)throws IllegalStateException, IOException {
		
		//创建一个通用的多部分解析器
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		//判断 request 是否有文件上传,即多部分请求
		if(multipartResolver.isMultipart(request)){
			//转换成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
			//取得request中的所有文件名
			Iterator<String> iter = multiRequest.getFileNames();
			while(iter.hasNext()){
				
				//记录上传过程起始时的时间，用来计算上传时间
				//取得上传文件
				MultipartFile file = multiRequest.getFile(iter.next());
				if(file != null){
					
					log.info("/postFile::" + file.getName());
					
					//取得当前上传文件的文件名称
					String myFileName = file.getOriginalFilename();
					//如果名称不为“”,说明该文件存在，否则说明该文件不存在
					if(myFileName.trim() !=""){
						System.out.println(myFileName);
						//重命名上传后的文件名
						String fileName = "demoUpload" + file.getOriginalFilename();
						//定义上传路径
//						String path = "/" + fileName;
//						File localFile = new File(path);
//						file.transferTo(localFile);
					}
				}
			}
			
		}
		
		return "{\"id\": 2, \"name\": \"中文\"}";
	}
}
