package controllers.admin.question;

import java.util.List;
import java.util.Map;

import models.question.Exam;
import models.question.Question;
import play.data.DynamicForm;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.admin.question.question;

public class AdminQuestion extends Controller {
	
	public static Result add(){
		List<Exam> exams = Exam.getExamAll();	
		return ok(question.render(exams, null,true));
	}
	
	public static Result edit(String code){
		List<Exam> exams = Exam.getExamAll();	
		Question q = Question.getQuestionByCode(code);
		return ok(question.render(exams, q,false));
	}
	
	public static Result create(){
		Map<String,String> map = DynamicForm.form().bindFromRequest().data();
		Question qt = new Question();
		qt.qtitle = map.get("qtitle");
		qt.qoptionA = map.get("qoptionA");
		qt.qoptionB = map.get("qoptionB");
		qt.qoptionC = map.get("qoptionC");
		qt.qoptionD = map.get("qoptionD");
		qt.qright = map.get("qright");
		qt.exam_code = map.get("exam_code");
		Question.createQuestion(qt);
		return redirect("/admin/question");
	}
	
	public static Result update(){
		Map<String,String> map = DynamicForm.form().bindFromRequest().data();
		Question qt = new Question();
		qt.qtitle = map.get("qtitle");
		qt.qoptionA = map.get("qoptionA");
		qt.qoptionB = map.get("qoptionB");
		qt.qoptionC = map.get("qoptionC");
		qt.qoptionD = map.get("qoptionD");
		qt.qright = map.get("qright");
		qt.q_code = map.get("q_code");
		qt.exam_code = map.get("exam_code");
		Question.modifyQuestion(qt);
		return redirect("/admin/question");
	}
	
	public static Result delete(String code){
		Question.deleteQuestion(code);
		return redirect("/admin/question");
	}
	
	public static Result audit(String code){
		Question.auditQuestion(code);
		return redirect("/admin/question");
	}

}
