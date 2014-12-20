package reaf.db.beans;

public class Category {
	private long id;
	private short site;
	private int external;	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public short getSite() {
		return site;
	}
	public void setSite(short site) {
		this.site = site;
	}
	public int getExternal() {
		return external;
	}
	public void setExternal(int external) {
		this.external = external;
	}
	
	@Override
	public String toString() {
		return "Category [id=" + id + ", site=" + site + ", external="
				+ external + "]";
	}
}
