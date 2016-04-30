package com.pingvini.mobilecooking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.Normalizer;
import java.util.Arrays;


public class LoginActivity extends AppCompatActivity {

    private Button btnFacebook;
    private String userNameText;
    private String emailId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            // Initializing Parse connection
            Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
                    .applicationId("pingvini")
                    .server("http://pingvini-mbcooking.rhcloud.com/parse/")
                    .build()
            );
        }
        catch (Exception e){
            e.printStackTrace();
        }

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
                                    Toast.makeText(getApplicationContext(),
                                            "Login canceled or no internet connection!", Toast.LENGTH_LONG)
                                            .show();
                                }
                                else if (user.isNew()){
                                    Log.d("MobileCooking","New user!");
                                    //dohvati podatke
                                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                }
                                else{
                                    Log.d("MobileCooking","Already connected!");
                                    startActivity(new Intent(getApplicationContext(), MainActivity.class));

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

    private void getUserDetailsFromParse(){
        ParseUser parseUser = ParseUser.getCurrentUser();
        userNameText = parseUser.getUsername();
        emailId = parseUser.getEmail();
        Toast.makeText(getApplicationContext(),
                "Welcome back " + userNameText + "!", Toast.LENGTH_LONG)
                .show();
    }

    private void getUsersDetailsFromFB()
    {
        GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback(){
            @Override
            public void onCompleted(JSONObject jsonObject, GraphResponse graphResponse){
                try {
                    userNameText = jsonObject.getString("name");
                } catch (JSONException e){
                    e.printStackTrace();
                }
                try{
                    emailId = jsonObject.getString("email");
                }catch (JSONException e){
                    e.printStackTrace();
                }
                saveNewUser();
            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields","name,email");
        request.setParameters(parameters);
        request.executeAsync();
    }

    private void saveNewUser() {
        ParseUser parseUser = ParseUser.getCurrentUser();
        parseUser.setUsername(userNameText);
        parseUser.setEmail(emailId);
        parseUser.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                Toast.makeText(getApplicationContext(),
                        "Thank you for signing up" + userNameText + "!", Toast.LENGTH_LONG)
                        .show();
            }
        });
    }
}
