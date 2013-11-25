package models.message;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import play.db.ebean.Model;

@Entity
@Table(name="message")
public class Message extends Model{

	private static final long serialVersionUID = 1L;
	
	@Id
	public long id;
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
	
	public static Model.Finder<Long, Message> find = new Model.Finder<Long, Message>(Long.class, Message.class);
	
	public static void createMessage(Message msg){
		msg.save();
	}
	
	public static void modifyMessage(Message msg){
		msg.update();
	}
	
	public static void deleteMessage(long id){
		find.byId(id).delete();
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
	
	public static int getMessagePage(int size){
		return find.findPagingList(size).getTotalPageCount();
	}
	
	public static void statusMessage(long id){
		Message msg = find.byId(id);
		msg.status = true;
		msg.update();
	}

}
