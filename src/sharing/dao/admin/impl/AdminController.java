package sharing.dao.admin.impl;

import base.sqlSession.SqlSessionUtil;
import sharing.dao.admin.AdminMapper;
import sharing.entity.admin.Admin;

public class AdminController implements AdminMapper{

	@Override
	public Long judgeAdmmin(Admin admin) throws Exception {
		Admin result = SqlSessionUtil.getSqlSession().selectOne("sharing.entity.admin.judgeAdmin",admin);
		if(result == null) {
			return null;
		}
		else{
			return result.getId();
		}
	}

	@Override
	public Admin findAdminById(Long adminId) throws Exception {
		return SqlSessionUtil.getSqlSession().selectOne("sharing.entity.admin.findAdminById",adminId);
	}

}
