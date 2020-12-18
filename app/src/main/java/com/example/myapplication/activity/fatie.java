package com.example.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.dao.TieziDao;

public class fatie extends AppCompatActivity {

    //private Button fatie;
    private EditText title;
    private EditText introduce;
    private String name;
    private String nickname;
    private int fenqu_id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fatie);

        //fatie = (Button)findViewById(R.id.fatie_edit_btn);
        title = (EditText)findViewById(R.id.title_edit);
        introduce = (EditText)findViewById(R.id.introduce_edit);
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        nickname = intent.getStringExtra("nickname");
        //fenqu_id = intent.getIntExtra("fenqu_id",0);
        //分区
        Spinner spinner = (Spinner) findViewById(R.id.spinner2);
        spinner.setOnItemSelectedListener(
                new  AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                        String[] fenqu = getResources().getStringArray(R.array.fenqu);
                        fenqu_id = pos;
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        // Another interface callback
                    }
                });

    }

    public void fatie(View v){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String name_input = name;
                String nickname_input = nickname;
                String title_input = title.getText().toString().trim();
                String introduce_input = introduce.getText().toString().trim();
                //int fenqu_id_input = fenqu_id;


                TieziDao tz = new TieziDao();
                Boolean result = tz.faTie(name_input,title_input,introduce_input,nickname_input,fenqu_id);
                if (!result){
                    Looper.prepare();
                    Toast toast = Toast.makeText(fatie.this,"发帖成功！",Toast.LENGTH_SHORT);
                    toast.show();
                    Intent intent=new Intent(fatie.this, home.class);
                    startActivity(intent);
                    Looper.loop();

                }
                Log.i("fatie","fun"+result);
                    Intent intent=new Intent(fatie.this,home.class);
                    startActivity(intent);
                }
        }).start();
    }

}
