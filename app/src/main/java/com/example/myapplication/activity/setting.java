package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.dao.UserDao;

public class setting extends AppCompatActivity {

    private static  final  String TAG="SettingActivity";
    private EditText nickname;
    private EditText password;
    private String name;
    private String n;
    private String psw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        nickname = findViewById(R.id.nickname_setting);
        password = findViewById(R.id.password_setting);

        Intent intent = getIntent();
        name = intent.getStringExtra("name");


    }
    //用户根据点击事件来找到相应的功能
    public void setting(View v){
        n = nickname.getText().toString().trim();
        psw = password.getText().toString().trim();
        if(!n.equals("")){
            new Thread(new Runnable() {
                    @Override
                    public void run() {
                        UserDao ud = new UserDao();
                        boolean result =ud.settingNickname(n,name);
                        if (!result){
                            Looper.prepare();
                            Toast toast = Toast.makeText(setting.this,"更新昵称成功！",Toast.LENGTH_SHORT);
                            toast.show();
                            Looper.loop();
                        }
                        Log.i(TAG,"fun"+result);
                    }
                }).start();
        }
        if(!psw.equals("")) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    UserDao ud = new UserDao();
                    boolean result = ud.settingPassword(psw, name);
                    if (!result) {
                        Looper.prepare();
                        Toast toast = Toast.makeText(setting.this, "更新密码成功！", Toast.LENGTH_SHORT);
                        toast.show();
                        Looper.loop();
                    }
                    Log.i(TAG, "fun" + result);
                }
            }).start();
        }
        Intent intent = new Intent(setting.this, home.class);
        intent.putExtra("nickname",n);
        startActivity(intent);







//        switch (v.getId()){
//            case R.id.nickname_setting_btn:
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        String n = nickname.getText().toString().trim();
//                        UserDao ud = new UserDao();
//                        boolean result =ud.settingNickname(n,name);
//                        if (!result){
//                            Looper.prepare();
//                            Toast toast = Toast.makeText(setting.this,"更新昵称成功！",Toast.LENGTH_SHORT);
//                            toast.show();
//                            Looper.loop();
//                        }
//                        Log.i(TAG,"fun"+result);
//                    }
//                }).start();
//                break;
//            case R.id.password_setting_btn:
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        String psw = password.getText().toString().trim();
//                        UserDao ud = new UserDao();
//                        boolean result =ud.settingPassword(psw,name);
//                        if (!result){
//                            Looper.prepare();
//                            Toast toast = Toast.makeText(setting.this,"更新密码成功！",Toast.LENGTH_SHORT);
//                            toast.show();
//                            Looper.loop();
//                        }
//                        Log.i(TAG,"fun"+result);
//                    }
//                }).start();
//                break;
//
//        }

    }
}