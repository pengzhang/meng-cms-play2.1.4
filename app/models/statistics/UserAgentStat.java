package models.statistics;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import play.db.ebean.Model;
import utils.StringUtils;

@Entity
@Table(name="user_agent")
public class UserAgentStat extends Model{

	private static final long serialVersionUID = 1L;
	@Id
	public long id;
	public String code = StringUtils.getMengCode();
	public String brower;
	public String brower_version;
	public String platform;
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	public Timestamp create_date = new Timestamp(System.currentTimeMillis());

	public static Model.Finder<Long, UserAgentStat> find = new Model.Finder<Long, UserAgentStat>(Long.class, UserAgentStat.class);
	
	public static void createUA(UserAgentStat ua){
		ua.save();
	}
	
	public static UserAgentStat getUserAgent(String code){
		return find.where().eq("code", code).findUnique();
	}
	
	public static List<UserAgentStat> getUserAgent(int page,int size){
		return find.orderBy().desc("create_date").findPagingList(size).getPage(page).getList();
	}

}
