package com.example.financetracker.database;

import com.example.financetracker.model.Transaction;
import java.sql.*;

public class DatabaseManager {
    private static final String URL="jdbc:sqlite:finance.db";

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(URL);
    }

    public static void initializeDatabase(){
        String sql= """
                CREATE TABLE IF NOT EXISTS transactions(
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    amount REAL,
                    date TEXT,
                    message TEXT,
                    category TEXT,
                    type TEXT
                );
        """;

        try(
                Connection conn=getConnection();
                Statement stmt=conn.createStatement()
        ){
            stmt.execute(sql);
        } catch(SQLException e){
            System.err.println("Database Error: "+e.getMessage());
        }
    }

    public static void deleteTransaction(Transaction t) throws SQLException{
        String sql="DELETE FROM transactions WHERE amount = ? AND date = ? AND category = ? AND type = ? AND message = ?";
        Connection conn=getConnection();
        PreparedStatement stmt= conn.prepareStatement(sql);
        stmt.setDouble(1,t.getAmount());
        stmt.setString(2,t.getDate().toString());
        stmt.setString(3,t.getCategory().name());
        stmt.setString(4,t.getType().name());
        stmt.setString(5,t.getMessage());
        stmt.executeUpdate();
        stmt.close();
    }

    public static void updateTransaction(Transaction selectedTransaction, Transaction newTransaction) throws SQLException{
        String sql="UPDATE transactions SET amount = ?, date = ?, category = ?, type = ?, message = ? WHERE amount = ? AND date = ? AND category = ? AND type = ? AND message = ?";
        Connection conn=getConnection();
        PreparedStatement stmt=conn.prepareStatement(sql);

        stmt.setDouble(1,newTransaction.getAmount());
        stmt.setString(2,newTransaction.getDate().toString());
        stmt.setString(3,newTransaction.getCategory().name());
        stmt.setString(4,newTransaction.getType().name());
        stmt.setString(5, newTransaction.getMessage());

        stmt.setDouble(6,selectedTransaction.getAmount());
        stmt.setString(7,selectedTransaction.getDate().toString());
        stmt.setString(8,selectedTransaction.getCategory().name());
        stmt.setString(9,selectedTransaction.getType().name());
        stmt.setString(10,selectedTransaction.getMessage());

        stmt.executeUpdate();
        stmt.close();
    }
}
