package sharing.dao.admin;

import sharing.entity.admin.Admin;

public interface AdminMapper {

	Long judgeAdmmin(Admin admin) throws Exception;

	Admin findAdminById(Long adminId) throws Exception;

}
