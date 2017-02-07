package mvc.dao.maps;

import mvc.dao.pojo.User;

import java.util.List;

/**
 * Created by dx on 17-1-19.
 */
public interface UserMapper {
	
	User selectUserById(int id);
	
	List<User> selectUser(User user);
	
	void insertOneUser(User user);
}
