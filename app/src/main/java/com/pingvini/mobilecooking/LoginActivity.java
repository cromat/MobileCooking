package com.pingvini.mobilecooking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;

import java.text.Normalizer;
import java.util.Arrays;


public class LoginActivity extends AppCompatActivity {

    private Button btnFacebook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ParseFacebookUtils.initialize(this);
        FacebookSdk.sdkInitialize(getApplicationContext());

        setContentView(R.layout.activity_login);

        btnFacebook = (Button) findViewById(R.id.fb_login_button);

        btnFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseFacebookUtils.logInWithReadPermissionsInBackground(
                        LoginActivity.this, Arrays.asList("email", "user_photos", "public_profile", "user_friends"),
                        new LogInCallback() {
                            @Override
                            public void done(ParseUser user, ParseException e) {
                                if (user == null){
                                    Log.d("MobileCooking","Login error! User canceled login!");
                                }
                                else if (user.isNew()){
                                    Log.d("MobileCooking","New user!");
                                    //dohvati podatke
                                }
                                else{
                                    Log.d("MobileCooking","Already connected!");
                                }
                            }
                        });
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ParseFacebookUtils.onActivityResult(requestCode, resultCode, data);
    }
}
