package mvc.controller;

import mvc.dao.pojo.User;
import mvc.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import spring.aop.Man;
import spring.ioc.Soldier;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
	
	@RequestMapping(value = "/test", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String test() {
		return userService.selectUser(new User(0, "", "", "")).toString();
	}
	
	@RequestMapping(value = "/session", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String session() {
		
		log.info("厉害了");
		
		userService.insertOneUser(new User(99, "中文007", "0", "0"));
		
		return "session";
	}
	
	@RequestMapping(value = "/selectCache", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String selectCache(HttpServletRequest request) {
		
		log.info("测试查询缓存");
		
		int id = 0;
		
		if (request.getParameter("id") != null) {
			id = Integer.parseInt(request.getParameter("id"));
		}
		
		return userService.selectUser(new User(id, "", "", "")).toString();
	}
	
	@RequestMapping(value = "/removeCache", produces = "text/html;charset=UTF-8")
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
	
	@RequestMapping(value = "/cache2", produces = "text/html;charset=UTF-8")
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
	
	@RequestMapping(value = "/view", produces = "text/html;charset=UTF-8")
	public String view() {
		
		log.info("/views/index");
		
		return "/views/index";
	}
}
