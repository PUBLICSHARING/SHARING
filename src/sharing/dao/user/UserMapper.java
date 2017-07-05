package sharing.dao.user;
import java.util.List;
import java.util.Map;

import sharing.entity.user.User;

public interface UserMapper {
	
	public User findUserById(Long userId) throws Exception;

	public boolean checkUserName(String name) throws Exception;

	public Long addUser(User user) throws Exception;

	public Long judgeLoginUser(User user) throws Exception;

	public Map<String, Object> findUserInfoTitleNeedById(Long userId) throws Exception;
	
	public User updateUser(User user) throws Exception;
	
	public Long updatePassWord(Long userId, String originalPassWord, String newPassWord) throws Exception;

	public List<User> findUsersByLimit(Long currentPage, Long pageSize) throws Exception;

	public Long findAllUsersTotal() throws Exception;

	public Map<String, Object> findUserInfoAndCountOfOthers(Long userId) throws Exception;

	public User findUserBaseInfoAndImages(Long userId) throws Exception;

	public int stopUser(Long userId) throws Exception;

	public int startUser(Long userId) throws Exception;
}
