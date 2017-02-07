package mvc.service.impl;

import mvc.dao.maps.UserMapper;
import mvc.dao.pojo.User;
import mvc.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * Created by dx on 17-1-19.
 */
@Service
public class UserServiceImpl implements UserService {
	
	private static Logger log = Logger.getLogger(UserServiceImpl.class);
	
	@Resource
	private UserMapper userMapper;
	
	/*使用了spring的缓存*/
	@Cacheable(value = "queryCache", key = "#user.ID")
	public List<User> selectUser(User user) {
		log.info("调用了selectUser" + user.getID());
		return userMapper.selectUser(user);
	}
	
	@Transactional
	public void insertOneUser(User user) {
		log.error("插入准备报错");
		userMapper.insertOneUser(user);
		int a = 1 / 0;
	}
	
	/*使用了spring的缓存清理*/
	@CacheEvict(value = "queryCache", key = "#id")
	public void clearUser(int id) {
		
	}
	
	/*使用二级缓存查询*/
	public void selectUserUseCache2(User user) {
		log.info("使用二级缓存：***************************start******************************");
		userMapper.selectUser(user);
		log.info("使用二级缓存：***************************end******************************");
	}
}
