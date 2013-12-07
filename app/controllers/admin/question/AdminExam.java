package controllers.admin.question;

import java.util.Map;

import models.question.Exam;
import play.data.DynamicForm;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.admin.question.exam;

public class AdminExam extends Controller {
	
	public static Result add(){
		return ok(exam.render(null,true));
	}
	
	public static Result edit(String code){
		Exam ex  = Exam.getExamByCode(code);
		return ok(exam.render(ex,false));
	}
	
	public static Result create(){
		Map<String,String> map = DynamicForm.form().bindFromRequest().data();
		Exam ex = new Exam();
		ex.exam_name = map.get("exam_name");
		ex.exam_desc = map.get("exam_desc");
		Exam.createExam(ex);
		return redirect("/admin/exam");
	}
	
	public static Result update(){
		Map<String,String> map = DynamicForm.form().bindFromRequest().data();
		Exam ex = new Exam();
		ex.exam_name = map.get("exam_name");
		ex.exam_desc = map.get("exam_desc");
		ex.e_code = map.get("e_code");
		Exam.modifyExam(ex);
		return redirect("/admin/exam");
	}
	
	public static Result delete(String code){
		Exam.deleteExam(code);
		return redirect("/admin/exam");
	}

}
