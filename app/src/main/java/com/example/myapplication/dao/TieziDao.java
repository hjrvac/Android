package com.example.myapplication.dao;

import android.util.Log;
import android.widget.LinearLayout;

import com.example.myapplication.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static android.content.ContentValues.TAG;

public class TieziDao {

    JdbcUtil jdbcUtil = JdbcUtil.getInstance();
    //第一个参数为数据库名称，第二个参数为数据库账号 第三个参数为数据库密码
    Connection conn = jdbcUtil.getConnection("android","root","20003366");
    //发帖
    public  Boolean faTie(String name,String title,String introduce,String nickname,int fenqu_id){
        if (conn==null){
            Log.i(TAG,"register:conn is null");
            return false;
        }else {
            //进行数据库操作
            String sql = "insert into tiezi(name,title,introduce,nickname,subject) values(?,?,?,?,?)";
            try {
                PreparedStatement pre = conn.prepareStatement(sql);
                pre.setString(1,name);
                pre.setString(2,title);
                pre.setString(3,introduce);
                pre.setString(4,nickname);
                pre.setInt(5,fenqu_id);
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
    //看帖
    public ResultSet kanTie(){
        if (conn==null){
            Log.i(TAG,"register:conn is null");
            return null;
        }else {
            String sql = "select * from tiezi order by time desc ";
            try {
                PreparedStatement pres = conn.prepareStatement(sql);
                ResultSet res = pres.executeQuery();

               Log.i(TAG,"title++++++++++++++++");
                return res;
            } catch (SQLException e) {
                Log.i(TAG,"t==================================");

                return null;
            }

        }
    }
    //找帖
    public ResultSet searchTiezi(String key){
        if (conn==null){
            Log.i(TAG,"searchTiezi:conn is null");
            return null;
        }else {
            String sql = "select * from tiezi where title like ? ";

            try {
                PreparedStatement pres1 = conn.prepareStatement(sql);
                Log.i(TAG,key);
                pres1.setString(1,"%"+key+"%");
                Log.i(TAG,"t==================================");
                ResultSet res = pres1.executeQuery();

                return res;
            } catch (SQLException e) {
                Log.i(TAG,"t1==================================");

                return null;
            }

        }

    }

    //按分区找帖
    public ResultSet searchTiezi(int fenqu_id){
        if (conn==null){
            Log.i(TAG,"searchTiezi:conn is null");
            return null;
        }else {
            String sql = "select * from tiezi where subject = ? ";

            try {
                PreparedStatement pres1 = conn.prepareStatement(sql);
                pres1.setInt(1,fenqu_id);
                Log.i(TAG,"t==================================");
                ResultSet res = pres1.executeQuery();

                return res;
            } catch (SQLException e) {
                Log.i(TAG,"t1==================================");

                return null;
            }

        }

    }

    //按分区和关键字找帖
    public ResultSet searchTiezi(int fenqu_id,String key){
        if (conn==null){
            Log.i(TAG,"searchTiezi:conn is null");
            return null;
        }else {
            String sql = "select * from tiezi where subject = ? and title like ? ";

            try {
                PreparedStatement pres1 = conn.prepareStatement(sql);
                pres1.setInt(1,fenqu_id);
                pres1.setString(2,"%"+key+"%");
                Log.i(TAG,"t==================================");
                ResultSet res = pres1.executeQuery();

                return res;
            } catch (SQLException e) {
                Log.i(TAG,"t1==================================");

                return null;
            }

        }

    }
}