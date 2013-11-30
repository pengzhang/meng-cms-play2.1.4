package controllers.admin;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.admin.*;
import views.html.admin.article.*;
import views.html.admin.question.*;
import views.html.admin.message.*;
import views.html.admin.user.*;
import views.html.admin.advertising.*;
import views.html.admin.faq.*;
import views.html.admin.image.*;
import views.html.admin.news.*;
import views.html.admin.download.*;

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
	
	public static Result advertising(){
		return ok(advertising_view.render());
	}
	
	public static Result faq(){
		return ok(faq_view.render());
	}
	
	public static Result image(){
		return ok(image_view.render());
	}
	
	public static Result image_category(){
		return ok(image_category_view.render());
	}
	
	public static Result news(){
		return ok(news_view.render());
	}
	
	public static Result news_category(){
		return ok(news_category_view.render());
	}
	
	public static Result download(){
		return ok(download_view.render());
	}
	
	public static Result download_category(){
		return ok(download_category_view.render());
	}
	
	
	
	
	
	

}
