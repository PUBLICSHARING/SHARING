package sharing.dao.disLike.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import base.sqlSession.SqlSessionUtil;
import sharing.dao.disLike.DisLikeMapper;
import sharing.entity.disLike.DisLike;
import sharing.entity.like.Like;

public class DisLikeController implements DisLikeMapper {

	@Override
	public Long addDisLike(DisLike disLike) throws Exception {
		SqlSessionUtil.getSqlSession().insert("sharing.entity.disLike.DisLikeMapper.addDisLike", disLike);
		return disLike.getId();
	}

	@Override
	public DisLike updateDisLike(DisLike disLike) throws Exception {
		SqlSessionUtil.getSqlSession().update("sharing.entity.disLike.DisLikeMapper.updateDisLike", disLike);
		return disLike;
	}

	@Override
	public DisLike findDisLikeById(Long disLikeId) throws Exception {
		return SqlSessionUtil.getSqlSession().selectOne("sharing.entity.disLike.DisLikeMapper.findDisLikeById", disLikeId);
	}

	@Override
	public Long deleteDisLikeById(Long disLikeId) throws Exception {
		return (long) SqlSessionUtil.getSqlSession().delete("sharing.entity.disLike.DisLikeMapper.deleteDisLikeById", disLikeId);
	}

	@Override
	public String findDisLikeByUserIdAndDynamicId(Long userId, Long dynamicId) throws Exception {
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("userId", userId);
		params.put("dynamicId", dynamicId);
		List<Like> disLikes = SqlSessionUtil.getSqlSession().selectList("sharing.entity.disLike.DisLikeMapper.findDisLikeByUserIdAndDynamicId", params);
		if(disLikes.size() > 0) {
			return "false";
		} else {
			return "true";
		}
	}

	@Override
	public Long deleteDisLikeByDynamicIdAndUserId(Long dynamicId, Long userId) throws Exception {
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("userId", userId);
		params.put("dynamicId", dynamicId);
		SqlSessionUtil.getSqlSession().delete("sharing.entity.disLike.DisLikeMapper.deleteDisLikeByDynamicIdAndUserId", params);
		return 1L;
	}

}
