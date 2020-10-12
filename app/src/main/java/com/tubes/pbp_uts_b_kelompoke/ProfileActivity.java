package com.tubes.pbp_uts_b_kelompoke;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
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
    private Button buttonEdit, cancel;
    private String CHANNEL_ID = "Channel 1";
    private ImageButton back;
    private ImageView photo;
    private MaterialButton take_photo;
    private static final int PERMISSION_CODE = 100;
    private static final int IMAGE_CAPTURE_CODE = 101;
    private Uri imageUri;
    private TextView lay_username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mAuth = FirebaseAuth.getInstance();

        nama = findViewById(R.id.txtname);
        no_telp = findViewById(R.id.txtNoPhone);
        username = findViewById(R.id.txtuser);
        buttonEdit = findViewById(R.id.save);
        photo = (ImageView) findViewById(R.id.imgProfile);
        take_photo = (MaterialButton) findViewById(R.id.takePhoto);
        back = (ImageButton) findViewById(R.id.back);
        lay_username = findViewById(R.id.layout_user);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        getData();


        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editData(nama.getText().toString().trim(), username.getText().toString().trim(), no_telp.getText().toString().trim());
            }
        });

        cancel = findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                createNotificationChannel ();
                addSignOutNotification ();
                mAuth.signOut();
            }
        });

        take_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkSelfPermission(Manifest.permission.CAMERA)== PackageManager.PERMISSION_DENIED ||
                        checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED) {

                    String[] permission = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};

                    ActivityCompat.requestPermissions(ProfileActivity.this, permission, PERMISSION_CODE);

                }else if(checkSelfPermission(Manifest.permission.CAMERA)== PackageManager.PERMISSION_GRANTED ||
                        checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){

                    openCamera();

                }
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
                lay_username.setText(snapshot.child("Username").getValue().toString());
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == PERMISSION_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(ProfileActivity.this,
                        "Camera Permission Granted",
                        Toast.LENGTH_SHORT)
                        .show();
            }
            else {
                Toast.makeText(ProfileActivity.this,
                        "Camera Permission Denied",
                        Toast.LENGTH_SHORT)
                        .show();
            }
            if (grantResults.length > 0
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(ProfileActivity.this,
                        "Storage Permission Granted",
                        Toast.LENGTH_SHORT)
                        .show();
            }
            else {
                Toast.makeText(ProfileActivity.this,
                        "Storage Permission Denied",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }


    private void openCamera(){
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE,"New Picture");
        values.put(MediaStore.Images.Media.DESCRIPTION,"From tubes APP");
        imageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);

        //START CAMERA
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
        startActivityForResult(intent,IMAGE_CAPTURE_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            photo.setImageURI(imageUri);
        }
    }
}