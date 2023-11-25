package com.example.drivercarpaking.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.drivercarpaking.R;
import com.example.drivercarpaking.api.API;
import com.example.drivercarpaking.models.AuthResponse;
import com.example.drivercarpaking.models.LoginRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText login_phonenumber;
    private EditText login_password;
    private Button login_button;

    private void init(){
        login_phonenumber = findViewById(R.id.login_phonenumber);
        login_password = findViewById(R.id.login_password);
        login_button = findViewById(R.id.login_button);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();


        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber = login_phonenumber.getText().toString();
                String password = login_password.getText().toString();

                LoginRequest loginRequest = new LoginRequest();
                loginRequest.setPhone(phoneNumber);
                loginRequest.setPassword(password);

                API.apiDriverTrip.login(loginRequest).enqueue(new Callback<AuthResponse>() {
                    @Override
                    public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                        if (response.isSuccessful()) {
                            AuthResponse authResponse = response.body();
                            saveAuthData(authResponse.getAccess_token(), authResponse.getPhone());
                            Intent intent = new Intent(LoginActivity.this, ListDriverTripActivity.class);
                            intent.putExtra("ACCESS_TOKEN", authResponse.getAccess_token());
                            intent.putExtra("PHONE_NUMBER", authResponse.getPhone());
                            startActivity(intent);
                            // Handle the authentication response, e.g., save tokens, navigate to the next screen
                        } else {
                            Toast.makeText(LoginActivity.this, "Login Falied", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<AuthResponse> call, Throwable t) {
                        Log.e("LoginActivity", "Login failed: " + t.getMessage(), t);
                        Toast.makeText(LoginActivity.this, "Login Falied", Toast.LENGTH_SHORT).show();

                    }
                });


            }
        });
    }

    private void saveAuthData(String accessToken, String phoneNumber) {
        // Get SharedPreferences instance
        SharedPreferences preferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);

        // Get SharedPreferences editor
        SharedPreferences.Editor editor = preferences.edit();

        // Save the access token and phone number
        editor.putString("access_token", accessToken);
        editor.putString("phone_number", phoneNumber);

        // Apply changes
        editor.apply();
    }

}