package com.gavin.dao;

import com.gavin.domain.Quiz;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuizDAO {
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

    public boolean insertQuiz(Quiz Quiz) throws SQLException {
        String sql = "INSERT INTO Quiz (type, start_time, end_time, username, fullname, result) VALUES (?, ?, ?, ?, ?, ?)";
        connect();
         
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, Quiz.getType());
        statement.setTimestamp(2,  Quiz.getStarttime());
        statement.setTimestamp(3, Quiz.getEndtime());
        statement.setString(4, Quiz.getUsername());
        statement.setString(5, Quiz.getFullName());
        statement.setInt(6, Quiz.getResult());
         
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }
    
    public Quiz getQuiz(int qzid) throws SQLException {
    	Quiz quiz = null;
        String sql = "SELECT * FROM Quiz WHERE qzid = ?";
         
        connect();
         
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, qzid);
         
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            qzid = resultSet.getInt("qzid");
            String type = resultSet.getString("type");
            Timestamp starttime = resultSet.getTimestamp("start_time");
            Timestamp endtime = resultSet.getTimestamp("end_time");
            String username = resultSet.getString("username");
            String fullName = resultSet.getString("fullname");
            int result = resultSet.getInt("result");
             
            quiz = new Quiz(qzid, type, starttime, endtime, username, fullName, result);
        }
         
        resultSet.close();
        statement.close();
         
        return quiz;
    }
    
    public ArrayList<Quiz> listAllQuizzesByUser(String username) throws SQLException {
        ArrayList<Quiz> listQuiz = new ArrayList<>();
         
        String sql = "SELECT * FROM Quiz WHERE username = '" + username + "'";
         
        connect();
         
        PreparedStatement statement = conn.prepareStatement(sql);
        //statement.setString(1, username);
        
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
        	int qzid = resultSet.getInt("qzid");
            String type = resultSet.getString("type");
            Timestamp starttime = resultSet.getTimestamp("start_time");
            Timestamp endtime = resultSet.getTimestamp("end_time");
            username = resultSet.getString("username");
            String fullName = resultSet.getString("fullname");
            int result = resultSet.getInt("result");
             
            Quiz quiz = new Quiz(qzid, type, starttime, endtime, username, fullName, result);
            listQuiz.add(quiz);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return listQuiz;
    }

    public ArrayList<Quiz> listAllQuizzesByCat(String cat) throws SQLException {
        ArrayList<Quiz> listQuiz = new ArrayList<>();

        String sql = "SELECT * FROM Quiz WHERE type = '" + cat + "'";

        connect();

        PreparedStatement statement = conn.prepareStatement(sql);
        //statement.setString(1, username);

        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int qzid = resultSet.getInt("qzid");
            cat = resultSet.getString("type");
            Timestamp starttime = resultSet.getTimestamp("start_time");
            Timestamp endtime = resultSet.getTimestamp("end_time");
            String username = resultSet.getString("username");
            String fullName = resultSet.getString("fullname");
            int result = resultSet.getInt("result");

            Quiz quiz = new Quiz(qzid, cat, starttime, endtime, username, fullName, result);
            listQuiz.add(quiz);
        }

        resultSet.close();
        statement.close();

        disconnect();

        return listQuiz;
    }

    public ArrayList<Quiz> listAllQuizzes() throws SQLException {
        ArrayList<Quiz> listQuiz = new ArrayList<>();

        String sql = "SELECT * FROM Quiz";

        connect();

        PreparedStatement statement = conn.prepareStatement(sql);

        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int qzid = resultSet.getInt("qzid");
            String type = resultSet.getString("type");
            Timestamp starttime = resultSet.getTimestamp("start_time");
            Timestamp endtime = resultSet.getTimestamp("end_time");
            String username = resultSet.getString("username");
            String fullName = resultSet.getString("fullname");
            int result = resultSet.getInt("result");

            Quiz quiz = new Quiz(qzid, type, starttime, endtime, username, fullName, result);
            listQuiz.add(quiz);
        }

        resultSet.close();
        statement.close();

        disconnect();

        return listQuiz;
    }

    public boolean insertChoice(int qzid, int qid, Integer integer) throws SQLException {
        String sql = "INSERT INTO QuizQuestion (qzid, qid, choiceindex) VALUES (?, ?, ?)";
        connect();

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, qzid);
        statement.setInt(2,  qid);
        statement.setInt(3, integer);

        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }

    public ArrayList<Quiz> listAllQuizzesByUserAndCat(String username, String cat) throws SQLException {
        ArrayList<Quiz> listQuiz = new ArrayList<>();

        String sql = "SELECT * FROM Quiz WHERE username = '" + username + "' AND type = '" + cat + "'";

        connect();

        PreparedStatement statement = conn.prepareStatement(sql);
        //statement.setString(1, username);

        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int qzid = resultSet.getInt("qzid");
            cat = resultSet.getString("type");
            Timestamp starttime = resultSet.getTimestamp("start_time");
            Timestamp endtime = resultSet.getTimestamp("end_time");
            username = resultSet.getString("username");
            String fullName = resultSet.getString("fullname");
            int result = resultSet.getInt("result");

            Quiz quiz = new Quiz(qzid, cat, starttime, endtime, username, fullName, result);
            listQuiz.add(quiz);
        }

        resultSet.close();
        statement.close();

        disconnect();

        return listQuiz;
    }
}
