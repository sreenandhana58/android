package com.example.shared;

import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText usernameEt, mobileEt, emailEt, pass1Et, pass2Et;
    Button submitBtn;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameEt = findViewById(R.id.username);
        mobileEt = findViewById(R.id.mobile);
        emailEt = findViewById(R.id.email); // <-- Updated ID
        pass1Et = findViewById(R.id.password);
        pass2Et = findViewById(R.id.repass);
        submitBtn = findViewById(R.id.submit);

        sharedPreferences = getSharedPreferences("UserDetails", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEt.getText().toString().trim();
                String mobile = mobileEt.getText().toString().trim(); // <- Changed to String
                String email = emailEt.getText().toString().trim();
                String pass1 = pass1Et.getText().toString().trim();
                String pass2 = pass2Et.getText().toString().trim();

                if (username.isEmpty()) {
                    usernameEt.setError("Username is empty");
                    usernameEt.requestFocus();
                    return;
                }

                if (mobile.isEmpty()) {
                    mobileEt.setError("Mobile number is required");
                    mobileEt.requestFocus();
                    return;
                }

                if (mobile.length() != 10) {
                    mobileEt.setError("Enter a valid 10-digit mobile number");
                    mobileEt.requestFocus();
                    return;
                }

                if (email.isEmpty()) {
                    emailEt.setError("Email is empty");
                    emailEt.requestFocus();
                    return;
                }

                if (pass1.isEmpty()) {
                    pass1Et.setError("Enter password");
                    pass1Et.requestFocus();
                    return;
                }

                if (pass1.length() < 6) {
                    pass1Et.setError("Password must be at least 6 characters");
                    pass1Et.requestFocus();
                    return;
                }

                if (!pass2.equals(pass1)) {
                    pass2Et.setError("Passwords do not match");
                    pass2Et.requestFocus();
                    return;
                }

                // Save data
                editor.putString("keyusername", username);
                editor.putString("keymobile", mobile);
                editor.putString("keymail", email);
                editor.putString("keypassword", pass2);
                editor.apply();

                Toast.makeText(MainActivity.this, "Registration successful", Toast.LENGTH_LONG).show();
            }
        });
    }
}
