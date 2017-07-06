package sharing.service.admin.impl;

import sharing.dao.admin.AdminMapper;
import sharing.dao.admin.impl.AdminController;
import sharing.entity.admin.Admin;
import sharing.service.admin.AdminService;

public class AdminServiceBean implements AdminService{

	private AdminMapper adminMapper = new AdminController();
	
	@Override
	public Long judgeAdmin(Admin admin) throws Exception {
		try{
			return this.adminMapper.judgeAdmmin(admin);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new Exception("judgeAdmin",e);
		}
	}

	@Override
	public Admin findAdminById(Long adminId) throws Exception {
		try{
			return this.adminMapper.findAdminById(adminId);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new Exception("findAdminById",e);
		}
	}

}
