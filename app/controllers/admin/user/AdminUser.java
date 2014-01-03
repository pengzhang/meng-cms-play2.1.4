package controllers.admin.user;

import java.util.Map;

import models.users.User;
import models.users.UserProfile;

import play.data.DynamicForm;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.admin.user.*;

public class AdminUser extends Controller {
	
	public static Result add(){
		return ok(user.render(null,true));
	}
	
	public static Result create(){
		User user = new User();
		UserProfile up = new UserProfile();
		Map<String,String> map = DynamicForm.form().bindFromRequest().data();
		user.username = map.get("username");
		up.username = user.username;
		user.password = map.get("password");
		User.createUser(user);
		up.cardid = map.get("cardid");
		up.email = map.get("email");
		up.mobile = map.get("mobile");
		up.name = map.get("name");
		up.nickname = map.get("nickname");
		UserProfile.createUserProfile(up);
		return redirect("/admin/user");
	}
	
	public static Result edit(String username){
		UserProfile up = UserProfile.getUserProfileByUsername(username);
		return ok(user.render(up,false));
	}
	
	public static Result update(){
		UserProfile up = new UserProfile();
		Map<String,String> map = DynamicForm.form().bindFromRequest().data();
		up.username = map.get("username");
		up.cardid = map.get("cardid");
		up.email = map.get("email");
		up.mobile = map.get("mobile");
		up.name = map.get("name");
		up.nickname = map.get("nickname");
		UserProfile.modifyUserProfile(up);
		return redirect("/admin/user");
	}
	
	public static Result password(String username){
		return ok(password.render(username,false));
	}
	
	public static Result modifypwd(){
		Map<String,String> map = DynamicForm.form().bindFromRequest().data();
		String username = map.get("username");
		String oldpwd = map.get("old_password");
		String newpwd = map.get("password");
		User.modifyUserPassword(username, oldpwd, newpwd);
		return redirect("/admin/user");
	}
	
	public static Result delete(String username){
		User.disableUser(username);
		return redirect("/admin/user");
	}
	
	public static Result active(String username){
		User.activeUser(username);
		return redirect("/admin/user");
	}
	
	public static Result forgetPwd(){
		return ok();
	}
	

}
