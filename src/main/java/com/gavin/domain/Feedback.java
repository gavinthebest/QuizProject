package com.gavin.domain;

import java.sql.Date;

public class Feedback {
	private int fid;
	private Date date;
	private String comment;
	private int rating;
	
	public Feedback(Date date, String comment, int rating) {
		this.date = date;
		this.comment = comment;
		this.rating = rating;
	}

	public int getFid()
	{
		return fid;
	}
	
	public Date getDate()
	{
		return date;
	}
	
	public String getComment()
	{
		return comment;
	}
	
	public int getRating()
	{
		return rating;
	}

	public void setDate(Date d)
	{
		date = d;
	}
	
	public void setComment(String s)
	{
		comment = s;
	}
	
	public void setRating(int r)
	{
		rating = r;
	}
}
