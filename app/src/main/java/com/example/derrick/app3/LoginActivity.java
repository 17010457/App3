package com.example.derrick.app3;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    TextView tvFashion;
    ImageView ivFashion;
    Button btnLogin;
    RelativeLayout rellay1, rellay2;
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            rellay1.setVisibility(View.VISIBLE);
            rellay2.setVisibility(View.VISIBLE);
            tvFashion.setVisibility(View.GONE);
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tvFashion = findViewById(R.id.tvFashion);
        ivFashion = findViewById(R.id.fashion_icon);
        btnLogin = findViewById(R.id.btnLogin);
        rellay1 = findViewById(R.id.rellay1);
        rellay2 = findViewById(R.id.rellay2);

        handler.postDelayed(runnable, 2000); //timeout for the

        if (tvFashion.getVisibility() == View.INVISIBLE) {
        }

    }
}
