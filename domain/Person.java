package nanyou.txl.domain;

public class Person {
	/**
	 * 主键,姓名,电话,地址,邮箱
	 */
	 private int	id;
	 private String username;
	 private long phone;
	 private String address;
	 private String postbox;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPostbox() {
		return postbox;
	}
	public void setPostbox(String postbox) {
		this.postbox = postbox;
	}
	
	public String toString() {
		return "Person [username=" + username + ", phone=" + phone + ", address=" + address + ", postbox=" + postbox
				+ "]";
	}
	
}
