package models.users;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import play.Logger;
import play.db.ebean.Model;
import play.i18n.Messages;
import utils.StringUtils;

import com.avaje.ebean.Ebean;

/**
 * 系统后台管理员管理
 * @author zhangpeng
 *
 */
@Entity
@Table(name="adminuser")
public class AdminUser extends Model{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 管理员ID
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public long id;
	
	@Column
	public String admcode = StringUtils.getMengCode();
	
	/**
	 * 管理员用户名
	 */
	@Column
	public String username = "admin";
	
	/**
	 * 管理员密码
	 */
	@Column
	public String password = "admin";
	
	/**
	 * 管理员电子邮箱
	 */
	@Column
	public String email;
	
	/**
	 * 管理员手机号
	 */
	@Column
	public String mobile;
	
	/**
	 * 管理员姓名
	 */
	@Column
	public String real_name;
	
	/**
	 * 管理员权限 all:超级管理员
	 */
	@Column
	public String privilege = "all";
	
	/**
	 * 管理员状态 true可用
	 */
	@Column
	public boolean status = true;
	
	@Column
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	public Timestamp login_time = new Timestamp(System.currentTimeMillis());
	
	@Column
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	public Timestamp logout_time = new Timestamp(System.currentTimeMillis());
	
	@Column
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	public Timestamp create_date = new Timestamp(System.currentTimeMillis());
	
	
	public static Model.Finder<Long, AdminUser> find = new Model.Finder<Long, AdminUser>(Long.class, AdminUser.class);
	
	/**
	 * 创建管理员
	 * @param au
	 */
	public static void createUser(AdminUser au){
		au.password = StringUtils.md5(au.password);
		if (getAdminUserByUsername(au.username) != null) {
			Logger.info(Messages.get("admin.user.reg.error", ""));
			return ;
		}
		au.save();

	}
	
	/**
	 * 管理员登陆
	 * @param username
	 * @param password
	 * @return
	 */
	public static boolean login(String username, String password){
		String pwd = StringUtils.md5(password);
		AdminUser au = getAdminUserByUsername(username);
		if(au.equals(pwd) && au.status){
			return true;
		}
		return false;
	}
	
	/**
	 * 修改管理员信息
	 * @param au
	 */
	public static void modifyAdminUser(AdminUser au){
		AdminUser admin = getAdminUserByUsername(au.username);
		au.id = admin.id;
		au.password = admin.password;
		au.update();
	}
	
	/**
	 * 修改用户密码
	 * @param username
	 * @param password
	 */
	public static boolean modifyAdminUserPassword(String username, String oldPassword, String password){
		password = StringUtils.md5(password);
		AdminUser au = getAdminUserByUsername(username);
		if(au.password.equals(StringUtils.md5(oldPassword))){
			au.password = password;
			au.update();
		return true;
		}
		Logger.info("old_password is not rigth");
		return false;
	}
	
	/**
	 * 根据用户名查询管理员
	 * @param username
	 * @return
	 */
	public static AdminUser getAdminUserByUsername(String username){
		return find.where().eq("username", username).findUnique();
	}
	
	/**
	 * 根据Code查询管理员
	 * @param code
	 * @return
	 */
	public static AdminUser getAdminUserByCode(String code){
		return find.where().eq("ucode", code).findUnique();
	}
	
	/**
	 * 查询管理员(分页方式)
	 * @param page
	 * @param size
	 * @return
	 */
	public static List<AdminUser> getAdminUserByPage(int page, int size){
		return find.findPagingList(size).getPage(page).getList();
	}
	
	/**
	 * 查询管理员总数
	 * @return
	 */
	public static int getAdminUserCount(){
		return find.findRowCount();
	}
	
	/**
	 * 标识管理员不可用
	 * @param username
	 */
	public static void disableAdminUser(String username){
		AdminUser user = find.where().eq("username", username).findUnique();
		user.status = false;
		user.update();
	}
	
	/**
	 * 删除管理员
	 * @param username
	 */
	public static void deleteAdminUser(String username){
		Ebean.delete(find.where().eq("username", username).findList());
	}
	
	//TODO 找回密码
	//TODO 发送邮件激活
	//TODO 忘记密码
}
