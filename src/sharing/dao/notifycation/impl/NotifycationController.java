package sharing.dao.notifycation.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import base.sqlSession.SqlSessionUtil;
import sharing.dao.notifycation.NotifycationMapper;
import sharing.entity.notifycation.Notifycation;

public class NotifycationController implements NotifycationMapper{

	@Override
	public Notifycation addNotifycation(Notifycation notifycation) throws Exception {
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("noticeUserId", notifycation.getNoticeUser().getId());
		if(notifycation.getNoticeFromAccusation()!=null) {
			params.put("noticeFromAccusationId", notifycation.getNoticeFromAccusation().getId());
		}
		else{
			params.put("noticeFromAccusationId", null);
		}
		if(notifycation.getNoticeFromDynamic()!=null){
			params.put("noticeFromDynamicId", notifycation.getNoticeFromDynamic().getId());
		}
		else{
			params.put("noticeFromDynamicId", null);
		}
		if(notifycation.getNoticeFromComment()!=null){
			params.put("noticeFromCommentId", notifycation.getNoticeFromComment().getId());
		}
		else{
			params.put("noticeFromCommentId", null);
		}
		if(notifycation.getNoticeFromLike()!=null){
			params.put("noticeFromLikeId", notifycation.getNoticeFromLike().getId());
		}
		else{
			params.put("noticeFromLikeId", null);
		}
		params.put("noticeContent", notifycation.getNoticeContent());
		params.put("date", new Date());
		SqlSessionUtil.getSqlSession().insert("sharing.entity.notifycation.addNotifycation", params);
		return notifycation;
	}

	@Override
	public Long findCountOfNotifycationNotRead(Long userId) throws Exception {
		return SqlSessionUtil.getSqlSession().selectOne("sharing.entity.notifycation.findCountOfNotifycationNotRead",userId);
	}

	@Override
	public List<Notifycation> findUserNotifycationByLimit(Long userId, Long currentPage, Long pageSize) throws Exception {
		Map<String, Object> params = new HashMap<String,Object>();
		Long startIndex = Long.valueOf((currentPage.longValue() - 1L)* pageSize.longValue());
		params.put("startIndex", startIndex);
		params.put("pageSize", pageSize);
		params.put("userid", userId);
		return SqlSessionUtil.getSqlSession().selectList("sharing.entity.notifycation.findUserNotifycationByLimit",params);
	}

	@Override
	public Long findTotalOfUserNotifycation(Long userId) throws Exception {
		return SqlSessionUtil.getSqlSession().selectOne("sharing.entity.notifycation.findTotalOfUserNotifycation",userId);
	}

	@Override
	public int markReaded(Long notifycationId) throws Exception {
		return SqlSessionUtil.getSqlSession().update("sharing.entity.notifycation.markReaded", notifycationId);
	}

	@Override
	public Long findTotalOfAllNotifycation() throws Exception {
		return SqlSessionUtil.getSqlSession().selectOne("sharing.entity.notifycation.findTotalOfAllNotifycation");
	}

	@Override
	public List<Notifycation> findAllNotifycationByLimit(Long currentPage, Long pageSize) throws Exception {
		Map<String, Object> params = new HashMap<String,Object>();
		Long startIndex = Long.valueOf((currentPage.longValue() - 1L)* pageSize.longValue());
		params.put("startIndex", startIndex);
		params.put("pageSize", pageSize);
		return SqlSessionUtil.getSqlSession().selectList("sharing.entity.notifycation.findAllNotifycationByLimit",params);
	}

}
