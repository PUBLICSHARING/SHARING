package sharing.dao.notifycation;

import java.util.List;

import sharing.entity.notifycation.Notifycation;

public interface NotifycationMapper {

	public Notifycation addNotifycation(Notifycation notifycation) throws Exception;

	public Long findCountOfNotifycationNotRead(Long userId) throws Exception;

	public List<Notifycation> findUserNotifycationByLimit(Long userId, Long currentPage, Long pageSize) throws Exception;

	public Long findTotalOfUserNotifycation(Long userId) throws Exception;

	public int markReaded(Long notifycationId) throws Exception;

	public Long findTotalOfAllNotifycation() throws Exception;

	public List<Notifycation> findAllNotifycationByLimit(Long currentPage, Long pageSize) throws Exception;

}
