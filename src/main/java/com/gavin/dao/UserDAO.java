package com.gavin.dao;

import com.gavin.domain.Quiz;
import com.gavin.domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDAO {
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static String dbURL = "jdbc:mysql://localhost:3306/QuizProject?useSSL=false";
	static String username = "root";
	static String password = "Xie102938!";
	private Connection conn;
	
    protected void connect() throws SQLException  {
        if (conn == null || conn.isClosed()) {
            try {
                Class.forName(JDBC_DRIVER);
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            System.out.println("Connecting to database..............."+dbURL);
            conn = DriverManager.getConnection(dbURL, username, password);
            System.out.println("Connection is successful!!!!!!");
        }
    }
     
    protected void disconnect() throws SQLException {
        if (conn != null && !conn.isClosed()) {
        	conn.close();
        }
    }

    public boolean insertUser(User User) throws SQLException {
        String sql = "INSERT INTO User (username, firstname, lastname, password, suspended) VALUES (?, ?, ?, ?, ?)";
        connect();
         
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, User.getUsername());
        statement.setString(2, User.getFirstname());
        statement.setString(3,  User.getLastname());
        statement.setString(4, User.getPassword());
        statement.setInt(5, User.getSuspended());
         
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }
    
    public User getUser(User user) throws SQLException {
        String sql = "SELECT * FROM User WHERE username = ? AND password = ?";
         
        connect();
         
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, user.getUsername());
        statement.setString(2, user.getPassword());
         
        ResultSet resultSet = statement.executeQuery();
         
        if (resultSet.next()) {
            String un = resultSet.getString("username");
            String firstname = resultSet.getString("firstname");
            String lastname = resultSet.getString("lastname");
            String password = resultSet.getString("password");
            int suspended = resultSet.getInt("suspended");
             
            user = new User(un, firstname, lastname, password, suspended);
        }
         
        resultSet.close();
        statement.close();
         
        return user;
    }

    public User getUser(String username) throws SQLException {
        User user = null;
        String sql = "SELECT * FROM User WHERE username = ?";

        connect();

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, username);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String un = resultSet.getString("username");
            String firstname = resultSet.getString("firstname");
            String lastname = resultSet.getString("lastname");
            String password = resultSet.getString("password");
            int suspended = resultSet.getInt("suspended");

            user = new User(un, firstname, lastname, password, suspended);
        }

        resultSet.close();
        statement.close();

        return user;
    }

    public List<User> getAllUsers() throws SQLException {
        ArrayList<User> listUser = new ArrayList<>();
        String sql = "SELECT * FROM User";

        connect();

        PreparedStatement statement = conn.prepareStatement(sql);

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            String un = resultSet.getString("username");
            String firstname = resultSet.getString("firstname");
            String lastname = resultSet.getString("lastname");
            String password = resultSet.getString("password");
            int suspended = resultSet.getInt("suspended");

            User user = new User(un, firstname, lastname, password, suspended);
            listUser.add(user);
        }

        resultSet.close();
        statement.close();

        return listUser;
    }
    
	public boolean checkUsername(String username) throws SQLException {

		boolean exists = false;
		String sql = "Select * from user where username='" + username + "'";
		connect();

		PreparedStatement statement = conn.prepareStatement(sql);
		
		try {
			ResultSet resultSet = statement.executeQuery();
			exists = true;
			statement.close();
			resultSet.close();
		} catch (Exception e) {
			exists = false;
		}

		if (exists) {
			System.out.println("Username already exists !");
		} else {
			System.out.println("Username is available.");
		}
		
		return exists;
	}

    public boolean updateUser(User user) throws SQLException {
        String sql = "UPDATE user SET firstname = ?, lastname = ?, password = ?, suspended = ?";
        sql += " WHERE username = ?";
        connect();

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, user.getFirstname());
        statement.setString(2,  user.getLastname());
        statement.setString(3, user.getPassword());
        statement.setInt(4, user.getSuspended());
        statement.setString(5, user.getUsername());

        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;
    }
}
