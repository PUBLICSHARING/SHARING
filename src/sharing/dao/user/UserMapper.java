package sharing.dao.user;

import sharing.entity.user.User;

public interface UserMapper {
	
	public User findUserById(Long userId) throws Exception;

	public boolean checkUserName(String name) throws Exception;

	public Long addUser(User user) throws Exception;

	public boolean judgeLoginUser(User user) throws Exception;
}
