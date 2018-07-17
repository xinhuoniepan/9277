package com.example.wanghaiyan.myfirstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.wanghaiyan.common.model.Drink;

public class StarbuzzActivity extends AppCompatActivity {

    private ListView coffee_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starbuzz);
        init();
    }
    private void init(){
        coffee_list = findViewById(R.id.coffee_list);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,getResources().getStringArray(R.array.coffee_list));
        coffee_list.setAdapter(arrayAdapter);



        coffee_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String[] coffeeList = getResources().getStringArray(R.array.coffee_list);
                Toast.makeText(StarbuzzActivity.this,coffeeList[position],Toast.LENGTH_SHORT).show();
                Intent selectIntent = new Intent(StarbuzzActivity.this,SelectActivity.class);
                selectIntent.putExtra("Selected",coffeeList[position]);
                startActivity(selectIntent);
            }
        });


    }
}
