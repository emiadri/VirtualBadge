package com.example.virtualbadge.Views;




import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.virtualbadge.R;
import com.example.virtualbadge.Singleton;


public class EmployeeActivity extends AppCompatActivity {

    Button returnBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);

        setContentView(R.layout.employee_activity);

        TextView loginText = findViewById(R.id.loginId);
        TextView firstNameText = findViewById(R.id.nameId);
        TextView surNameText = findViewById(R.id.surNameId);
        Button returnBtn;

        loginText.setText("login: " + Singleton.getUser().getLogin());
        firstNameText.setText("imie: " + Singleton.getUser().getName());
        surNameText.setText("nazwisko: " + Singleton.getUser().getSurname());


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

    private void configureReturnButton(){
        returnBtn = findViewById(R.id.returnBtnId);
        returnBtn.setOnClickListener(view -> {
            Singleton.quit();
            startActivity(new Intent(EmployeeActivity.this, LoginActivity.class));
        });
    }
}
