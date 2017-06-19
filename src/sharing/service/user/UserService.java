package sharing.service.user;

import sharing.entity.user.User;

public interface UserService {
	public User findUserById(Long userId) throws Exception;
}
