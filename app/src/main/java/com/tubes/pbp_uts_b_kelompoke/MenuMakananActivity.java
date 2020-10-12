package com.tubes.pbp_uts_b_kelompoke;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MenuMakananActivity extends AppCompatActivity {
    private EditText value1, value2;
    private Button order_button, buttonPlus1, buttonPlus2, buttonMinus1, buttonMinus2; //logout_button;
    private FirebaseAuth mAuth;
    private ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_makanan);

        value1 = findViewById(R.id.value1);
        value2 = findViewById(R.id.value2);
        order_button = findViewById(R.id.order_button);
        buttonPlus1 = findViewById(R.id.buttonPlus1);
        buttonPlus2 = findViewById(R.id.buttonPlus2);
        buttonMinus1 = findViewById(R.id.buttonMinus1);
        buttonMinus2 = findViewById(R.id.buttonMinus2);
        mAuth = FirebaseAuth.getInstance();

        value1.setText("0");
        value2.setText("0");

        back = (ImageButton) findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuMakananActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        buttonPlus1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt(value1.getText().toString().trim());
                a = a + 1;
                value1.setText(String.valueOf(a));
            }
        });

        buttonMinus1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt(value1.getText().toString().trim());
                a = a - 1;
                if (a < 0){
                    a = 0;
                }
                value1.setText(String.valueOf(a));
            }
        });

        buttonPlus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt(value2.getText().toString().trim());
                a = a + 1;
                value2.setText(String.valueOf(a));
            }
        });

        buttonMinus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt(value2.getText().toString().trim());
                a = a - 1;
                if (a < 0){
                    a = 0;
                }
                value2.setText(String.valueOf(a));
            }
        });

//        logout_button = findViewById(R.id.logout_button);
//        logout_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//                startActivity(new Intent(getApplicationContext(), MainActivity.class));
//                mAuth.signOut();
//            }
//        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        startActivity(new Intent(getApplicationContext(), MenuActivity.class));
    }
}