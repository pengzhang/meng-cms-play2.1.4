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

import play.db.ebean.Model;
import utils.StringUtils;

import com.avaje.ebean.Ebean;

@Entity
@Table(name="exam")
public class Exam extends Model {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public long id;
	@Column
	public String e_code = StringUtils.getMengCode();
	@Column
	public String exam_name;
	@Column
	public String exam_desc;
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	public Timestamp create_date;
	
	public static  Model.Finder<Long, Exam> find = new Model.Finder<Long, Exam>(Long.class, Exam.class);
	
	public static void createExam(Exam ex){
		ex.save();
	}
	
	public static Exam getExamByCode(String code){
		return find.where().eq("e_code", code).findUnique();
	}
	
	public static void modifyExam(Exam ex){
		ex.id = getExamByCode(ex.e_code).id;
		ex.update();
	}
	
	public static void deleteExam(String code){
		Ebean.delete(find.where().eq("e_code", code).findList());
	}
	
	public static List<Exam> getExamPage(int page,int size){
		return find.findPagingList(size).getPage(page).getList();
	}

	public static List<Exam> getExamAll() {
		return find.select("e_code,exam_name").findList();
	}
	

}
