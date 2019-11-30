package pojo;

public class Copies {
	private int id; 
	private String bookid; 
	private String rack; 
	private String status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBookid() {
		return bookid;
	}
	public void setBookid(String bookid) {
		this.bookid = bookid;
	}
	public String getRack() {
		return rack;
	}
	public void setRack(String rack) {
		this.rack = rack;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Copies() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Copies [id=" + id + ", bookid=" + bookid + ", rack=" + rack + ", status=" + status + "]";
	}
	public Copies(int id, String bookid, String rack, String status) {
		super();
		this.id = id;
		this.bookid = bookid;
		this.rack = rack;
		this.status = status;
	} 

}
