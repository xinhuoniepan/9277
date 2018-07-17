package com.example.wanghaiyan.myfirstapp;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.wanghaiyan.common.model.Drink;
import com.example.wanghaiyan.common.utils.Constant;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SelectActivity extends ListActivity {

    private ListView lv_select;
    private Drink[] strList = {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        init();
    }

    private void init(){
        lv_select = findViewById(R.id.lv_select);

        switch(getIntent().getStringExtra("Selected")){
            case Constant.COFFEE_DRINKS:
                strList = Drink.drinks;
                break;
            case Constant.COFFEE_FOOD:
                break;
            case Constant.COFFEE_STORES:
                break;
        }
        ArrayAdapter<Drink> arrayAdapter = new ArrayAdapter<Drink>(this,R.layout.support_simple_spinner_dropdown_item,strList);
        lv_select.setAdapter(arrayAdapter);
        lv_select.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = parent.getAdapter().getItem(position).toString();
                Toast.makeText(SelectActivity.this,name,Toast.LENGTH_SHORT).show();
                Drink drink = selectByName(name);


                Intent seletedIntent = new Intent(SelectActivity.this,DetailActivity.class);
                seletedIntent.putExtra("selectedDetail",(Serializable) drink);
                startActivity(seletedIntent);
            }
        });

    }

    private Drink selectByName(String name){
        Drink[] drinkList = Drink.drinks;
        List<String> nameList = new ArrayList<String>();
        for(Drink drink:drinkList){
            nameList.add(drink.getName());
        }
        if(nameList.indexOf(name)>-1){
            return drinkList[nameList.indexOf(name)];
        } else {
            return null;
        }
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
    }
}
