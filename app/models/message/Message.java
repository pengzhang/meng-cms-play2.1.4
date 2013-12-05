package models.message;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.avaje.ebean.Ebean;

import play.db.ebean.Model;
import utils.StringUtils;

@Entity
@Table(name="message")
public class Message extends Model{

	private static final long serialVersionUID = 1L;
	
	@Id
	public long id;
	@Column
	public String msg_code = StringUtils.getMengCode();
	@Column
	public String name;
	@Column
	public String email;
	@Column
	public String mobile;
	@Column
	public String message;
	@Column
	public boolean status = false;
	@Column
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	public Timestamp create_date = new Timestamp(System.currentTimeMillis());	
	
	public static Model.Finder<Long, Message> find = new Model.Finder<Long, Message>(Long.class, Message.class);
	
	public static void createMessage(Message msg){
		msg.save();
	}
	
	public static void modifyMessage(Message msg){
		msg.id = getMessage(msg.msg_code).id;
		msg.update();
	}
	
	public static void deleteMessage(long id){
		find.byId(id).delete();
	}
	
	public static void deleteMessage(String code){
		Ebean.delete(find.where().eq("msg_code", code).findList());
	}
	
	public static void auditMessage(String code){
		Message msg = getMessage(code);
		msg.status = true;
		msg.update();
	}
	
	public static Message getMessage(long id){
		return find.byId(id);
	}
	
	public static List<Message> getMessageList(){
		return find.all();
	}
	
	public static List<Message> getMessageShow(){
		return find.where().eq("status", true).findList();
	}
	
	public static List<Message> getMessagePage(int page,int size){
		return find.findPagingList(size).getPage(page).getList();
	}
	
	public static int getMessageTotalPage(int size){
		return find.findPagingList(size).getTotalPageCount();
	}
	
	public static void statusMessage(long id){
		Message msg = find.byId(id);
		msg.status = true;
		msg.update();
	}

	public static Message getMessage(String code) {
		return find.where().eq("msg_code", code).findUnique();
	}

}
