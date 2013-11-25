package models.users;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import play.db.ebean.Model;
import utils.MengException;

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
	
	public static Model.Finder<Long, UserProfile> find = new Model.Finder<Long, UserProfile>(Long.class, UserProfile.class);
	
	/**
	 * 根据用户名获取用户信息
	 * @param username
	 * @return
	 */
	public static UserProfile getUserProfileByUsername(String username){
		return find.where().eq("username", username).findUnique();
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
	public static void createUserProfile(UserProfile up) throws MengException{
		if(verifyRepeatEmailMobile(up)){
			up.save();
		}
	}
	
	/**
	 * 修改用户信息
	 * @param up
	 * @throws MengException
	 */
	public static void modifyUserProfile(UserProfile up) throws MengException{
		if(verifyRepeatEmailMobile(up)){
			up.update();
		}
	}
	
	/**
	 * 验证用户信息的Email和Mobile是否重复(辅助方法)
	 * @param up
	 * @return
	 * @throws MengException
	 */
	private static boolean verifyRepeatEmailMobile(UserProfile up) throws MengException{
		List<UserProfile> up_email = find.where().eq("email", up.email).findList();
		List<UserProfile> up_mobile = find.where().eq("mobile", up.mobile).findList();
		if(up_email != null){
			throw new MengException("100105");
		}
		if(up_mobile != null){
			throw new MengException("100106");
		}
		return true;
	}
	
	

}
