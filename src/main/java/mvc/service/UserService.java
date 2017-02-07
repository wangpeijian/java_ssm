package mvc.service;

import mvc.dao.pojo.User;

import java.util.List;

/**
 * Created by dx on 17-1-19.
 */
public interface UserService {
	
	List<User> selectUser(User user);
	
	void insertOneUser(User user);
	
	void clearUser(int id);
	
	void selectUserUseCache2(User user);
}
