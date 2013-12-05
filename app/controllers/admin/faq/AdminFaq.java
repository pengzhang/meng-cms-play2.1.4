package controllers.admin.faq;

import java.util.Map;

import models.faq.Faq;
import play.data.DynamicForm;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.admin.faq.faq;

public class AdminFaq extends Controller {
	
	public static Result add(){
		return ok(faq.render(null,true));
	}
	
	public static Result edit(String code){
		Faq f = Faq.getFaqByCode(code);
		return ok(faq.render(f,false));
	}
	
	public static Result create(){
		Map<String,String> map = DynamicForm.form().bindFromRequest().data();
		Faq faq = new Faq(); 
		faq.question = map.get("question");
		faq.answer  = map.get("answer");
		Faq.createFaq(faq);
		return redirect("/admin/faq");
	}
	
	public static Result update(){
		Map<String,String> map = DynamicForm.form().bindFromRequest().data();
		Faq faq = new Faq(); 
		faq.question = map.get("question");
		faq.answer  = map.get("answer");
		faq.code = map.get("code");
		Faq.modifyFaq(faq);
		return redirect("/admin/faq");
	}
	
	public static Result delete(String code){
		Faq.deleteFaq(code);
		return redirect("/admin/faq");
	}
	
	public static Result audit(String code){
		Faq.auditFaq(code);
		return redirect("/admin/faq");
	}

}
