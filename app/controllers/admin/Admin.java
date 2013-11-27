package controllers.admin;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.admin.*;

public class Admin extends Controller {
	
	public static Result index(){
		return ok(index.render());
	}
	
	public static Result main(){
		return ok(dashboard.render());
	}
	
	public static Result article(){
		return ok(article_view.render());
	}
	
	public static Result article_category(){
		return ok(article_category_view.render());
	}
	
	public static Result message(){
		return ok(message_view.render());
	}
	
	public static Result question(){
		return ok(question_view.render());
	}
	
	public static Result exam(){
		return ok(exam_view.render());
	}
	
	public static Result user(){
		return ok(user_view.render());
	}

}
