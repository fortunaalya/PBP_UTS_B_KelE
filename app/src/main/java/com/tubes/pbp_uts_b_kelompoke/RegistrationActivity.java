package com.tubes.pbp_uts_b_kelompoke;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText nama, email, alamat, no_telp, username, passwordnya;
    private TextView simpleText;
    private Button buttonSignup;
    private String CHANNEL_ID = "Channel 1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mAuth = FirebaseAuth.getInstance();

        nama = findViewById(R.id.txtfullname);
        email = findViewById(R.id.txtemail);
        no_telp = findViewById(R.id.txtphone);
        username = findViewById(R.id.txtusername);
        passwordnya = findViewById(R.id.txtpass);
        buttonSignup = findViewById(R.id.btn_register);
        simpleText = findViewById(R.id.simpleText);

        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callsignup(email.getText().toString().trim(), passwordnya.getText().toString().trim());
            }
        });

        simpleText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }

    private void callsignup(String email, final String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(RegistrationActivity.this, "Signed up Failed", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            FirebaseUser user = mAuth.getCurrentUser();
                            String UserID=user.getEmail().replace("@","").replace(".","");
                            DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();

                            DatabaseReference ref1= mRootRef.child("Users").child(UserID);

                            ref1.child("Name").setValue(nama.getText().toString().trim());
                            ref1.child("Email").setValue(user.getEmail());
                            ref1.child("No_Telepon").setValue(no_telp.getText().toString().trim());
                            ref1.child("Username").setValue(username.getText().toString().trim());
                            ref1.child("Password").setValue(passwordnya.getText().toString().trim());

                            createNotificationChannel ();
                            addSignInNotification ();

                            Log.d("TESTING", "Sign up Successful" + task.isSuccessful());
                            Toast.makeText(RegistrationActivity.this, "Account Created ", Toast.LENGTH_SHORT).show();
                            Log.d("TESTING", "Created Account");
                            mAuth.signOut();
                            finish();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }
                    }
                });
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Channel 1";
            String description = "This is Channel 1";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void addSignInNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Registration")
                .setContentText("Registration Complate")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        NotificationManager manager = (NotificationManager) getSystemService( Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }
}