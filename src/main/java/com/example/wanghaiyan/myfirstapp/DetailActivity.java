package com.example.wanghaiyan.myfirstapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wanghaiyan.common.model.Drink;

import java.io.Serializable;

public class DetailActivity extends AppCompatActivity {

    private ImageView iv_detail_image;
    private TextView tv_name,tv_description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        init();
    }

    private void init(){
        iv_detail_image = findViewById(R.id.iv_detail_image);
        tv_name = findViewById(R.id.tv_name);
        tv_description = findViewById(R.id.tv_description);
        Drink drink = (Drink) getIntent().getSerializableExtra("selectedDetail");
        iv_detail_image.setImageResource(drink.getImageId());
        tv_name.setText(drink.getName());
        tv_description.setText(drink.getDescription());
    }
}
