package com.example.lizelu.helloworld;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    //加载布局，初始化控件
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //获取界面上的按钮
        Button myFirstButton = (Button) findViewById(R.id.myFirstButton);

        //按钮点击的回调
        myFirstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "点击MyFirstButton", Toast.LENGTH_LONG).show();
            }
        });


        //结束这个Activity
        Button finishActivityButton = (Button) findViewById(R.id.finish_activity);
        finishActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        Button goSecondActivityButton = (Button) findViewById(R.id.go_sceond_button);
        goSecondActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //新建一个Intent(当前Activity, SecondActivity)=====显示Intent
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);


                //传值给下一个Activity
                String data = "我是上一个Activity中传过来的值";

                intent.putExtra("extra_data", data);

                //启动Intent
                startActivity(intent);
            }
        });


        Button goThiredActivityButton = (Button) findViewById(R.id.go_third_button);
        goThiredActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //从下一个Activity中获取数据
                Intent intent = new Intent(MainActivity.this, ThirdActivity.class);
                startActivityForResult(intent, 1);
            }
        });



        Button openBaiduButton = (Button) findViewById(R.id.open_baidu_button);
        openBaiduButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //调用本地浏览器打开网址
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.baidu.com"));
                startActivity(intent);
            }
        });

        Button telPhoneButton = (Button) findViewById(R.id.tel_number);
        telPhoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:10010"));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    String returnedData = data.getStringExtra("data_return");

                    TextView returnedDataTextView = (TextView) findViewById(R.id.return_textview);
                    returnedDataTextView.setText(returnedData);
                }
                break;
        }
    }

    @Override
    //创建菜单
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        Log.d("ddddd", "msdgggdddd");
        return true;
    }

    @Override
    //菜单按钮响应事件
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            this.showToastWithContext("Setting");
            return true;
        }

        if (id == R.id.action_haha){
            this.showToastWithContext("hehe");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showToastWithContext(String context){
        Toast.makeText(MainActivity.this, context, Toast.LENGTH_SHORT).show();
    }
}

