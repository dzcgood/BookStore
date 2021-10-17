package dzc.bookStore.admin.service;

import dzc.bookStore.admin.dao.AdminDao;
import dzc.bookStore.admin.domain.Admin;
import dzc.bookStore.user.domain.User;
import dzc.bookStore.user.service.UserException;

public class AdminService {
    private AdminDao adminDao = new AdminDao();

    /**
     * 管理员登录功能
     * */
    public Admin login(Admin form)throws AdminException {
        /**使用admin_name查询，得到user*/
        Admin admin = adminDao.findByAdminName(form.getAdmin_name());
        /**若admin为null，抛出异常*/
        if(admin==null)throw new AdminException("该管理员账户不存在！");
        /**比较密码是否正确，若错误，抛出异常*/
        if(!admin.getAdmin_password().equals(form.getAdmin_password()))throw new AdminException("管理账户密码错误！");
        return admin;
    }
}
