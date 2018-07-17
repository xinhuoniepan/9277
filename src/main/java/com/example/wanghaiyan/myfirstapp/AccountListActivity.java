package com.example.wanghaiyan.myfirstapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.wanghaiyan.common.model.Account;

public class AccountListActivity extends AppCompatActivity {

    private ListView lv_account_listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_list);
        init();
    }

    private void init() {
        //初始化一个Adapter
        ArrayAdapter<String> accountAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,getResources().getStringArray(R.array.account_list));
        //通过ID获取listview
        ListView accountlistView = findViewById(R.id.lv_account_listView);
        //设置listview的Adapter
        accountlistView.setAdapter(accountAdapter);

        lv_account_listView = findViewById(R.id.lv_account_listView);
        lv_account_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        showAccountName(getResources().getStringArray(R.array.account_list)[0]);
                        break;
                    case 1:
                        showAccountName(getResources().getStringArray(R.array.account_list)[1]);
                        break;
                    case 2:
                        showAccountName(getResources().getStringArray(R.array.account_list)[2]);
                        break;
                    case 3:
                        showAccountName(getResources().getStringArray(R.array.account_list)[3]);
                        break;
                }
            }
        });


    }

    private void showAccountName(String name){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Account");
        alertDialog.setMessage(name);
        alertDialog.setPositiveButton(R.string.OK, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialog.setNegativeButton(R.string.Close, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialog.show();
    }

    @Override
    public void onBackPressed(){
        Intent backIntent = new Intent();
        backIntent.putExtra("account_num","4");
        setResult(2,backIntent);
        finish();
    }

}
