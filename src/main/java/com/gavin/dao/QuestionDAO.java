package com.gavin.dao;

import com.gavin.domain.Question;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class QuestionDAO {
	public int currentQuestion = 0;
	public static Integer numberOfQuestions=10;
	public Map<Integer, Integer> selections = new LinkedHashMap<Integer, Integer>();
	public ArrayList<Question> questionList = new ArrayList<Question>(numberOfQuestions);
	
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static String dbURL = "jdbc:mysql://localhost:3306/QuizProject?useSSL=false";
	static String Username = "root";
	static String password = "Xie102938!";
	private Connection conn;
	
    protected void connect() throws SQLException  {
        if (conn == null || conn.isClosed()) {
            try {
                Class.forName(JDBC_DRIVER);
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            conn = DriverManager.getConnection(dbURL, Username, password);
        }
    }
     
    protected void disconnect() throws SQLException {
        if (conn != null && !conn.isClosed()) {
        	conn.close();
        }
    }

    public boolean insertQuestion(Question Question) throws SQLException {
        String sql = "INSERT INTO Question (type, description, optionA, optionB, optionC, optionD, answerIndex) VALUES (?, ?, ?, ?, ?, ?, ?)";
        connect();
         
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, Question.getType());
        statement.setString(2,  Question.getDescription());
        statement.setString(3, Question.getOptionA());
        statement.setString(4, Question.getOptionB());
        statement.setString(5, Question.getOptionC());
        statement.setString(6, Question.getOptionD());
        statement.setInt(7, Question.getAnswerIndex());
         
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }
    
    public Question getQuestion(int qid) throws SQLException {
    	Question question = null;
        String sql = "SELECT * FROM Question WHERE qid = ?";
         
        connect();
         
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, qid);
         
        ResultSet resultSet = statement.executeQuery();
         
        if (resultSet.next()) {
        	qid = resultSet.getInt("qid");
            String type = resultSet.getString("type");
            String desc = resultSet.getString("description");
            String optionA = resultSet.getString("optiona");
            String optionB = resultSet.getString("optionb");
            String optionC = resultSet.getString("optionc");
            String optionD = resultSet.getString("optiond");
            int ans = resultSet.getInt("answerindex");
             
            question = new Question(qid, type, desc, optionA, optionB, optionC, optionD, ans);
        }
         
        resultSet.close();
        statement.close();
         
        return question;
    }
    
    public List<Question> generateQuestions(String type) throws SQLException {         
        String sql = "SELECT * FROM Question WHERE type = '" + type + "' ORDER BY rand() limit 10";

        connect();
         
        PreparedStatement statement = conn.prepareStatement(sql);
        //statement.setString(1, type);
        
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
        	int qid = resultSet.getInt("qid");
            type = resultSet.getString("type");
            String desc = resultSet.getString("description");
            String optionA = resultSet.getString("optiona");
            String optionB = resultSet.getString("optionb");
            String optionC = resultSet.getString("optionc");
            String optionD = resultSet.getString("optiond");
            int ans = resultSet.getInt("answerindex");
             
            Question question = new Question(qid, type, desc, optionA, optionB, optionC, optionD, ans);
            questionList.add(question);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return questionList;
    }
    
	public int calculateResult(QuestionDAO quiz) {
		int totalCorrect = 0;
		List<Integer> userSelectionsList = new ArrayList<Integer>(10);
		for (Map.Entry<Integer, Integer> entry : selections.entrySet()) {
			userSelectionsList.add(entry.getValue());
		}
		List<Question> questionList = quiz.questionList;
		List<Integer> correctAnswersList = new ArrayList<Integer>(10);
		for (Question question : questionList) {
			correctAnswersList.add(question.getAnswerIndex());
		}
		

		for (int i = 0; i < selections.size(); i++) {
			//System.out.println(userSelectionsList.get(i) + " --- " + correctAnswersList.get(i));
			if ((userSelectionsList.get(i) - 1) == correctAnswersList.get(i)) {
				totalCorrect++;
			}
		}

		//System.out.println("You Got " + totalCorrect + " Correct");
		return totalCorrect;
	}

    public boolean updateQuestion(Question question) throws SQLException {
        String sql = "UPDATE Question SET type = ?, description = ?, optionA = ?, optionB = ?, optionC = ?, optionD = ?, answerIndex = ?";
        sql += " WHERE qid = ?";
        connect();

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, question.getType());
        statement.setString(2,  question.getDescription());
        statement.setString(3, question.getOptionA());
        statement.setString(4, question.getOptionB());
        statement.setString(5, question.getOptionC());
        statement.setString(6, question.getOptionD());
        statement.setInt(7, question.getAnswerIndex());
        statement.setInt(8, question.getQid());

        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }

    public boolean deleteQuestion(int qid) throws SQLException {
        String sql = "DELETE FROM Question where qid = ?";

        connect();

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, qid);

        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;
    }
}
