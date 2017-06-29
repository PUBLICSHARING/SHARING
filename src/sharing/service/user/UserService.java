package sharing.service.user;

import sharing.entity.user.User;

public interface UserService {
	public User findUserById(Long userId) throws Exception;

	public boolean checkUserName(String name) throws Exception;

	public Long addUser(User user) throws Exception;

	public Long judgeLoginUser(User user) throws Exception;
}
