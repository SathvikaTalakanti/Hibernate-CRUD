package com.onetoone_mapping;
import java.sql.Connection;
import java.sql.DriverManager;

public class TestJDBC
{
    public  static  void main(String[] args)
    {
        String jdbcurl="jdbc:mysql://localhost:3306/hb-01-one-to-one-uni?useSSL=false";
        String user="hbstudent";
        String pass="Sath@9381@#9";
        try
        {
            System.out.println(" Connecting to db "+jdbcurl);
            Connection myconn=
                    DriverManager.getConnection(jdbcurl,user,pass);
            System.out.println("Connection successfull!!!");

        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
