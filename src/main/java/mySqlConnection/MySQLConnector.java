package mySqlConnection;

import  java.sql.Connection;
import  java.sql.Statement;
import  java.sql.ResultSet;
import  java.sql.DriverManager;
import  java.sql.SQLException;

public class MySQLConnector {

    public static void  main(String[] args) throws  ClassNotFoundException, SQLException {
        //Connection URL Syntax: "jdbc:mysql://ipaddress:portnumber/db_name"
        String dbUrl = "jdbc:mysql://localhost:3306/emredb";

        //Database Username
        String username = "root";

        //Database Password
        String password = "Kule.3000";

        //Load mysql jdbc driver
        Class.forName("com.mysql.jdbc.Driver");

        //Create Connection to DB
        Connection connection = DriverManager.getConnection(dbUrl,username,password);

        //Create Statement Object
        Statement statement = connection.createStatement();


        //Query to Execute
        String query = "select *  from employee;";

        // Execute the SQL Query. Store results in ResultSet
        ResultSet resultSet= statement.executeQuery(query);

        // While Loop to iterate through all data and print results
        while (resultSet.next()){
            for (int i=1;i<=3;i++)
            {
                String empID = resultSet.getString(1);
                String empName = resultSet.getString(2);
                String empAge = resultSet.getString(3);
                System. out.println(empID+"  "+empName+ " " +empAge);
            }
        }
        // closing DB Connection
        connection.close();
    }
}