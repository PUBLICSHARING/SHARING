package sharing.dao.like.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import base.sqlSession.SqlSessionUtil;
import sharing.dao.like.LikeMapper;
import sharing.entity.like.Like;

public class LikeController implements LikeMapper {

	@Override
	public Long addLike(Like like) throws Exception {
		SqlSessionUtil.getSqlSession().insert("sharing.entity.like.LikeMapper.addLike", like);
		return like.getId();
	}

	@Override
	public Like updateLike(Like like) throws Exception {
		SqlSessionUtil.getSqlSession().update("sharing.entity.like.LikeMapper.updateLike", like);
		return like;
	}
	
	@Override
	public Like findLikeById(Long likeId) throws Exception {
		return SqlSessionUtil.getSqlSession().selectOne("sharing.entity.like.LikeMapper.findLikeById", likeId);
	}
	
	@Override
	public Long deleteLikeById(Long likeId) throws Exception {
		return (long) SqlSessionUtil.getSqlSession().delete("sharing.entity.like.LikeMapper.findLikeById", likeId);
		
	}
	
	@Override
	public String findLikeByUserIdAndDynamicId(Long userId, Long dynamicId) throws Exception {
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("userId", userId);
		params.put("dynamicId", dynamicId);
		List<Like> likes = SqlSessionUtil.getSqlSession().selectList("sharing.entity.like.LikeMapper.findLikeByUserIdAndDynamicId", params);
		if(likes.size() > 0) {
			return "false";
		} else {
			return "true";
		}
	}

	@Override
	public Long deleteLikeByDynamicIdAndUserId(Long dynamicId, Long userId) throws Exception {
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("userId", userId);
		params.put("dynamicId", dynamicId);
		SqlSessionUtil.getSqlSession().delete("sharing.entity.like.LikeMapper.deleteLikeByDynamicIdAndUserId", params);
		return 1L;
	}
}
