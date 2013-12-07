package models.question;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.avaje.ebean.Ebean;

import play.db.ebean.Model;
import utils.StringUtils;

@Entity
@Table(name="question")
public class Question extends Model{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public long id;
	@Column
	public String q_code = StringUtils.getMengCode();
	@Column
	public String qtitle;
	@Column
	public String qoptionA;
	@Column
	public String qoptionB;
	@Column
	public String qoptionC;
	@Column
	public String qoptionD;
	@Column
	public String qright;
	@Column
	public String exam_code;
	@Column
	public boolean status = false;
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	public Timestamp create_date;
	
	public static Model.Finder<Long, Question> find = new Model.Finder<Long, Question>(Long.class, Question.class);
	
	public static void createQuestion(Question q){
		q.save();
	}
	
	public static void modifyQuestion(Question q){
		q.id = getQuestionByCode(q.q_code).id;
		q.status = false;
		q.update();
	}
	
	public static Question getQuestion(long id){
		return find.byId(id);
	}
	
	public static Question getQuestionByCode(String code){
		return find.where().eq("q_code", code).findUnique();
	}

	public static void auditQuestion(String code){
		Question q = getQuestionByCode(code);
		q.status = true;
		q.update();
	}
	
	public static void deleteQuestion(String code){
		Ebean.delete(find.where().eq("q_code", code).findList());
	}
	
	public static List<Question> getQuestionPage(int page,int size){
		return find.findPagingList(size).getPage(page).getList();
	}
	
	public static List<Question> getExamQuestion(String exam_code){
		return find.where().eq("exam_code", exam_code).findList();
	}
	
	public static List<Question> getExamQuestionPage(String exam_code, int page, int size){
		return find.where().eq("exam_code", exam_code).findPagingList(size).getPage(page).getList();
	}

}
