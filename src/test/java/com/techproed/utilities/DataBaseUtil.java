package com.techproed.utilities;

import com.sun.corba.se.pept.transport.ReaderThread;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBaseUtil

{

    static public int getRowCount(ResultSet resultSet) throws SQLException {
        //get the number of row all values in employee table
        int count = 0;
        int preCount=0;
        while (resultSet.next()) {
            count++;
        }

        //  preCount=count;//3
        //        System.out.println(preCount);
        //
        //        if(!resultSet.next()){
        //            while(preCount>0){
        //                resultSet.previous();
        //                preCount--;
        //            }
        //        }
        //        System.out.println(preCount);
        return count;
    }

    static public List <String >getColumnNames(ResultSetMetaData rsMetaData) throws SQLException {
        //Retrieving the list of column names
        List<String> columnNames = new ArrayList<String>();
        int count = rsMetaData.getColumnCount();

        for (int i = 1; i <= count; i++)
            columnNames.add(rsMetaData.getColumnName(i));

        return columnNames;
    }

     public static List< HashMap<String, Object> >putAllValues(ResultSet resultSet, ResultSetMetaData rsMetaData, int rowCount, int columnCount) throws SQLException {

        List<HashMap<String, Object>> allValues= new ArrayList<>();

        for (int i = 1; i <=rowCount; i++)
        {
            for (int j = 1; j <= columnCount; j++) {
                allValues.add((HashMap<String, Object>) new HashMap<String, Object>().put(rsMetaData.getColumnName(i),resultSet.getObject(rsMetaData.getColumnName(i))));
            }
        }

       return allValues;


    }
}