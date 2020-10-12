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
    private Button list, location, reservasi, bayar, profile, keluar;
    //private Button logout_button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mAuth = FirebaseAuth.getInstance();

        list = findViewById(R.id.btnmenu);
        location = findViewById(R.id.btnLocation);
        reservasi = findViewById(R.id.btnreservasi);
        bayar = findViewById(R.id.btnPayment);
        profile = findViewById(R.id.btnProfile);
        keluar = findViewById(R.id.btnLogout);

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(), MapsActivity.class));
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
            }
        });

        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(), MenuMakananActivity.class));
            }
        });

        bayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(), MenuMakananActivity.class));
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

       // reservasi.setOnClickListener(new View.OnClickListener() {
      //      @Override
        //    public void onClick(View view) {
         //       Intent intent = new Intent(MenuActivity.this, ReservasiActivity.class);
          //      startActivity(intent);
           // }
        //});

        keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }
}