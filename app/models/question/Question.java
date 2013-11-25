package models.question;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import play.Logger;
import play.db.ebean.Model;

@Entity
@Table(name="question")
public class Question extends Model{

	private static final long serialVersionUID = 1L;
	
	@Id
	public long id;
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
	
	public static Model.Finder<Long, Question> find = new Model.Finder<Long, Question>(Long.class, Question.class);
	
	public static void createQuestion(Question q){
		q.save();
	}
	
	public static void modifyQuestion(Question q){
		q.update();
	}
	
	public static Question getQuestion(long id){
		return find.byId(id);
	}
	
	public static List<Question> getQuestionList(){
		return find.all();
	}
	
	public static void deleteQuestion(long id){
		Logger.info("[Message Model delete question] id:" + id);
		find.byId(id).delete();
	}
	
	public static List<Question> getQuestionPage(int page,int size){
		return find.findPagingList(size).getPage(page).getList();
	}
	
	public static int getQuestionTotalPage(int size){
		return find.findPagingList(size).getTotalPageCount();
	}

		
}
