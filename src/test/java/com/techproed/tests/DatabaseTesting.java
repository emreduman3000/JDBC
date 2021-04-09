package com.techproed.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;

public class DatabaseTesting {

    //jdbc:databaseType://hostName:PORT/DataBaseName
    String databaseUrl="jdbc:mysql://107.182.225.121:3306/LibraryMgmt";

    //credentials
    String username="techpro";
    String password="tchpr2020";

    //Connection,Statement,ResultSet
    Connection connection;
    Statement statement;
    ResultSet resultSet;

    @Before
    public void setUp() throws SQLException, ClassNotFoundException
    {
        //By getConnection() - Database Connection
        connection=DriverManager.getConnection(databaseUrl,username,password);

        //createStatement()
        statement= connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
    }

    @Test
    public void Test1() throws SQLException {
        resultSet=statement.executeQuery("SELECT * FROM Book;");

    //skipping first row
        resultSet.next();

        String data1=resultSet.getString("BookName");
        System.out.println(data1);


    //print out all values in BookName Coloumn
        int rowCount=1;
        while (resultSet.next()){
            Object values= resultSet.getObject("BookName");
            System.out.println(rowCount++ +"-"+ values.toString());
        }

    //check whether 14 element are there in the coloumn
        Assert.assertEquals(rowCount,14);

    //check if 5. values is JAVA in the coloumn
        //go to fifth row in the column
        resultSet.absolute(5);
        //get the value
        String value2 = resultSet.getString("BookName");
        //assert the value
        Assert.assertEquals(value2,"Java");

    }

    @Test
    public void test2() throws SQLException {
        resultSet=statement.executeQuery("select * from Book;");

    //check if last value in the coloumn is UIPath
        //go to last row
        resultSet.last();
        //get the value
        String lastValue=resultSet.getString("BookName");
        //assertion
        Assert.assertEquals(lastValue,"UIPath");


        //Check first row is SQL
        resultSet.first();
        String firstValue= resultSet.getString("BookName");
        String expectedResult="SQL";
        Assert.assertEquals(firstValue,expectedResult);

    }

    @Test
    public void Test3() throws SQLException
    {
        //MetaData: Datayla ilgili bilgiler
        DatabaseMetaData databaseMetaData= connection.getMetaData();

        databaseMetaData.getURL();
        databaseMetaData.getSchemas();
        databaseMetaData.getUserName();
        databaseMetaData.getDatabaseProductName();
        databaseMetaData.getDatabaseProductVersion();

        //databaseMetaData.getTables();
        //databaseMetaData.getColumns();
        //databaseMetaData.getPrimaryKeys();



    }
}
