package sharing.dao.user;

import sharing.entity.user.User;

public interface UserMapper {
	
	public User findUserById(Long userId) throws Exception;
}
