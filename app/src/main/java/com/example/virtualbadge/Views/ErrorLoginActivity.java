package com.example.virtualbadge.Views;




import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.virtualbadge.R;
import com.example.virtualbadge.Singleton;


public class ErrorLoginActivity extends AppCompatActivity {

    Button returnBtn;
    TextView errorMessage;
    Bundle intent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {




        super.onCreate(savedInstanceState);

        setContentView(R.layout.error_login);

        if(getIntent().getExtras() != null) {
            intent = getIntent().getExtras();
        }
        errorMessage = findViewById(R.id.loginId);
        errorMessage.setText(intent.getString("errorMessage"));
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
            if(Singleton.getUser() != null && Singleton.getUser().getLogin().equals("admin")) {
                startActivity(new Intent(ErrorLoginActivity.this, LeaderActivity.class));
            }
            else {
                Singleton.quit();
                startActivity(new Intent(ErrorLoginActivity.this, LoginActivity.class));
            }
        });
    }
}
