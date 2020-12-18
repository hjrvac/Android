package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.dao.CommentDao;

public class faPingLun extends AppCompatActivity {

    private EditText comment;
    private Button comment_btn;
    private String name;
    private String nickname;
    private int tiezi_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fa_ping_lun);
        comment = (EditText)findViewById(R.id.comment_edit);
        comment_btn = (Button)findViewById(R.id.comment_edit_btn);
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        nickname = intent.getStringExtra("nickname");
        tiezi_id = intent.getIntExtra("id",0);

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
                    Toast toast = Toast.makeText(faPingLun.this,"发帖成功！",Toast.LENGTH_SHORT);
                    toast.show();
                    Intent intent=new Intent(faPingLun.this, page.class);
                    intent.putExtra("id",tiezi_id);
                    intent.putExtra("name",name);
                    intent.putExtra("nickname",nickname);
                    startActivity(intent);
                    Looper.loop();

                }
                Log.i("fatie","fun"+result);
                Intent intent=new Intent(faPingLun.this,page.class);
                intent.putExtra("id",tiezi_id);
                intent.putExtra("name",name);
                intent.putExtra("nickname",nickname);
                startActivity(intent);
            }
        }).start();
    }

}