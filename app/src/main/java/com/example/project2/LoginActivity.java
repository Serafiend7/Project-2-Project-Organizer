package com.example.project2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project2.database.entities.UserID;
import com.example.project2.database.UserIDRepository;
import com.example.project2.databinding.LoginPageBinding;

public class LoginActivity extends AppCompatActivity {

    LoginPageBinding binding;

    private UserIDRepository repository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = LoginPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = UserIDRepository.getRepository(getApplication());
        binding.LoginButton.setOnClickListener(v -> {
            String username = binding.EnterUsernameEditTextNumberSigned.getText().toString();
            String password= binding.EnterPasswordEditTextNumberSigned.getText().toString();

            if (!checkUserID(username, password)) {
                Toast.makeText(LoginActivity.this, "Incorrect username or password", Toast.LENGTH_SHORT).show();
            }
            else {
                UserID user = repository.getUserByUserName(username);
                SharedPreferences preferences = getApplicationContext().getSharedPreferences(LandingPage.SHARED_PREFERENCE_USERID_KEY,Context.MODE_PRIVATE);
                SharedPreferences.Editor preferencesEditor = preferences.edit();
                preferencesEditor.putInt(LandingPage.SHARED_PREFERENCE_USERID_KEY, user.getId());
                preferencesEditor.apply();

                startActivity(LandingPage.landingPageActivityIntentFactory(getApplicationContext(),user.getId()));
                if (checkAdminStatus(username)){
                    startActivity(new Intent(LoginActivity.this, AdminLandingPage.class));
                }
            }
        });
    }



    private boolean checkUserID(String username, String password){

        UserID userId = repository.getUserByUserName(username);

        if (username != null && userId != null){
            return password.equals(userId.getPassword());
        }
        return false;
    }

    private boolean checkAdminStatus(String username){
        UserID userId = repository.getUserByUserName(username);
        assert userId != null;
        return userId.isAdmin();
    }

    public static Intent loginPageActivityIntentFactory(Context context) {
        return new Intent(context, LoginActivity.class);
    }

}
