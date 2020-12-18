package com.example.myapplication.dao;

import android.util.Log;

import com.example.myapplication.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static android.content.ContentValues.TAG;

public class UserDao {

    JdbcUtil jdbcUtil = JdbcUtil.getInstance();
    //第一个参数为数据库名称，第二个参数为数据库账号 第三个参数为数据库密码
    Connection conn = jdbcUtil.getConnection("android","root","20003366");
    //注册
    public  boolean register(String name,String password){
        if (conn==null){
            Log.i(TAG,"register:conn is null");
            return false;
        }else {
            //进行数据库操作
            String sql = "insert into user(name,password) values(?,?)";
            try {
                PreparedStatement pre = conn.prepareStatement(sql);
                pre.setString(1,name);
                pre.setString(2,password);
                return pre.execute();
            } catch (SQLException e) {
                return false;
            }finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    //登录
    public boolean login(String name,String password){
        if (conn==null){
            Log.i(TAG,"register:conn is null");
            return false;
        }else {
            String sql = "select * from user where name=? and password=?";
            try {
                PreparedStatement pres = conn.prepareStatement(sql);
                pres.setString(1,name);
                pres.setString(2,password);
                ResultSet res = pres.executeQuery();
                boolean t = res.next();
                return t;
            } catch (SQLException e) {

                return false;
            }

        }
    }

    //设置用户名
    public  boolean settingNickname(String nickname,String name){
        if (conn==null){
            Log.i(TAG,"setting:conn is null");
            return false;
        }else {
            //进行数据库操作
            String sql = "update user set nickname = ? where name = ?";
            try {
                PreparedStatement pre = conn.prepareStatement(sql);
                pre.setString(1,nickname);
                pre.setString(2,name);
                return pre.execute();
            } catch (SQLException e) {
                return false;
            }finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    //设置密码
       public  boolean settingPassword(String password,String name){
        if (conn==null){
            Log.i(TAG,"setting:conn is null");
            return false;
        }else {
            //进行数据库操作
            String sql = "update user set password = ? where name = ?";
            try {
                PreparedStatement pre = conn.prepareStatement(sql);
                pre.setString(1,password);
                pre.setString(2,name);
                return pre.execute();
            } catch (SQLException e) {
                return false;
            }finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //查看用户名
    public String kanNickname(String name){
        if (conn==null){
            Log.i(TAG,"kanNickname:conn is null");
            return null;
        }else {
            String sql = "select nickname from user where name = ?";
            try {
                PreparedStatement pres = conn.prepareStatement(sql);
                pres.setString(1,name);
                ResultSet res = pres.executeQuery();
                res.next();
                String nick = res.getNString("nickname");

                Log.i(TAG,"title++++++++++++++++");
                return nick;
            } catch (SQLException e) {
                Log.i(TAG,"t==================================");

                return null;
            }

        }
    }


}