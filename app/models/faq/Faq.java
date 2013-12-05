package models.faq;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import play.db.ebean.Model;
import utils.StringUtils;

import com.avaje.ebean.Ebean;

@Entity
@Table(name="faq")
public class Faq extends Model {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public long id;
	@Column
	public String code = StringUtils.getMengCode();
	@Column
	public String question;
	@Lob
	public String answer;
	@Column
	public boolean status = false;
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	public Timestamp create_date = new Timestamp(System.currentTimeMillis());
	
	public static Model.Finder<Long,Faq> find = new Model.Finder<Long, Faq>(Long.class, Faq.class);
	
	public static void createFaq(Faq faq){
		faq.save();
	}
	
	public static Faq getFaqByCode(String code){
		return find.where().eq("code", code).findUnique();
	}
	
	public static void modifyFaq(Faq faq){
		faq.id = getFaqByCode(faq.code).id;
		faq.status = false;
		faq.update();
	}
	
	public static void deleteFaq(String code){
		Ebean.delete(find.where().eq("code", code).findList());
	}
	
	public static void auditFaq(String code){
		Faq faq = getFaqByCode(code);
		faq.status = true;
		faq.update();
	}
	
	public static List<Faq> getFaqPage(int page, int size){
		return find.findPagingList(size).getPage(page).getList();
	}

}
