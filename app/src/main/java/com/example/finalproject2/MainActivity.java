package com.example.finalproject2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    TextView tv;
    Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        tv = findViewById(R.id.textView);
        logout = findViewById(R.id.logoutbtn);
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            String userEmail = user.getEmail();
            tv.setText("Hi, " + userEmail);
        } else {
            // במידה ואין משתמש מחובר, נחזור למסך ההתחברות
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        }
      logout.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              HandleLogOut();
          }
      });

    };
    private void HandleLogOut() {
        mAuth.signOut(); // התנתקות מהמשתמש
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish(); // סיום הפעילות הנוכחית
    }

}

