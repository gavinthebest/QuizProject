package com.gavin.dao;

import com.gavin.domain.Feedback;
import com.gavin.util.JDBCUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FeedbackDAO {
    private Connection conn;

    public boolean insertFeedback(Feedback Feedback) throws SQLException {
        String sql = "INSERT INTO Feedback (date, comment, rating) VALUES (?, ?, ?)";
        JDBCUtils.connect(conn);
         
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setDate(1, Feedback.getDate()); //java.sql.Date.valueOf(java.time.LocalDate.now())
        statement.setString(2,  Feedback.getComment());
        statement.setInt(3, Feedback.getRating());
         
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        JDBCUtils.disconnect(conn);
        return rowInserted;
    }
}
