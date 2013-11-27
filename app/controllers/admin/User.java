package controllers.admin;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.admin.*;

public class User extends Controller {
	
	public static Result add(){
		return ok(user.render());
	}
	
	public static Result edit(String code){
		return ok(user.render());
	}
	
	public static Result create(){
		return redirect("/admin/user");
	}
	
	public static Result update(){
		return redirect("/admin/user");
	}
	
	public static Result delete(String code){
		return redirect("/admin/user");
	}

}
