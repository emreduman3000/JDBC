package com.techproed.github;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.*;
import java.util.Properties;


public class EmployeeTest {

        public static void main(String[] args) throws SQLException, ClassNotFoundException {
            WebDriver driver;
            String host ="localhost";
            String port ="3306";
            Class.forName("com.mysql.jdbc.Driver");

            Properties properties = new Properties();
            properties.setProperty("useSSL", "false");
            properties.setProperty("autoReconnect", "true");


            Connection con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/qadb", "root", "Vivekananda1!");
            Statement st=  con.createStatement();
            ResultSet rs = st.executeQuery("select *from Employee where name = 'Esha'");

            while(rs.next()){
                System.setProperty("webdriver.chrome.driver","C:/Users/Emre Duman/Documents/selenium dependencies/drivers/chromedriver.exe");


                driver = new ChromeDriver();
                driver.get("https://login.salesforce.com/?locale=au");
                driver.findElement(By.xpath("//*[@id='username']")).sendKeys(rs.getString(1));
                driver.findElement(By.cssSelector("#password")).sendKeys(rs.getString("location"));
                driver.findElement(By.xpath("//*[@id='Login']")).click();

            }
        }

    }
