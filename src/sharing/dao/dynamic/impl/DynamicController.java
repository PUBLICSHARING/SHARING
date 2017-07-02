package sharing.dao.dynamic.impl;

import base.sqlSession.SqlSessionUtil;
import sharing.dao.dynamic.DynamicMapper;
import sharing.entity.dynamic.Dynamic;

public class DynamicController implements DynamicMapper{

	@Override
	public Long addDynamic(Dynamic dynamic) throws Exception {
		SqlSessionUtil.getSqlSession().insert("sharing.entity.dynamic.addDynamic", dynamic);
		return dynamic.getId();
	}

}
