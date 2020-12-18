package com.example.myapplication.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.adpter.commentAdpter;
import com.example.myapplication.dao.CommentDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.comment;

public class page extends AppCompatActivity {

    private List<comment> mData = null;
    private Context mContext;
    private commentAdpter mAdapter = null;
    private ListView listView;
    private int tiezi_id;
    private String name;
    private String nickname;
    private String title;
    private String introduce;
    private TextView page_title;
    private TextView page_introduce;
    private EditText comment;
    private Button comment_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page);
        mContext = this;
        listView = (ListView) findViewById(R.id.listview);
        mData = new ArrayList<comment>();
        Intent intent = getIntent();
        tiezi_id=intent.getIntExtra("id",0);
        name = intent.getStringExtra("name");
        nickname = intent.getStringExtra("nickname");
        title = intent.getStringExtra("title");
        introduce = intent.getStringExtra("introduce");


        page_title = findViewById(R.id.page_title);
        page_title.setText(title);
        page_introduce = findViewById(R.id.page_introduce);
        page_introduce.setText(introduce);
        comment = (EditText)findViewById(R.id.comment_edit);
        comment_btn = (Button)findViewById(R.id.comment_edit_btn);

        Thread thread = new Thread(new Runnable() {
            private static final String TAG = "home";
            @Override
            public void run() {
                CommentDao cd = new CommentDao();
                ResultSet rs = cd.kanPingLun(tiezi_id);
                try {
                    while (rs.next()){
                        //Log.i(TAG,rs.getString("content")+rs.getString("content")+rs.getString("time"));
                        mData.add(new comment(rs.getString("content"),rs.getString("name"),rs.getString("time"),rs.getString("nickname")));
                    }
                } catch (SQLException e) {
                }
            }
        });
        thread.start();
        try {
            thread.join();//阻塞调用此方法的线程进入 TIMED_WAITING 状态，直到线程完成。通常用于在main()主线程内，等待其它线程完成再结束main()主线程。

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Log.i("home","+++++++++++"+mData.toString());
        mAdapter = new commentAdpter(mData, mContext);
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(mContext, "点击了第" + position + "条数据", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void comment(View v){
        Intent intent = new Intent(page.this, faPingLun.class);
        intent.putExtra("id",tiezi_id);
        intent.putExtra("name",name);
        intent.putExtra("nickname",nickname);
        startActivity(intent);

    }
    public void fapinglun(View v){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String name_input = name;
                String nickname_input = nickname;
                String comment_input = comment.getText().toString().trim();

                CommentDao cd = new CommentDao();
                Boolean result = cd.faPingLun(name_input,comment_input,tiezi_id,nickname_input);
                if (!result){
                    Looper.prepare();
                    Toast toast = Toast.makeText(page.this,"评论成功！",Toast.LENGTH_SHORT);
                    toast.show();
                    finish();
                    Intent intent=new Intent(page.this,page.class);
                    intent.putExtra("id",tiezi_id);
                    intent.putExtra("name",name);
                    intent.putExtra("nickname",nickname);
                    intent.putExtra("title",title);
                    intent.putExtra("introduce",introduce);
                    startActivity(intent);
                    Looper.loop();

                }
                Log.i("fatie","fun"+result);
//                finish();
//                Intent intent=new Intent(page.this,page.class);
//                intent.putExtra("id",tiezi_id);
//                intent.putExtra("name",name);
//                intent.putExtra("nickname",nickname);
//
//                startActivity(intent);
            }
        }).start();

//        Intent intent=new Intent(page.this,page.class);
//        intent.putExtra("id",tiezi_id);
//        intent.putExtra("name",name);
//        intent.putExtra("nickname",nickname);
//       // intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        startActivity(intent);

    }
}