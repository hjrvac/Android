package com.example.myapplication;


import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class JdbcUtil {
    private static JdbcUtil instance;

    public static JdbcUtil getInstance(){
        if (instance ==null){
            instance = new JdbcUtil();
        }
        return instance;
    }
    public Connection getConnection(String dbName,String name,String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://10.0.2.2:3306/"+dbName+"?characterEncoding=UTF-8&serverTimezone=UTC";
            return DriverManager.getConnection(url,name,password);
        } catch (Exception e) {
            return null;
        }
    }
}