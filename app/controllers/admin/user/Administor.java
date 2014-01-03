package controllers.admin.user;

import java.util.Map;

import models.users.Administrator;

import play.data.DynamicForm;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.admin.user.*;

public class Administor extends Controller {
	
	public static Result add(){
		return ok(administrator.render(null,true));
	}
	
	public static Result edit(String username){
		Administrator au = Administrator.getAdminUserByUsername(username);
		return ok(administrator.render(au,false));
	}
	
	public static Result create(){
		Map<String,String> map = DynamicForm.form().bindFromRequest().data();
		Administrator admin = new Administrator();
		admin.username = map.get("username");
		admin.password = map.get("password");
		admin.email = map.get("email");
		admin.mobile = map.get("mobile");
		admin.real_name = map.get("real_name");
		Administrator.createUser(admin);
		return redirect("/admin/administor");
	}
	
	public static Result update(){
		Map<String,String> map = DynamicForm.form().bindFromRequest().data();
		Administrator admin = new Administrator();
		admin.username = map.get("username");
		admin.email = map.get("email");
		admin.mobile = map.get("mobile");
		admin.real_name = map.get("real_name");
		Administrator.modifyAdminUser(admin);
		return redirect("/admin/administor");
	}
	
	public static Result delete(String username){
		Administrator.disableAdminUser(username);
		return redirect("/admin/administor");
	}
	
	public static Result active(String username){
		Administrator.activeAdminUser(username);
		return redirect("/admin/administor");
	}
	
	public static Result password(String username){
		return ok(password.render(username,true));
	}
	
	public static Result modifypwd(){
		Map<String,String> map = DynamicForm.form().bindFromRequest().data();
		String username	 = map.get("username");
		String old_password = map.get("old_password");
		String new_password = map.get("password");
		Administrator.modifyAdminUserPassword(username, old_password, new_password);
		return redirect("/admin/administor");
	}

}
