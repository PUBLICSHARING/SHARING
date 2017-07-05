package sharing.dao.dynamic.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import base.sqlSession.SqlSessionUtil;
import sharing.dao.dynamic.DynamicMapper;
import sharing.entity.dynamic.Dynamic;
import sharing.entity.user.User;

public class DynamicController implements DynamicMapper{

	@Override
	public Long addDynamic(Dynamic dynamic) throws Exception {
		SqlSessionUtil.getSqlSession().insert("sharing.entity.dynamic.addDynamic", dynamic);
		return dynamic.getId();
	}

	@Override
	public Long findAllDynamicsTotal() throws Exception {
		return SqlSessionUtil.getSqlSession().selectOne("sharing.entity.dynamic.findAllDynamicsTotal");
	}

	@Override
	public List<Dynamic> findDynamicsByLimit(Long currentPage, Long pageSize) throws Exception {
		Map<String, Object> params = new HashMap<String,Object>();
		Long startIndex = Long.valueOf((currentPage.longValue() - 1L)* pageSize.longValue());
		params.put("startIndex", startIndex);
		params.put("pageSize", pageSize);
		return SqlSessionUtil.getSqlSession().selectList("sharing.entity.dynamic.findDynamicsByLimit", params);
	}

	@Override
	public Long findCountOfDynamicsByUserId(Long userId) throws Exception {
		return SqlSessionUtil.getSqlSession().selectOne("sharing.entity.dynamic.findCountOfDynamicsByUserId",userId);
	}

	@Override
	public Long findMaxDynamicId() throws Exception {
		return SqlSessionUtil.getSqlSession().selectOne("sharing.entity.dynamic.findMaxDynamicId");
	}

	@Override
	public Map<String, Object> findAllDynamicsByUserId(Long id) throws Exception {
		return SqlSessionUtil.getSqlSession().selectMap("sharing.entity.dynamic.findAllDynamicsByUserId",id,"id");
	}
	
	@Override
	public Map<String, Object> findNewestDynamics() throws Exception {
		return SqlSessionUtil.getSqlSession().selectMap("sharing.entity.dynamic.findNewestDynamics","id");
	}

}
