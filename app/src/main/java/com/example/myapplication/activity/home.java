package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;


import com.example.myapplication.R;
import com.example.myapplication.adpter.tieziAdpter;
import com.example.myapplication.dao.TieziDao;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.tiezi;

public class home extends AppCompatActivity {

    private List<tiezi> mData = null;
    private Context mContext;
    private tieziAdpter mAdapter = null;
    private ListView listView;
    private EditText search;
    private Button search_btn;
    private Button fatie;
    private String name;
    private String nickname;
    private int fenqu_id = 0;
    private String[] fenqu_array;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        nickname = intent.getStringExtra("nickname");
        fenqu_id = intent.getIntExtra("fenqu_id",0);

        search = (EditText)findViewById(R.id.search);
        search_btn = (Button)findViewById(R.id.search_btn);
        fatie = (Button)findViewById(R.id.fatie_btn);

        mContext = this;
        listView = (ListView) findViewById(R.id.listview);
        mData = new ArrayList<tiezi>();

        mData = (List<tiezi>) getIntent().getSerializableExtra("data");
//        mData = intent.getParcelableArrayListExtra();
        fenqu_array = getResources().getStringArray(R.array.fenqu);


        if(mData==null){
            mData = new ArrayList<tiezi>();
            Thread thread = new Thread(new Runnable() {
                private static final String TAG = "home";
                @Override
                public void run() {
                    TieziDao tz = new TieziDao();
                    ResultSet rs = tz.kanTie();
                    try {
                        while (rs.next()){
                            Log.i(TAG,rs.getString("title")+rs.getString("name")+rs.getString("introduce"));
                            mData.add(new tiezi(rs.getString("title"),rs.getString("name"), rs.getString("introduce"),
                                    rs.getInt("tiezi_id"),rs.getString("nickname"),fenqu_array[rs.getInt("subject")]));
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


        }


        Log.i("home","+++++++++++"+mData.toString());
        mAdapter = new tieziAdpter(mData, mContext);

        listView.setAdapter(mAdapter);
        //mAdapter.notifyDataSetChanged();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int tiezi_id = mData.get(position).getId();
                String title = mData.get(position).getTitle();
                String introduce = mData.get(position).getIntroduce();

                Toast.makeText(mContext, "点击了第" + position + "条数据", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(home.this, page.class);
                intent.putExtra("id",tiezi_id);
                intent.putExtra("name",name);
                intent.putExtra("nickname",nickname);
                intent.putExtra("introduce",introduce);
                intent.putExtra("title",title);
                startActivity(intent);
            }
        });

        //分区
        Spinner spinner = (Spinner) findViewById(R.id.spinner1);
        spinner.setSelection( fenqu_id , true );
        spinner.setOnItemSelectedListener(
                new  AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                        String[] fenqu = getResources().getStringArray(R.array.fenqu);
                        fenqu_id = pos;
                        if(pos != 0){
                            Toast.makeText(home.this, "你点击的是:" +fenqu[pos], Toast.LENGTH_SHORT).show();

                        }
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        // Another interface callback
                    }
                });

    }

    //功能按钮
    public void homeFunction(View v){
        switch (v.getId()){
            //搜索按钮
            case R.id.search_btn:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String key = search.getText().toString().trim();
                        TieziDao tz = new TieziDao();
                        ResultSet rs = null;
                        if(key.equals("") && fenqu_id != 0 ) rs = tz.searchTiezi(fenqu_id);
                        else if(!key.equals("") && fenqu_id == 0) rs = tz.searchTiezi(key);
                        else if(!key.equals("") && fenqu_id != 0) rs = tz.searchTiezi(fenqu_id,key);
                        else rs = tz.kanTie();
                        mData = new ArrayList<tiezi>();

                        if(rs!=null){
                            try {
                                while (rs.next()){
                                    Log.i("function",rs.getString("title")+rs.getString("name")+rs.getString("introduce"));
                                    mData.add(new tiezi(rs.getString("title"),rs.getString("name"),rs.getString("introduce"),rs.getInt("tiezi_id"),rs.getString("nickname"),fenqu_array[rs.getInt("subject")]));
                                }
                            } catch (SQLException e) {
                            }
                            Intent intent=new Intent(home.this,home.class);
                            intent.putExtra("data", (Serializable) mData);
                            intent.putExtra("name",name);
                            intent.putExtra("fenqu_id",fenqu_id);
                            intent.putExtra("nickname",nickname);
                            startActivity(intent);
                        }
                    }
                }).start();
                break;
                //发帖按钮
            case R.id.fatie_btn:
                Intent intent=new Intent(home.this, com.example.myapplication.activity.fatie.class);
 //               intent.putExtra("data", (Serializable) mData);
                intent.putExtra("name",name);
                intent.putExtra("nickname",nickname);
                startActivity(intent);
                break;
            case R.id.setting_btn:
                Intent intent1=new Intent(home.this, setting.class);
                //               intent.putExtra("data", (Serializable) mData);
                intent1.putExtra("name",name);
                intent1.putExtra("nickname",nickname);
                startActivity(intent1);
                break;

        }

    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}