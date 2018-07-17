package com.example.wanghaiyan.myfirstapp;

public class TestActivity {

    public static int a = 5;
    public static int temp = 1;
    public static void main(String[] args) {
        aMethod(a);
    }

    public TestActivity() {}

    public static void aMethod(int a) {

        if(a>0){
            temp = temp * a;
            System.out.println(temp);
            a--;
            aMethod(a);
        }
    }


}


