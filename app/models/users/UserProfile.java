package models.users;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import play.Logger;
import play.db.ebean.Model;
import utils.MengException;
import utils.StringUtils;

@Entity
@Table(name="user_profile")
public class UserProfile extends Model{

	private static final long serialVersionUID = 1L;
	
	@Id
	public long id;
	
	/**
	 * 用户名
	 */
	@Column
	public String username;
	
	@Column
	public String code = StringUtils.getMengCode();
	
	/**
	 * 真实姓名
	 */
	@Column
	public String name;
	
	/**
	 * 昵称
	 */
	@Column
	public String nickname;
	
	/**
	 * 身份证号
	 */
	@Column
	public String cardid;
	
	/**
	 * 性别
	 */
	@Column
	public boolean gender;
	
	/**
	 * 电子邮箱
	 */
	@Column
	public String email;
	
	/**
	 * 移动电话
	 */
	@Column
	public String mobile;
	
	/**
	 * 电话号码
	 */
	@Column
	public String tel;
	
	/**
	 * 公司名称
	 */
	@Column
	public String company;
	
	@Column
	public boolean status = false; 
	
	public static Model.Finder<Long, UserProfile> find = new Model.Finder<Long, UserProfile>(Long.class, UserProfile.class);
	
	/**
	 * 根据用户名获取用户信息
	 * @param username
	 * @return
	 */
	public static UserProfile getUserProfileByUsername(String username){
		return find.where().eq("username", username).findUnique();
	}
	
	public static UserProfile getUserProfileByCode(String code){
		return find.where().eq("code", code).findUnique();
	}
	
	/**
	 * 获取用户信息列表(分页方式)
	 * @param page
	 * @param size
	 * @return
	 */
	public static List<UserProfile> getUserProfilesPage(int page, int size){
		return find.findPagingList(size).getPage(page).getList();
	}
	
	/**
	 * 根据Email获取用户名
	 * @param email
	 * @return
	 */
	public static String getUserNameByEmail(String email){
		return find.where().eq("email", email).findUnique().username;
	}
	
	/**
	 * 保存用户信息
	 * @param up
	 * @throws MengException 
	 */
	public static void createUserProfile(UserProfile up){
		if(verifyRepeatEmailMobile(up)){
			up.save();
		}
	}
	
	/**
	 * 修改用户信息
	 * @param up
	 * @throws MengException
	 */
	public static void modifyUserProfile(UserProfile up){
		if(verifyRepeatEmailMobile(up)){
			UserProfile profile = getUserProfileByUsername(up.username);
			up.id = profile.id;
			up.update();
				
		}else{
			Logger.info("user update failure");
		}
	}
	
	/**
	 * 验证用户信息的Email和Mobile是否重复(辅助方法)
	 * @param up
	 * @return
	 * @throws MengException
	 */
	private static boolean verifyRepeatEmailMobile(UserProfile up){
		UserProfile upemail = find.where().eq("email", up.email).findUnique();
		UserProfile upmobile = find.where().eq("mobile", up.mobile).findUnique();
		if(upemail!=null && !upemail.username.equals(up.username) ){
//			throw new MengException("100105");
			Logger.info("email exist");
			return false;
		}
		if(upmobile!=null && !upmobile.username.equals(up.username) ){
//			throw new MengException("100106");
			Logger.info("mobile exist");
			return false;
		}
		return true;
	}
	
	public static void modifyStatus(String username,boolean status){
		UserProfile up = getUserProfileByUsername(username);
		up.status = status;
		up.update();
	}
	
	
	

}
