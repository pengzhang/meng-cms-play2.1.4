package models.category;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;

@Entity
public class Category extends Model {

	private static final long serialVersionUID = 1L;
	@Id
	public long id;
	public String cg_code;
	public String cg_name;
	public String cg_desc;
	public String cg_parent;
	public boolean is_channel;  //是否是频道
	

}
