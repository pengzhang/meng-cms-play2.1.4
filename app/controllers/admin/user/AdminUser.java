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
		return ok(user.render());
	}
	
	public static Result edit(String code){
		return ok(user.render());
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
	
	public static Result update(){
		return redirect("/admin/user");
	}
	
	public static Result delete(String code){
		return redirect("/admin/user");
	}

}
