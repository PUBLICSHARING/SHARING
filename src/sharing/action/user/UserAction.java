package sharing.action.user;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sharing.entity.user.User;
import sharing.service.user.UserService;
import sharing.service.user.impl.UserServiceBean;

public class UserAction {

	private UserService userService = new UserServiceBean();
	
	public User findUserById(Long userId) throws Exception{
		try{
			return userService.findUserById(userId);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public boolean checkUserName(String name) throws Exception{
		try{
			return userService.checkUserName(name);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public Long addUser(User user) throws Exception {
		try{
			return userService.addUser(user);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public Long judgeLoginUser(User user) throws Exception {
		try{
			return userService.judgeLoginUser(user);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public Map<String, Object> findUserInfoTitleNeedById(Long userId) throws Exception {
		try{
			return userService.findUserInfoTitleNeedById(userId);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public User updateUser(User user) throws Exception {
		try {
			return userService.updateUser(user);
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public Long updatePassWord(Long userId, String originalPassWord, String newPassWord) throws Exception {
		try {
			return userService.updatePassWord(userId, originalPassWord, newPassWord);
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	public List<User> findUsersByLimit(Long currentPage,Long pageSize) throws Exception {
		try{
			return userService.findUsersByLimit(currentPage,pageSize);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public Long findAllUsersTotal() throws Exception {
		try{
			return userService.findAllUsersTotal();
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public Map<String, Object> findUsersAndTotalByLimit(Long currentPage,Long pageSize) throws Exception {
		try{
			Map<String, Object> result = new HashMap<String,Object>();
			result.put("total", this.findAllUsersTotal());
			result.put("users", this.findUsersByLimit(currentPage, pageSize));
			return result;
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
