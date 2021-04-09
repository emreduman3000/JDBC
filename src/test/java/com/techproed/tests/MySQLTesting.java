package com.techproed.tests;

import com.techproed.pojo.Employee;
import com.techproed.utilities.ConfigReader;
import com.techproed.utilities.DBUtil;
import com.techproed.utilities.DataBaseUtil;
import com.techproed.utilities.TestBase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.*;

import static com.techproed.utilities.DBUtil.getQueryResultList;

public class MySQLTesting {


    //jdbc:databaseType://hostName:PORT/DataBaseName
    String dbUrl=ConfigReader.getProperty("databaseUrl");
    //credentials
    String uname=ConfigReader.getProperty("username");
    String psswrd=ConfigReader.getProperty("password");

    //Connection,Statement,ResultSet
    protected Connection connection;
    protected Statement statement;
    protected ResultSet resultSet;

    @Before
    public void setUp() throws SQLException, ClassNotFoundException
    {

        // Make a connection to the Database using method.
        connection= DriverManager.getConnection(dbUrl,uname,psswrd);

        // Create Query to the Database using the Statement Object.
        statement= connection.createStatement();
    }



    @Test
    public void Test1() throws SQLException {
        //Send the query to database using execute query and store the results in the ResultSet object.
        resultSet = statement.executeQuery(ConfigReader.getProperty("sqlQuery"));

        //Retrieving the ResultSetMetadata object
        ResultSetMetaData rsMetaData = resultSet.getMetaData();
        System.out.println(rsMetaData.toString());
            /*
            com.mysql.cj.jdbc.result.ResultSetMetaData@235834f2 - Field level information:
            com.mysql.cj.result.Field@5656be13[dbName=emredb,tableName=Employee,originalTableName=employee,columnName=empID,originalColumnName=empID,mysqlType=3(FIELD_TYPE_INT),sqlType=4,flags= PRIMARY_KEY, charsetIndex=63, charsetName=ISO-8859-1]
            com.mysql.cj.result.Field@4218d6a3[dbName=emredb,tableName=Employee,originalTableName=employee,columnName=empName,originalColumnName=empName,mysqlType=253(FIELD_TYPE_VARCHAR),sqlType=12,flags=, charsetIndex=255, charsetName=UTF-8]
            com.mysql.cj.result.Field@76505305[dbName=emredb,tableName=Employee,originalTableName=employee,columnName=empAge,originalColumnName=empAge,mysqlType=3(FIELD_TYPE_INT),sqlType=4,flags=, charsetIndex=63, charsetName=ISO-8859-1]
             */

        //Retrieving the list of column names
        List <String>columnNames = DataBaseUtil.getColumnNames(rsMetaData);
        System.out.println(columnNames);//[empID, empName, empAge]

        //int rowCount = DataBaseUtil.getRowCount(resultSet);
        int columnCount = columnNames.size();

        HashMap<String,  String> allValues;
        List <HashMap<String, String>> employeeMapsInList=new ArrayList<>();
        List<Employee> employeesPojoObjects = new ArrayList<>();


        // While Loop to iterate through all data and print results

        while (resultSet.next()){
            allValues = new HashMap<>();
            for (int i=1;i<=columnCount;i++)
            {
                String value = resultSet.getString(i);
                allValues.put(columnNames.get(i-1),value);
            }
            employeeMapsInList.add(allValues);
            employeesPojoObjects.add(new Employee(allValues,columnNames));
        }
        System.out.println(employeeMapsInList);
        System.out.println(employeesPojoObjects);


        //assertion
        Assert.assertEquals(employeesPojoObjects.get(0).getEmpName(),"emre");
        Assert.assertEquals(employeeMapsInList.get(0).get("empName"),"emre");

        for (int i=0,j=0;i< employeeMapsInList.size();i++,j=0)
        {
            Assert.assertEquals(String.valueOf(employeesPojoObjects.get(i).getEmpID()),employeeMapsInList.get(i).get(columnNames.get(j++)));
            Assert.assertEquals(               employeesPojoObjects.get(i).getEmpName(),employeeMapsInList.get(i).get(columnNames.get(j++)));
            Assert.assertEquals(String.valueOf(employeesPojoObjects.get(i).getEmpAge()),employeeMapsInList.get(i).get(columnNames.get(j)));


        }





    }

}
