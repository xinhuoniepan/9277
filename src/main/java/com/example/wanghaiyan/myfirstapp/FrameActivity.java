package com.example.wanghaiyan.myfirstapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class FrameActivity extends AppCompatActivity implements View.OnClickListener{

    private CheckBox cb_milk,cb_sugar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame);
        init();
    }
    private void init(){
        cb_milk = findViewById(R.id.cb_milk);
        cb_sugar = findViewById(R.id.cb_sugar);
        cb_milk.setOnClickListener(this);
        cb_sugar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.cb_milk:
                if (cb_milk.isChecked()){
                    Toast.makeText(FrameActivity.this,"milk",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.cb_sugar:
                if (cb_sugar.isChecked()){
                    Toast.makeText(FrameActivity.this,"sugar",Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }
}
