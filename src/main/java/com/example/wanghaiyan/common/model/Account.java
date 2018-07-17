package com.example.wanghaiyan.common.model;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private String accountName = "";
    private String accountPassword = "";
    //SingletonInstance
    public static Account getInstance() {return SingleTonHolder.singletonInstance;}
    private static class SingleTonHolder {
        private static final Account singletonInstance = new Account();
    }


    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountPassword() {
        return accountPassword;
    }

    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
    }

    public Account(){}

    public Account(String accountName,String accountPassword){
        this.accountName = accountName;
        this.accountPassword = accountPassword;
    }

}
