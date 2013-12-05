package controllers.admin.message;

import java.util.Map;

import models.message.Message;
import play.data.DynamicForm;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.admin.message.message;

public class AdminMessage extends Controller {
	
	public static Result add(){
		return ok(message.render(null,true));
	}
	
	public static Result edit(String code){
		Message msg = Message.getMessage(code);
		return ok(message.render(msg,false));
	}
	
	public static Result create(){
		Map<String,String> map = DynamicForm.form().bindFromRequest().data();
		Message msg = new Message();
		msg.name = map.get("name");
		msg.email = map.get("email");
		msg.mobile = map.get("mobile");
		msg.message = map.get("message");
		Message.createMessage(msg);
		return redirect("/admin/message");
	}
	
	public static Result update(){
		Map<String,String> map = DynamicForm.form().bindFromRequest().data();
		Message msg = new Message();
		msg.name = map.get("name");
		msg.email = map.get("email");
		msg.mobile = map.get("mobile");
		msg.message = map.get("message");
		msg.msg_code = map.get("msg_code");
		Message.modifyMessage(msg);
		return redirect("/admin/message");
	}
	
	public static Result delete(String code){
		Message.deleteMessage(code);
		return redirect("/admin/message");
	}
	
	public static Result audit(String code){
		Message.auditMessage(code);
		return redirect("/admin/message");
	}
	

}
