package com.tubes.pbp_uts_b_kelompoke;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.NotificationCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.auth.FirebaseAuth;

public class MenuActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private CardView menuLocation, menuProfile, menuMenu, menuPayment;
    //private Button logout_button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mAuth = FirebaseAuth.getInstance();

        menuLocation = findViewById(R.id.menuLocation);
        menuProfile = findViewById(R.id.menuProfile);
        menuMenu = findViewById(R.id.menuMenu);
        menuPayment = findViewById(R.id.menuPayment);
        //logout_button = findViewById(R.id.logout_button);

        menuLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(), MapsActivity.class));
            }
        });

        menuProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
            }
        });

        menuMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(), MenuMakananActivity.class));
            }
        });

        menuPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(), MenuMakananActivity.class));
            }
        });

//        logout_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mAuth.signOut();
//                finish();
//                startActivity(new Intent(getApplicationContext(), MainActivity.class));
//            }
//        });
    }
}