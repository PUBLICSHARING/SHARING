package sharing.service.admin;

import sharing.entity.admin.Admin;

public interface AdminService {

	Long judgeAdmin(Admin admin) throws Exception;

	Admin findAdminById(Long adminId) throws Exception;

}
