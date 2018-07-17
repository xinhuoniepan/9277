package com.example.wanghaiyan.myfirstapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import java.io.DataOutputStream;
import java.io.IOException;

import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wanghaiyan.common.utils.Base64Utils;


import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton humburger;
    private Switch network;
    private TextView show_text;
    private Button connOnClick,accoutOnClick,callOnClick,smsOnClick,spinnerOnClick,appOut,startService,stopService,stopwatch,starbuzz;
    private MyHandler handler;
    private boolean connFlag = false;
    private Spinner spinnerContents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        if(Build.VERSION.SDK_INT > 9){
//            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//            StrictMode.setThreadPolicy(policy);
//        }
        handler=new MyHandler();
        init();

    }


    private void init() {

        humburger = findViewById(R.id.humburger);
        network = findViewById(R.id.network);
        show_text = findViewById(R.id.show_text);
        connOnClick = findViewById(R.id.connOnClick);
        accoutOnClick = findViewById(R.id.accoutOnClick);
        callOnClick = findViewById(R.id.callOnClick);
        smsOnClick = findViewById(R.id.smsOnClick);
        spinnerContents = findViewById(R.id.spinnerContents);
        spinnerOnClick = findViewById(R.id.spinnerOnClick);
        appOut = findViewById(R.id.appOut);
        startService = findViewById(R.id.startService);
        stopService = findViewById(R.id.stopService);
        stopwatch = findViewById(R.id.stopwatch);
        starbuzz = findViewById(R.id.starbuzz);
        starbuzz.setOnClickListener(this);
        stopwatch.setOnClickListener(this);
        startService.setOnClickListener(this);
        stopService.setOnClickListener(this);
        appOut.setOnClickListener(this);
        humburger.setOnClickListener(this);
        connOnClick.setOnClickListener(this);
        accoutOnClick.setOnClickListener(this);
        callOnClick.setOnClickListener(this);
        smsOnClick.setOnClickListener(this);
        spinnerOnClick.setOnClickListener(this);
        show_text.setSelected(true);
        ArrayAdapter<String> adapter;
        String[] arrModel = getResources().getStringArray(R.array.account_list);
        adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,arrModel);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerContents.setAdapter(adapter);
        spinnerContents.setVisibility(View.VISIBLE);
        spinnerContents.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                show_text.setText(spinnerContents.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //disConnect();
        network.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {

//                    Toast.makeText(MainActivity.this, "enable", Toast.LENGTH_SHORT).show();
//
//                    ConnectivityManager manager = (ConnectivityManager) getApplicationContext().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
//                    NetworkInfo networkInfo = manager.getActiveNetworkInfo();
//                    if (manager != null && networkInfo != null && networkInfo.isAvailable()) {
//                        new GetThread().start();
//                        if(!connFlag){
//                            new PostThread().start();
//                        }
//                    } else {
//                        Toast.makeText(MainActivity.this, "不能上网", Toast.LENGTH_SHORT).show();
//
//                    }
                } else {
//                    new GetThread().start();
//                    if (connFlag) {
//                        new PostThread().start();
//                    }
                    Toast.makeText(MainActivity.this, "网络已断开", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.humburger:
                Intent frameIntent = new Intent(this,FrameActivity.class);
                startActivity(frameIntent);
                break;
            case R.id.connOnClick:
                Uri uri = Uri.parse("https://www.baidu.com");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
            case R.id.callOnClick:
                Intent callIntent = new Intent();
                callIntent.setAction(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"));
                try {
                    startActivity(callIntent);
                } catch(Exception e) {
                    show_text.setText("SIM卡无");
                }

                break;
            case R.id.smsOnClick:
                Intent smsIntent = new Intent();
                smsIntent.setAction(Intent.ACTION_SENDTO);
                smsIntent.setData(Uri.parse("smsto:"));
                smsIntent.putExtra("sms_body","welcome to ...");
                startActivity(smsIntent);
                break;
            case R.id.accoutOnClick:
                Intent accountIntent = new Intent(this,AccountListActivity.class);
                startActivityForResult(accountIntent,1);
                break;
            case R.id.spinnerOnClick:
                Toast.makeText(this,spinnerContents.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
                break;
            case R.id.appOut:
                Intent appOutIntent = new Intent(Intent.ACTION_SEND);
                appOutIntent.setType("text/plain");
                String messageText = "AppOutIntent";
                appOutIntent.putExtra(Intent.EXTRA_TEXT,messageText);
                Intent choseIntent = Intent.createChooser(appOutIntent,"send message");
                startActivity(choseIntent);
                break;
            case R.id.startService:
                Intent intentStartService = new Intent(this,MyService.class);
                startService(intentStartService);
                break;
            case R.id.stopService:
                Intent intentStopService = new Intent(this,MyService.class);
                stopService(intentStopService);
                break;
            case R.id.stopwatch:
                Intent stopWatchIntent = new Intent(this,StopwatchActivity.class);
                startActivity(stopWatchIntent);
                break;
            case R.id.starbuzz:
                Intent starbuzzIntent = new Intent(this,StarbuzzActivity.class);
                startActivity(starbuzzIntent);
                break;
        }
    }


    //网络线程，因为不能在主线程访问Intent
    class GetThread extends Thread{
        public void run(){
            HttpURLConnection conn=null;//声明连接对象
            String urlStr="http://www.baidu.com";
            InputStream is = null;
            String resultData = "";
            try {
                URL url = new URL(urlStr); //URL对象
                conn = (HttpURLConnection)url.openConnection(); //使用URL打开一个链接,下面设置这个连接
                conn.setRequestMethod("GET"); //使用get请求

                if(conn.getResponseCode()==200){//返回200表示连接成功
                    connFlag = true;
                    Log.e("网络已连接","网络已连接");
//                    Toast.makeText(MainActivity.this,"网络已连接",Toast.LENGTH_SHORT).show();
//                    is = conn.getInputStream(); //获取输入流
//                    InputStreamReader isr = new InputStreamReader(is);
//                    BufferedReader bufferReader = new BufferedReader(isr);
//                    String inputLine  = "";
//                    while((inputLine = bufferReader.readLine()) != null){
//                        resultData += inputLine + "\n";
//                    }
//                    System.out.println("get方法取回内容："+resultData);
//                    showRes("get方法取回内容：" + resultData);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    class PostThread extends Thread {
        public void run(){
            HttpURLConnection conn = null;
            String urlStr = getResources().getString(R.string.login_url);
            InputStream is = null;
            String resultData = "";
            String username1 = "lixufei";
            String password1 = "!QAZxsw2";
            String username = Base64Utils.getBase64(username1);
            String password = Base64Utils.getBase64(password1);
            String param = "";
            try {
                URL url = new URL(urlStr);
                conn = (HttpURLConnection)url.openConnection();
                conn.setRequestMethod("POST");

                if(!connFlag){
                    param = "username1=" + URLEncoder.encode(username1, "UTF-8") + "&username=" + URLEncoder.encode(username, "UTF-8") +
                            "&password1=" + URLEncoder.encode(password1, "UTF-8") + "&password=" + URLEncoder.encode(password, "UTF-8") + "&actionType=umlogin&userIpMac=&language=1";
                } else {
                    param = "language=1&actionType=umlogout";
                }


                DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
                dos.writeBytes(param);
                dos.flush();
                dos.close();

                if(conn.getResponseCode() == 200){
                    connFlag = true;
                    Toast.makeText(MainActivity.this,"网络连接成功",Toast.LENGTH_SHORT).show();

//                    is = conn.getInputStream();
//                    InputStreamReader isr = new InputStreamReader(is);
//                    BufferedReader bufferedReader = new BufferedReader(isr);
//                    String inputLine = "";
//                    while ((inputLine = bufferedReader.readLine()) != null){
//                        resultData += inputLine + "\n";
//                    }
//                    Log.e("POST方法取回内容"  ,resultData);
//                    showRes("POST方法取回内容:" + resultData);
                } else {
                    Toast.makeText(MainActivity.this,"网络连接失败",Toast.LENGTH_SHORT).show();

                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void showRes(String res){
        Bundle bundle = new Bundle();
        bundle.putString("res",res);

        Message msg = handler.obtainMessage();
        msg.setData(bundle);
        handler.sendMessage(msg);
    }
    class MyHandler extends Handler {
        @Override
        //接收别的线程的信息并处理
        public void handleMessage(Message msg) {
        }
    }

    private void checkNetworkInfo(){
        ConnectivityManager conMan = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo.State moblie = conMan.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();

        //Toast.makeText(this,"mobile:+++++"+moblie.toString(),Toast.LENGTH_SHORT).show();

        //Wifi
        boolean wifi = (conMan.getActiveNetworkInfo().getType() ==  ConnectivityManager.TYPE_WIFI);
        Toast.makeText(this,"wifi:+++++"+ wifi,Toast.LENGTH_SHORT).toString();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == 1 && resultCode == 2){
            Toast.makeText(this,"account_num:"+data.getStringExtra("account_num"),Toast.LENGTH_SHORT).show();
        }
    }


//    public final boolean ping() {
//        String result = null;
//        try {
//            //String ip = "114.114.114.114";
//            Process p = Runtime.getRuntime().exec("ping -c 3 https://www.baidu.com");
//
//            InputStream input = p.getInputStream();
//            BufferedReader in = new BufferedReader(new InputStreamReader(input));
//            StringBuffer stringBuffer = new StringBuffer();
//            String content = "";
//            while ((content = in.readLine()) != null) {
//                stringBuffer.append(content);
//            }
//            Log.e("----ping----", "result content:" + stringBuffer.toString());
//
//            //ping的状态
//            int status = p.waitFor();
//            if (status == 0) {
//                result = "success";
//                return true;
//            } else {
//                result = "failed";
//                new PostThread().start();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } finally {
//            Log.e("result:", result);
//        }
//        return false;
//    }
//    private boolean disConnect() {
//        String ip = "http://192.168.171.254:8887/";
//
//        HttpURLConnection conn = null;
//        try {
//            URL url = new URL(ip);
//            conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod("POST");
//            conn.setReadTimeout(5000);
//            conn.setDoOutput(true);
//            conn.setDoInput(true);
//            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//            String data = "language=1&actionType=umlogout";
//            conn.setRequestProperty("Content-Length", String.valueOf(data.getBytes().length));
//
//            OutputStream outputStream = conn.getOutputStream();
//            outputStream.write(data.getBytes());
//            outputStream.close();
//            int code = conn.getResponseCode();
//            if (code != 200) {
//                Toast.makeText(this, "----连接已断开----" , Toast.LENGTH_SHORT).show();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return false;
//    }

}
