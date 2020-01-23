package com.example.virtualbadge;

import android.app.Activity;
import android.widget.EditText;

import com.example.virtualbadge.dataBase.Employee;
import com.example.virtualbadge.dataBase.MyDBHandler;

public class Singleton {
    // static variable single_instance of type Singleton
    private static Singleton single_instance = null;

    // variable of type String
    private static MyDBHandler dataManager;
    private static Employee employee = null;

    // private constructor restricted to this class itself
    private Singleton(Activity activity) {

        dataManager = new MyDBHandler(activity, null,
                null);
        EditText userInput = activity.findViewById(R.id.loginInputId);
        System.out.println(userInput.getText().toString());
        EditText passwordInput = activity.findViewById(R.id.passwordInputId);
        System.out.println(passwordInput.getText().toString());
        employee = dataManager.tryLogin(userInput.getText().toString(), passwordInput.getText().toString(), dataManager.getWritableDatabase());
    }

    // static method to create instance of Singleton class
    public static Singleton getInstance(Activity activity) {
        System.out.println("jestem tu");
        if (employee == null)
            single_instance = new Singleton(activity);

        return single_instance;
    }

    public static void quit(){
        single_instance = null;
        employee = null;
    }
    public static Employee getUser(){
        return employee;
    }

    public static Boolean addUser(Employee employee){
        System.out.println("dodaje usera");
        return dataManager.addHandler(employee, dataManager.getWritableDatabase());
    }

    public static Boolean deleteUser(String login){
        System.out.println("usuwam usera");
        return dataManager.deleteHandler(login, dataManager.getWritableDatabase());
    }

    public static MyDBHandler getDataManager(){
        return dataManager;
    }


}
