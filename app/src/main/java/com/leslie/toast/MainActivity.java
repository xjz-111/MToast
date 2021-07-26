package com.leslie.toast;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.leslie.mtoast.MToast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
        findViewById(R.id.btn4).setOnClickListener(this);
        findViewById(R.id.btn5).setOnClickListener(this);
        findViewById(R.id.btn6).setOnClickListener(this);

        MToast.configGlobal(getApplicationContext()).iconResId(R.mipmap.a).textSize(16);



    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn1){
            show1();
        } else if (id == R.id.btn2){
            show2();
        } else if (id == R.id.btn3){
            show3();
        } else if (id == R.id.btn4){
            show4();
        } else if (id == R.id.btn5){
            show5();
        } else if (id == R.id.btn6){
            show6();
        }
    }


    // 简单显示
    private void show1(){
        MToast.show("AJSDASJDH");
    }

    // 带时间控制
    private void show2(){
        MToast.show("带时间控制", MToast.LENGTH_LONG);
    }

    // 带icon
    private void show3(){
        MToast.show("带icon", getResources().getDrawable(R.mipmap.ic_launcher));
    }

    // 带Config
    private void show4(){
        MToast.show("带Config", MToast.config().duration(MToast.LENGTH_LONG).iconResId(R.mipmap.ic_launcher).typeface(Typeface.DEFAULT_BOLD));
    }

    // 自定义view
    private void show5(){
        View view = LayoutInflater.from(this).inflate(com.leslie.mtoast.R.layout.mtoast, null);
        view.setBackgroundResource(R.drawable.toast_bg);
        ImageView imageView = view.findViewById(com.leslie.mtoast.R.id.toast_icon);
        TextView textView = view.findViewById(com.leslie.mtoast.R.id.toast_text);
        textView.setTextColor(Color.GREEN);
        textView.setText("自定义view");
        imageView.setImageResource(R.mipmap.a);
        MToast.show(view);
    }

    // 自定义view - 带config
    private void show6(){
        View view = LayoutInflater.from(this).inflate(com.leslie.mtoast.R.layout.mtoast, null);
        view.setBackgroundResource(R.drawable.toast_bg);
        ImageView imageView = view.findViewById(com.leslie.mtoast.R.id.toast_icon);
        TextView textView = view.findViewById(com.leslie.mtoast.R.id.toast_text);
        textView.setTextColor(Color.GREEN);
        textView.setText("自定义view - 带config");
        imageView.setImageResource(R.mipmap.a);
        MToast.show(view, MToast.config().duration(MToast.LENGTH_LONG).gravity(Gravity.BOTTOM, 0, 100));
    }

}
