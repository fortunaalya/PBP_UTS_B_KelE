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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText nama, no_telp, username;
    private Button buttonEdit, logout_button;
    private String CHANNEL_ID = "Channel 1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mAuth = FirebaseAuth.getInstance();

        nama = findViewById(R.id.editTextNama);
        no_telp = findViewById(R.id.editTextNoTelp);
        username = findViewById(R.id.editTextUsername);
        buttonEdit = findViewById(R.id.edit_button);

        getData();

        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editData(nama.getText().toString().trim(), username.getText().toString().trim(), no_telp.getText().toString().trim());
            }
        });

        logout_button = findViewById(R.id.logout_button);
        logout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                createNotificationChannel ();
                addSignOutNotification ();
                mAuth.signOut();
            }
        });
    }

    private void editData(final String nama, final String username, final String no_telp) {
        FirebaseUser user = mAuth.getCurrentUser();
        String UserID=user.getEmail().replace("@","").replace(".","");
        DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();

        DatabaseReference ref1= mRootRef.child("Users").child(UserID);

        ref1.child("Name").setValue(nama);
        ref1.child("No_Telepon").setValue(no_telp);
        ref1.child("Username").setValue(username);
        Toast.makeText(ProfileActivity.this, "Account Updated ", Toast.LENGTH_SHORT).show();
    }

    private void getData(){
        FirebaseUser user = mAuth.getCurrentUser();
        String UserID=user.getEmail().replace("@","").replace(".","");
        DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();

        DatabaseReference ref1= mRootRef.child("Users").child(UserID);

        ref1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                nama.setText(snapshot.child("Name").getValue().toString());
                no_telp.setText(snapshot.child("No_Telepon").getValue().toString());
                username.setText(snapshot.child("Username").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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

    private void addSignOutNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Goodbye")
                .setContentText("Comeback Again...")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        NotificationManager manager = (NotificationManager) getSystemService( Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        startActivity(new Intent(getApplicationContext(), MenuActivity.class));
    }
}