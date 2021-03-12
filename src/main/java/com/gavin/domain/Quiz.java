package com.gavin.domain;

import java.sql.Timestamp;


public class Quiz {
	
	private int qzid;
	private String type;
	private Timestamp starttime;
	private Timestamp endtime;
	private String username;
	private String fullName;
	private int result;
	
	public Quiz(String type, Timestamp starttime, Timestamp endtime, String username, String fullName, int result) {
		this.type = type;
		this.starttime = starttime;
		this.endtime = endtime;
		this.username = username;
		this.fullName = fullName;
		this.result = result;
	}
	
	public Quiz(int qzid, String type, Timestamp starttime, Timestamp endtime, String username, String fullName, int result) {
		this.qzid = qzid;
		this.type = type;
		this.starttime = starttime;
		this.endtime = endtime;
		this.username = username;
		this.fullName = fullName;
		this.result = result;
	}

	public int getQzid()
	{
		return qzid;
	}
	
	public String getType()
	{ 
		return type;
	}
	
	public void setType(String s)
	{
		type = s;
	}
	
	public Timestamp getStarttime()
	{ 
		return starttime;
	}
	
	public void setStarttime(Timestamp s)
	{
		starttime = s;
	}
	
	public Timestamp getEndtime()
	{ 
		return endtime;
	}
	
	public void setEndtime(Timestamp s)
	{
		endtime = s;
	}
	
	public String getUsername()
	{
		return username;
	}
	
	public void setUsername(String s)
	{
		username = s;
	}

	public int getResult() {
		return result;
	}
	
	public void setResult(int i)
	{
		result = i;
	}

	public String getFullName() { return fullName;
	}
	public void setFullName(String s)
	{
		fullName = s;
	}
}
