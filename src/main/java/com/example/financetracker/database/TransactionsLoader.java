package com.example.financetracker.database;

import com.example.financetracker.model.CategoryType;
import com.example.financetracker.model.Transaction;
import com.example.financetracker.model.TransactionType;
import javafx.collections.ObservableList;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class TransactionsLoader {
    public static void loadFromDatabase(ObservableList<Transaction> transactionList){
        String sql="SELECT * FROM transactions";
        try(
                Connection conn= DatabaseManager.getConnection();
                Statement stmt= conn.createStatement();
                ResultSet result=stmt.executeQuery(sql);
                ){
            while(result.next()){
                double amount=result.getDouble("amount");
                LocalDate date=LocalDate.parse(result.getString("date"));
                CategoryType category=CategoryType.valueOf(result.getString("category"));
                TransactionType type=TransactionType.valueOf(result.getString("type"));
                String message=result.getString("message");

                Transaction t=new Transaction(amount,date,message,type,category);
                transactionList.add(t);
            }
        }catch(SQLException e){
            System.out.println("Error loading from database: "+ e.getMessage());
        }
    }
}
