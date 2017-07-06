package sharing.service.notifycation.impl;

import java.util.List;

import sharing.dao.notifycation.NotifycationMapper;
import sharing.dao.notifycation.impl.NotifycationController;
import sharing.entity.notifycation.Notifycation;
import sharing.service.notifycation.NotifycationService;

public class NotifycationServiceBean implements NotifycationService{

	private NotifycationMapper notifycationMapper = new NotifycationController();
	
	@Override
	public Notifycation addNotifycation(Notifycation notifycation) throws Exception {
		try{
			return this.notifycationMapper.addNotifycation(notifycation);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new Exception("addNotifycation",e);
		}
	}

	@Override
	public Long findCountOfNotifycationNotRead(Long userId) throws Exception {
		try{
			return this.notifycationMapper.findCountOfNotifycationNotRead(userId);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new Exception("findCountOfNotifycationNotRead",e);
		}
	}

	@Override
	public List<Notifycation> findUserNotifycationByLimit(Long userId, Long currentPage, Long pageSize) throws Exception {
		try{
			return this.notifycationMapper.findUserNotifycationByLimit(userId,currentPage,pageSize);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new Exception("findUserNotifycationByLimit",e);
		}
	}

	@Override
	public Long findTotalOfUserNotifycation(Long userId) throws Exception {
		try{
			return this.notifycationMapper.findTotalOfUserNotifycation(userId);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new Exception("findTotalOfUserNotifycation",e);
		}
	}

	@Override
	public int markReaded(Long notifycationId) throws Exception {
		try{
			return this.notifycationMapper.markReaded(notifycationId);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new Exception("markReaded",e);
		}
	}

	@Override
	public Long findTotalOfAllNotifycation() throws Exception {
		try{
			return this.notifycationMapper.findTotalOfAllNotifycation();
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new Exception("findTotalOfAllNotifycation",e);
		}
	}

	@Override
	public List<Notifycation> findAllNotifycationByLimit(Long currentPage, Long pageSize) throws Exception {
		try{
			return this.notifycationMapper.findAllNotifycationByLimit(currentPage,pageSize);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new Exception("findAllNotifycationByLimit",e);
		}
	}

}
