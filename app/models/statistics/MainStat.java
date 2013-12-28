package models.statistics;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import play.db.ebean.Model;

@Entity
@Table(name="mainstat")
public class MainStat extends Model{

	private static final long serialVersionUID = 1L;
	@Id
	public long id;
	public long administrator = 0;
	public long user = 0;
	public long article = 0;
	public long news = 0;
	public long image = 0;
	public long download = 0;
	public long message = 0;
	public long exam = 0;
	public long question = 0;
	public long domain = 0;
	public long advertising = 0;
	public long faq = 0;
	
	@Transient
	public final static String ADMINISTRATOR = "administrator";
	@Transient
	public final static String USER = "user";
	@Transient
	public final static String ARTICLE = "article";
	@Transient
	public final static String NEWS = "news";
	@Transient
	public final static String IMAGE = "image";
	@Transient
	public final static String DOWNLOAD = "download";
	@Transient
	public final static String MESSAGE = "message";
	@Transient
	public final static String EXAM = "exam";
	@Transient
	public final static String QUESTION = "question";
	@Transient
	public final static String DOMAIN = "domain";
	@Transient
	public final static String ADVERTISING = "advertising";
	@Transient
	public final static String FAQ = "faq";
	
	public static Model.Finder<Long,MainStat> find = new Model.Finder<Long,MainStat>(Long.class, MainStat.class);
	
	public static void updateMainStat(String statfield){
		if(getMainStat() == null){
			MainStat mstat = new MainStat();
			mstat.save();
		}else{
			MainStat ms = getMainStat();
			if(statfield.equalsIgnoreCase(ADMINISTRATOR)){
				ms.administrator += 1;
			}else if(statfield.equalsIgnoreCase(USER)){
				ms.user += 1;
			}else if(statfield.equalsIgnoreCase(ARTICLE)){
				ms.article += 1;
			}else if(statfield.equalsIgnoreCase(NEWS)){
				ms.news += 1;
			}else if(statfield.equalsIgnoreCase(IMAGE)){
				ms.image += 1;
			}else if(statfield.equalsIgnoreCase(DOWNLOAD)){
				ms.download += 1;
			}else if(statfield.equalsIgnoreCase(MESSAGE)){
				ms.message += 1;
			}else if(statfield.equalsIgnoreCase(EXAM)){
				ms.exam += 1;
			}else if(statfield.equalsIgnoreCase(QUESTION)){
				ms.question += 1;
			}else if(statfield.equalsIgnoreCase(DOMAIN)){
				ms.domain += 1;
			}else if(statfield.equalsIgnoreCase(ADVERTISING)){
				ms.advertising += 1;
			}else if(statfield.equalsIgnoreCase(FAQ)){
				ms.faq += 1;
			}
			ms.update();
		}
	}
	
	public static MainStat getMainStat(){
		return find.byId(1L);
	}
	
	

}
