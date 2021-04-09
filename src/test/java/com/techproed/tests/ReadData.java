package com.techproed.tests;

import com.techproed.utilities.DBUtil;
import com.techproed.utilities.DBUtil.*;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

public class ReadData {

    @Before
    public void setUp()
    {
        DBUtil.createConnection();
    }

    @Test
    public void readData() throws SQLException {
        String query="Select Name from dbo.tHOTEL";

        //statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        //resultSet = statement.executeQuery(query);
        DBUtil.executeQuery(query);

        //skipping a line
        DBUtil.getResultSet().next();

        String firstName=DBUtil.getResultSet().getString("empName");

        System.out.println(firstName);

    }


}
