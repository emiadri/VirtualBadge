package com.example.virtualbadge.Views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.virtualbadge.R;
import com.example.virtualbadge.Singleton;
import com.example.virtualbadge.dataBase.Employee;


public class LeaderActivity extends AppCompatActivity {

    Spinner typeOfAction;
    EditText loginEditText;
    EditText passwordEditText;
    EditText nameEditText;
    EditText surnameEditText;
    Button actionAdminBtn;
    Button returnBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        System.out.println("LeaderActivity");


        setContentView(R.layout.activity_leader);

        Singleton.getInstance(LeaderActivity.this);

        configureLoginButton();
        configureReturnButton();
        Log.d("SlothCal", "onCreate");

    }



    @Override
    protected void onStart(){
        super.onStart();
        Log.d("SlothCal", "onStart");
    }
    @Override
    protected  void onResume(){
        super.onResume();

        Log.d("SlothCal", "onResume");

    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.d("SlothCal", "onPause");
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d("SlothCal", "onDestroy");
    }
    @Override
    protected void onRestart(){
        super.onRestart();
        Log.d("SlothCal", "onRestart");
    }

    private void configureLoginButton(){
        typeOfAction = findViewById(R.id.typeOfEventId);
        actionAdminBtn = findViewById(R.id.actionAdminBtnId);
        actionAdminBtn.setOnClickListener(view -> {
            loginEditText = findViewById(R.id.loginInputId);
            passwordEditText = findViewById(R.id.passwordInputId);
            nameEditText = findViewById(R.id.firstNameInputId);
            surnameEditText = findViewById(R.id.surnameInputId);

            Employee employee = new Employee(loginEditText.getText().toString(),
                    passwordEditText.getText().toString(), nameEditText.getText().toString(),
                    surnameEditText.getText().toString());
            if(typeOfAction.getSelectedItem().toString().equals("dodaj")) {
                if(Singleton.addUser(employee)) {
                    Singleton.quit();
                    Intent intent = new Intent(LeaderActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(LeaderActivity.this, ErrorLoginActivity.class);
                    intent.putExtra("errorMessage", "wprowadziles niepelne dane, lub uzytkownik juz istnieje");
                    startActivity(intent);
                }
            }
            else{
                if(!employee.getLogin().equals("admin")) {
                    if (Singleton.deleteUser(employee.getLogin())) {
                        Singleton.quit();
                        Intent intent = new Intent(LeaderActivity.this, LoginActivity.class);
                        intent.putExtra("adminMessage", "usunieto");
                        startActivity(intent);
                    }
                    else {
                        Intent intent = new Intent(LeaderActivity.this, ErrorLoginActivity.class);
                        intent.putExtra("errorMessage", "wprowadziles niepoprawny login");
                        startActivity(intent);
                    }
                }
                else {
                    Intent intent = new Intent(LeaderActivity.this, ErrorLoginActivity.class);
                    intent.putExtra("errorMessage", "nie mozna usunac konta administratora!");
                    startActivity(intent);
                }
            }
        });
    }

    private void configureReturnButton(){
        returnBtn = findViewById(R.id.returnBtnId);
        returnBtn.setOnClickListener(view -> {
            Singleton.quit();
            startActivity(new Intent(LeaderActivity.this, LoginActivity.class));
        });
    }

}
