package com.manytomany;

import java.sql.Connection;
import java.sql.DriverManager;

public class Testjdbc {
    public static void main(String[] args){
        String jdbcUrl="jdbc:mysql://localhost:3306/hb-05-many-to-many?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
        String user= "hbstudent";
        String pass="Sath@9381@#9";

        try{
            System.out.println("Connecting to database: "+jdbcUrl);
            Connection myConn= DriverManager.getConnection(jdbcUrl, user, pass);
            System.out.println("Connection Successful!!!!!");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}