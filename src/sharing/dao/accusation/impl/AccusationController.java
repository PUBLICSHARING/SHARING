package sharing.dao.accusation.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import base.sqlSession.SqlSessionUtil;
import sharing.dao.accusation.AccusationMapper;
import sharing.entity.accusation.Accusation;

public class AccusationController implements AccusationMapper{

	@Override
	public Accusation addAccusation(Accusation accusation) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		if(accusation.getAccusationUser() != null){
			params.put("accusationUserId", accusation.getAccusationUser().getId());
		}
		else{
			params.put("accusationUserId", null);
		}
		if(accusation.getAccusationedUser() != null){
			params.put("accusationedUserId",accusation.getAccusationedUser().getId());
		}
		else{
			params.put("accusationedUserId",null);
		}
		if(accusation.getAccusationedDynamic() != null){
			params.put("accusationedDynamicId", accusation.getAccusationedDynamic().getId());
		}
		else{
			params.put("accusationedDynamicId", null);
		}
		if(accusation.getAccusationedComment() != null){
			params.put("accusationedCommentId", accusation.getAccusationedComment().getId());
		}
		else{
			params.put("accusationedCommentId", null);
		}
		params.put("accusationedRemark", accusation.getAccusationedRemark());
		params.put("accusationedDate", new Date());
		SqlSessionUtil.getSqlSession().insert("sharing.entity.accusation.addAccusation", params);
		accusation.setId((Long) params.get("id"));
		return accusation;
	}

	@Override
	public int deleteAccusation(Long accusationId) throws Exception {
		return SqlSessionUtil.getSqlSession().delete("sharing.entity.accusation.deleteAccusation", accusationId);
	}

	@Override
	public List<Accusation> findAccusationByLimit(Long currentPage, Long pageSize) throws Exception {
		Map<String, Object> params = new HashMap<String,Object>();
		Long startIndex = Long.valueOf((currentPage.longValue() - 1L)* pageSize.longValue());
		params.put("startIndex", startIndex);
		params.put("pageSize", pageSize);
		return SqlSessionUtil.getSqlSession().selectList("sharing.entity.accusation.findAccusationByLimit",params);
	}

	@Override
	public Long findTotalOfAllAccusation() throws Exception {
		return SqlSessionUtil.getSqlSession().selectOne("sharing.entity.accusation.findTotalOfAllAccusation");
	}

	@Override
	public int markReaded(Long accusationId) throws Exception {
		return SqlSessionUtil.getSqlSession().update("sharing.entity.accusation.markReaded", accusationId);
	}

	@Override
	public Long findCountOfNotReaded() throws Exception {
		return SqlSessionUtil.getSqlSession().selectOne("sharing.entity.accusation.findCountOfNotReaded");
	}

}
