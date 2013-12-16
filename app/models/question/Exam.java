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
	@Column
	public boolean exam_status = false;
	@Column
	public String exam_author;
	@Column
	public String web_site_code;
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	public Timestamp create_date = new Timestamp(System.currentTimeMillis());
	
	public static  Model.Finder<Long, Exam> find = new Model.Finder<Long, Exam>(Long.class, Exam.class);
	
	public static void createExam(Exam ex){
		ex.save();
	}

	public static void modifyExam(Exam ex){
		ex.id = getExamByCode(ex.e_code).id;
		ex.update();
	}
	
	public static void deleteExam(String code){
		Ebean.delete(find.where().eq("e_code", code).findList());
	}
	
	public static void auditExam(String code){
		Exam ex = getExamByCode(code);
		ex.exam_status  = true;
		ex.update();
	}
	
	public static Exam getExamByCode(String code){
		return find.where().eq("e_code", code).findUnique();
	}
	
	public static List<Exam> getExamPage(int page,int size){
		return find.findPagingList(size).getPage(page).getList();
	}

	public static List<Exam> getWSExamPage(String ws_code, int page,int size){
		return find.where().eq("web_site_code", ws_code).findPagingList(size).getPage(page).getList();
	}

	public static List<Exam> getExamAll() {
		return find.all();
	}
	

}
