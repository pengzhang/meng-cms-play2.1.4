package controllers.admin.question;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.admin.question.*;

public class Question extends Controller {
	
	public static Result add(){
		return ok(question.render());
	}
	
	public static Result edit(String code){
		return ok(question.render());
	}
	
	public static Result create(){
		return redirect("/admin/question");
	}
	
	public static Result update(){
		return redirect("/admin/question");
	}
	
	public static Result delete(String code){
		return redirect("/admin/question");
	}

}
