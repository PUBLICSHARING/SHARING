package sharing.dao.user;
import java.util.Map;

import sharing.entity.user.User;

public interface UserMapper {
	
	public User findUserById(Long userId) throws Exception;

	public boolean checkUserName(String name) throws Exception;

	public Long addUser(User user) throws Exception;

	public Long judgeLoginUser(User user) throws Exception;

	public Map<String, Object> findUserInfoTitleNeedById(Long userId) throws Exception;
	
	public User updateUser(User user) throws Exception;
}
