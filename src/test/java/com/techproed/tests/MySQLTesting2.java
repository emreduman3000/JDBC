package com.techproed.tests;

import com.techproed.utilities.DBUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

import static com.techproed.utilities.DBUtil.*;

public class MySQLTesting2 {

    String baseQuery;
    @Before
    public void setUp()
    {
        DBUtil.createConnectionToMySQL();
        baseQuery="select * from albums";
    }

    @Test
    public void dbUtilTest() throws Exception {
        DBUtil.executeQuery(baseQuery);

        System.out.println(DBUtil.getRowCount());

        System.out.println(getCellValue(baseQuery,2,3));
        //2020-02-10T12:45:56

        System.out.println(getQueryResultList(baseQuery+" where GenreId=1;"));
        //[[2, mangaAlbum, 2020-02-10T12:45:56, 2, 1], [4, tarkanAlbum2, 2010-03-13T12:45:57, 1, 1]]

        System.out.println(getRowList(baseQuery,2));
        //[2, mangaAlbum, 2020-02-10T12:45:56, 2, 1]

        System.out.println(getRowMap(baseQuery,4));
        //{ArtistId=1, AlbumId=4, AlbumName=tarkanAlbum2, DateReleased=2010-03-13T12:45:57, GenreId=1}

        List<String> columnNames=getColumnNames(baseQuery);
        System.out.println(columnNames);
        //[AlbumId, AlbumName, DateReleased, ArtistId, GenreId]

        System.out.println(getColumnData(baseQuery, columnNames.get(1)));
        //[tarkanAlbum, mangaAlbum, cezaAlbum, tarkanAlbum2]

        System.out.println(getMaxValue(baseQuery, columnNames.get(0)));//4
        System.out.println(getMaxValue(baseQuery, columnNames.get(1)));//tarkanAlbum2

        //check date in 3.line
        Assert.assertEquals(getCellValue(baseQuery,3, columnNames.indexOf("DateReleased")+1).toString(),"2010-03-13T12:45:56");


    }


}
