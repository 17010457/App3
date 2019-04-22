package com.example.derrick.app3;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Currency;


public class LoginActivity extends AppCompatActivity{

    TextView tvStatus;
    TextView tvFashion;
    ImageView ivFashion;
    Button btnLogin;
    Button btnSignOut;
    LoginButton btnLoginFacebook;
    String email;
    String password;
    EditText etEmail;
    EditText etPassword;
    CallbackManager callbackManager;
    RelativeLayout rellay1, rellay2;
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            rellay1.setVisibility(View.VISIBLE);
            rellay2.setVisibility(View.VISIBLE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tvStatus = findViewById(R.id.status);
        btnSignOut = findViewById(R.id.btnSignout);
        tvFashion = findViewById(R.id.tvFashion);
        ivFashion = findViewById(R.id.fashion_icon);
        btnLogin = findViewById(R.id.btnLogin);
        btnLoginFacebook = findViewById(R.id.facebook_login);
        rellay1 = findViewById(R.id.rellay1);
        rellay2 = findViewById(R.id.rellay2);
        etEmail = findViewById(R.id.etemail);
        etPassword = findViewById(R.id.etpassword);

        handler.postDelayed(runnable, 1000); //timeout for the

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this, IndexActivity.class);
                startActivity(intent);
            }
        });

        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logOut();
                btnLoginFacebook.setVisibility(View.VISIBLE);
            }
        });

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        callbackManager = CallbackManager.Factory.create();

        final String EMAIL = "email";

        btnLoginFacebook.setReadPermissions(Arrays.asList(EMAIL));
        // If you are using in a fragment, call loginButton.setFragment(this);

        // Callback registration
        btnLoginFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                Intent intent = new Intent(LoginActivity.this, IndexActivity.class);
                startActivity(intent);
                btnLoginFacebook.setVisibility(View.GONE);
                btnSignOut.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancel() {
                LoginManager.getInstance().logOut();
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                tvStatus.setText("ERROR");
            }

        });

        //Validation
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

}
