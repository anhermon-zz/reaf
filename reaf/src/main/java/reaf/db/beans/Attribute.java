package reaf.db.beans;

public class Attribute {
	
	int id;
	Category category;
	String attribute;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public String getAttribute() {
		return attribute;
	}
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	@Override
	public String toString() {
		return "Attribute [id=" + id + ", category=" + category
				+ ", attribute=" + attribute + "]";
	}
}
