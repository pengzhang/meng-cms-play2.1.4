package models.users;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import play.db.ebean.Model;
import utils.StringUtils;
import utils.MengException;

import com.avaje.ebean.Ebean;

/**
 * 会员管理<br>
 * 1.注册功能 2.添加功能 3.登陆功能
 * 
 * @author zhangpeng
 * 
 */
@Entity
@Table(name = "user")
public class User extends Model {

	/**
	 * User serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 用户ID
	 */
	@Id
	public long id;

	/**
	 * 用户名
	 */
	@Column
	public String username;

	/**
	 * 密码
	 */
	@Column
	public String password;

	/**
	 * 是否高级会员
	 */
	@Column
	public boolean is_admin;

	/**
	 * 访问令牌
	 */
	@Column
	public String access_token;

	/**
	 * 有效时间
	 */
	@Column
	public long expires_in;
	
	/**
	 * 登陆时间
	 */
	@Column
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	public Timestamp login_time = new Timestamp(System.currentTimeMillis());
	/**
	 * 退出时间
	 */
	@Column
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	public Timestamp logout_time = new Timestamp(System.currentTimeMillis());
	
	@Column
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	public Timestamp create_date = new Timestamp(System.currentTimeMillis());

	/**
	 * 激活状态和暂停用户(新注册用户默认为不可用)
	 */
	@Column
	public boolean status = false;
	
	

	public static Model.Finder<Long, User> find = new Model.Finder<Long, User>(Long.class, User.class);

	/**
	 * 用户登陆 
	 * 1.将密码加密成MD5 2.根据用户名查出此用户 3.验证用户是否激活并验证密码是否正确
	 * 4.生成access_token和当前的时间戳 5.验证成功后,将access_token和有效期,登陆时间更新到数据库
	 * @param username
	 * @param password
	 * @return access_token
	 * @throws MengException 
	 */
	public static String login(String username, String password) throws MengException {
		password = StringUtils.md5(password);
		User user = getUserByName(username);

		String access_token = StringUtils.access_token();
		long currentTime = StringUtils.getTimeStamp();

		try {
			if (verify_pwd(user, password)) {
				user.access_token = access_token;
				user.expires_in = currentTime + 1800000;
//				user.login_at = currentTime;
				user.update();
			}
		} catch (MengException e) {
			throw new MengException(e.errorno);
		}

		return access_token;
	}
	
	/**
	 * 用户登出
	 * 1.根据access_token查询该用户信息
	 * 2.将access_token清空,有效期至0
	 * @param access_token
	 * @throws MengException
	 */
	public static void logout(String access_token) throws MengException{
		User user = find.where().eq("access_token", access_token).findUnique();
		if(user == null){
			throw new MengException("100100");
		}
		user.access_token = "";
		user.expires_in = 0;
//		user.logout_at = StringUtils.getTimeStamp(); 
		user.update();
	}
	
	/**
	 * 根据access_token查询UserProfile
	 * 验证成功添加access_token有效期
	 * @param access_token
	 * @return
	 * @throws MengException
	 */
	public static UserProfile getUserProfileByAccess_Token(String access_token) throws MengException{
		User user = find.where().eq("access_token", access_token).findUnique();
		if(user == null){
			throw new MengException("100100");
		}
		if(user.expires_in<StringUtils.getTimeStamp()){
			throw new MengException("100101");
		}
		//添加access_token时间
		user.expires_in = 1800000-(user.expires_in-StringUtils.getTimeStamp()) + user.expires_in;
		user.update();
		return UserProfile.getUserProfileByUsername(user.username);
	}

	/**
	 * 用户登录验证密码和是否可用(辅助方法)
	 * 
	 * @param user
	 * @param password
	 * @return
	 */
	private static boolean verify_pwd(User user, String password) throws MengException{
		if (user == null) {
			throw new MengException("100103");
		}
		if (user.status == false) {
			throw new MengException("100103");
		}

		if (!user.password.equals(password)) {
			throw new MengException("100103");
		}
		return true;
	}

	/**
	 * 根据用户名查询用户信息
	 * 
	 * @param username
	 * @return
	 */
	private static User getUserByName(String username) {
		return find.where().eq("username", username).findUnique();
	}

	/**
	 * 获取用户列表(分页方式)
	 * 
	 * @param page
	 * @param size
	 * @return
	 */
	public static List<User> getUserByPage(int page, int size) {
		return find.findPagingList(size).getPage(page).getList();
	}

	/**
	 * 获取用户的总数
	 * 
	 * @return
	 */
	public static int getUserCount() {
		return find.findRowCount();
	}


	/**
	 * 修改用户密码
	 * 
	 * @param access_token
	 * @param password
	 * @throws MengException 
	 */
	public static void modifyUserPassword(String access_token, String password) throws MengException {
		User user = find.where().eq("access_token", access_token).findUnique();
		if(user == null){
			throw new MengException("100100");
		}
		if(user.expires_in<StringUtils.getTimeStamp()){
			throw new MengException("100101");
		}
		//添加access_token时间
		user.expires_in = 1800000-(user.expires_in-StringUtils.getTimeStamp()) + user.expires_in;
		user.password = StringUtils.md5(password);
		user.update();
	}
	
	/**
	 * 创建一个新的用户
	 * @param user
	 * @throws MengException
	 */
	public static void createUser(User user) throws MengException{
		User u = find.where().eq("username", user.username).findUnique();
		if(u != null){
			throw new MengException("100107");
		}
		user.password = StringUtils.md5(user.password);
		user.save();
	}

	/**
	 * 删除用户信息(从数据库中删除)
	 * 
	 * @param username
	 */
	public static void destroyUser(String username) {
		Ebean.delete(find.where().eq("username", username).findList());
	}

	/**
	 * 标识用户不可用
	 * 
	 * @param username
	 */
	public static void disableUser(String username) {
		User user = getUserByName(username);
		user.status = false;
		user.save();
	}

}
