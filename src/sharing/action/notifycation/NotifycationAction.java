package sharing.action.notifycation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sharing.entity.notifycation.Notifycation;
import sharing.service.notifycation.NotifycationService;
import sharing.service.notifycation.impl.NotifycationServiceBean;

public class NotifycationAction {

	private NotifycationService notifycationService = new NotifycationServiceBean();
	
	public Notifycation addNotifycation(Notifycation notifycation) throws Exception{
		try{
			return this.notifycationService.addNotifycation(notifycation);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/*未查看消息个数*/
	public Long findCountOfNotifycationNotRead(Long userId) throws Exception {
		try{
			return this.notifycationService.findCountOfNotifycationNotRead(userId);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public List<Notifycation> findUserNotifycationByLimit(Long userId,Long currentPage,Long pageSize) throws Exception {
		try{
			return this.notifycationService.findUserNotifycationByLimit(userId,currentPage,pageSize);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public Long findTotalOfUserNotifycation(Long userId) throws Exception {
		try{
			return this.notifycationService.findTotalOfUserNotifycation(userId);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public Map<String, Object> findTotalAndUserNotifycationByLimit(Long userId,Long currentPage,Long pageSize) throws Exception{
		try{
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("total", this.findTotalOfUserNotifycation(userId));
			result.put("notifycations", this.findUserNotifycationByLimit(userId, currentPage, pageSize));
			return result;
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public int markReaded (Long notifycationId) throws Exception {
		try{
			return this.notifycationService.markReaded(notifycationId);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public Long findTotalOfAllNotifycation() throws Exception{
		try{
			return this.notifycationService.findTotalOfAllNotifycation();
		}
		catch(Exception e){
			e.printStackTrace();
			throw e;
		}
	}
	
	public List<Notifycation> findAllNotifycationByLimit(Long currentPage,Long pageSize) throws Exception {
		try{
			return this.notifycationService.findAllNotifycationByLimit(currentPage,pageSize);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public Map<String, Object> findTotalAndAllNotifycationByLimit(Long currentPage,Long pageSize) throws Exception{
		try{
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("total", this.findTotalOfAllNotifycation());
			result.put("notifycations", this.findAllNotifycationByLimit( currentPage, pageSize));
			return result;
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
