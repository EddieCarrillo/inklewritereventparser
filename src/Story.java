import java.util.Date;

public class Story {
	private String url_key;
	private String title;
	private Date updated_at;
	private Date created_at;
	private Data data;
	
	
	public String getUrl_key() {
		return url_key;
	}
	public void setUrl_key(String url_key) {
		this.url_key = url_key;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	public Data getData() {
		return data;
	}
	public void setData(Data data) {
		this.data = data;
	}
	
}
