package com.example.myapplication.dao;

import android.util.Log;

import com.example.myapplication.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static android.content.ContentValues.TAG;

public class CommentDao {

    JdbcUtil jdbcUtil = JdbcUtil.getInstance();
    //第一个参数为数据库名称，第二个参数为数据库账号 第三个参数为数据库密码
    Connection conn = jdbcUtil.getConnection("android","root","20003366");
    //发评论
    public  boolean faPingLun(String name,String content,int tiezi_id,String nickname){
        if (conn==null){
            Log.i(TAG,"register:conn is null");
            return false;
        }else {
            //进行数据库操作
            String sql = "insert into comment(name,content,tiezi_id,nickname) values(?,?,?,?)";
            try {
                PreparedStatement pre = conn.prepareStatement(sql);
                pre.setString(1,name);
                pre.setString(2,content);
                pre.setInt(3,tiezi_id);
                pre.setString(4,nickname);
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
    //看评论
    public ResultSet kanPingLun(int tiezi_id){
        if (conn==null){
            Log.i(TAG,"register:conn is null");
            return null;
        }else {
            String sql = "select * from comment where tiezi_id =?";
            try {
                PreparedStatement pres = conn.prepareStatement(sql);
                pres.setInt(1,tiezi_id);
                ResultSet res = pres.executeQuery();

               Log.i(TAG,"title++++++++++++++++");
                return res;
            } catch (SQLException e) {
                Log.i(TAG,"t==================================");

                return null;
            }

        }
    }
}