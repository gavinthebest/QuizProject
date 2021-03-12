package com.gavin.domain;


public class User {
	private String username;
	private String firstname;
	private String lastname;
	private String password;
	private int suspended;
	
    public User(String username, String firstname, String lastname, String password) {
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
		this.suspended = 0;
	}
    
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
		this.firstname = null;
		this.lastname = null;
	}

	public User(String username, String firstname, String lastname, String password, int suspended) {
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
		this.suspended = suspended;
	}



    public String getUsername()
	{
		return username;
	}
	
	public void setUsername(String s)
	{
		username = s;
	}
	
	public String getFirstname()
	{
		return firstname;
	}
	
	public void setFirstname(String s)
	{
		firstname = s;
	}
	
	public String getLastname()
	{
		return lastname;
	}
	
	public void setLastname(String s)
	{
		lastname = s;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public void setPassword(String s)
	{
		password = s;
	}

	public int getSuspended() { return suspended; }

	public void setSuspended(int i) { suspended = i; }

}
