package com.tubes.pbp_uts_b_kelompoke;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.tubes.pbp_uts_b_kelompoke.MainActivity;
import com.tubes.pbp_uts_b_kelompoke.R;

public class Dashboard extends AppCompatActivity {

    private final int SPLASH_SCREEN_TIME = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Dashboard.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_SCREEN_TIME);
    }
}
