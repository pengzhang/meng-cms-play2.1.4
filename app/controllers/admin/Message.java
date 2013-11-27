package controllers.admin;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.admin.*;

public class Message extends Controller {
	
	public static Result add(){
		return ok(message.render());
	}
	
	public static Result edit(String code){
		return ok(message.render());
	}
	
	public static Result create(){
		return redirect("/admin/message");
	}
	
	public static Result update(){
		return redirect("/admin/message");
	}
	
	public static Result delete(String code){
		return redirect("/admin/message");
	}

}
