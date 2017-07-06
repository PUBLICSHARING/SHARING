package sharing.dao.like.impl;

import base.sqlSession.SqlSessionUtil;
import sharing.dao.like.LikeMapper;
import sharing.entity.like.Like;

public class LikeController implements LikeMapper {

	@Override
	public Long addLike(Like like) throws Exception {
		SqlSessionUtil.getSqlSession().insert("sharing.entity.like.addLike", like);
		return like.getId();
	}

	@Override
	public Like updateLike(Like like) throws Exception {
		SqlSessionUtil.getSqlSession().update("sharing.entity.like.updateLike", like);
		return like;
	}
	
	@Override
	public Like findLikeById(Long likeId) throws Exception {
		return SqlSessionUtil.getSqlSession().selectOne("sharing.entity.like.findLikeById", likeId);
	}
	
	@Override
	public Long deleteLikeById(Long likeId) throws Exception {
		return (long) SqlSessionUtil.getSqlSession().delete("sharing.entity.like.findLikeById", likeId);
		
	}
}
