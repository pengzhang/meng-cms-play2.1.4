package controllers.admin;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.admin.*;

public class Exam extends Controller {
	
	public static Result add(){
		return ok(exam.render());
	}
	
	public static Result edit(String code){
		return ok(exam.render());
	}
	
	public static Result create(){
		return redirect("/admin/exam");
	}
	
	public static Result update(){
		return redirect("/admin/exam");
	}
	
	public static Result delete(String code){
		return redirect("/admin/exam");
	}

}
