package com.gavin.domain;

public class Question {

	private int qid;
	private String type;
	private String description;
	private String optionA;
	private String optionB;
	private String optionC;
	private String optionD;
	private int answerIndex;

	public Question(int qid, String type, String description, String optionA, String optionB, String optionC,
			String optionD, int answerIndex) {
		this.qid = qid;
		this.type = type;
		this.description = description;
		this.optionA = optionA;
		this.optionB = optionB;
		this.optionC = optionC;
		this.optionD = optionD;
		this.answerIndex = answerIndex;
	}

	public Question(String type, String description, String optionA, String optionB, String optionC,
					String optionD, int answerIndex) {
		this.type = type;
		this.description = description;
		this.optionA = optionA;
		this.optionB = optionB;
		this.optionC = optionC;
		this.optionD = optionD;
		this.answerIndex = answerIndex;
	}

    public Question() {

    }

    public int getQid()
	{
		return qid;
	}
	
	public String getType()
	{ 
		return type;
	}

	public String getDescription()
	{ 
		return description;
	}

	public String getOptionA()
	{
		return optionA;
	}

	public String getOptionB()
	{
		return optionB;
	}
	
	public String getOptionC()
	{
		return optionC;
	}
	
	public String getOptionD()
	{
		return optionD;
	}
	
	public int getAnswerIndex()
	{
		return answerIndex;
	}
	
	public void setType(String s)
	{
		type = s;
	}
	
	public void setDescription(String s)
	{
		description = s;
	}
	
	public void setAnswerIndex(int i)
	{
		answerIndex = i;
	}
	
	public void setOptionA(String s)
	{
		optionA = s;
	}
	
	public void setOptionB(String s)
	{
		optionB = s;
	}
	
	public void setOptionC(String s)
	{
		optionC = s;
	}
	
	public void setOptionD(String s)
	{
		optionD = s;
	}

}