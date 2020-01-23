package com.example.virtualbadge.Views;



import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.virtualbadge.R;
import com.example.virtualbadge.Singleton;


public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    Bundle intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);

        setContentView(R.layout.login_view);

        if(getIntent().getExtras() != null) {
            intent = getIntent().getExtras();
            alertDialog();
        }

        configureLoginButton();

        Log.d("SlothCal", "onCreate");

    }

    private void configureLoginButton(){
        btnLogin = findViewById(R.id.btnLoginId);
        btnLogin.setOnClickListener(view -> {

            Singleton.getInstance(LoginActivity.this);
            if(Singleton.getUser() == null) {
                Intent intent = new Intent(LoginActivity.this, ErrorLoginActivity.class);
                intent.putExtra("errorMessage", "wprowadziles niepelne dane, lub uzytkownik nie istnieje");
                startActivity(intent);
                System.out.println("blad logowania");
            }
            else if(Singleton.getUser().getLogin().equals("admin")){
                startActivity(new Intent(LoginActivity.this, LeaderActivity.class));
            }
            else if(Singleton.getUser() != null){
                startActivity(new Intent(LoginActivity.this, EmployeeActivity.class));
            }
        });
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

    private void alertDialog(){
        AlertDialog alertDialog = new AlertDialog.Builder(LoginActivity.this).create();
        alertDialog.setMessage(intent.getString("adminMessage"));
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                (dialog, which) -> dialog.dismiss());
        alertDialog.show();
    }

}
