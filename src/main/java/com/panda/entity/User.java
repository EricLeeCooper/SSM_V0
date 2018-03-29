package com.panda.entity;

public class User {
	private int id;
	private String name;
	private String email;
	private String age;
	private String address;
	private String phone;
	private String username;
	private String cardid;
	private String password;
	private String ident;
	private String identnum;
	
	public String getIdent() {
		return ident;
	}

	public void setIdent(String ident) {
		this.ident = ident;
	}

	public String getIdentnum() {
		return identnum;
	}

	public void setIdentnum(String identnum) {
		this.identnum = identnum;
	}

	public User() {
		super();
	}

	public User(int id, String name, String age, String address, String phone,
			String username, String cardid, String password) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.address = address;
		this.phone = phone;
		this.username = username;
		this.cardid = cardid;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCardid() {
		return cardid;
	}

	public void setCardid(String cardid) {
		this.cardid = cardid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", age=" + age
				+ ", address=" + address + ", phone=" + phone + ", username="
				+ username + ", cardid=" + cardid + ", password=" + password
				+ "]";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
