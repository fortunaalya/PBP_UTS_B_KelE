package com.tubes.pbp_uts_b_kelompoke;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.tubes.pbp_uts_b_kelompoke.databinding.ActivityMenuMakananBinding;
import java.util.ArrayList;

public class MenuMakananActivity extends AppCompatActivity {
    private ArrayList<Menu> listMenu;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMenuMakananBinding activityMenuMakananBinding = DataBindingUtil.setContentView(this, R.layout.activity_menu_makanan);
        //setContentView(R.layout.activity_main);

        //get data mahasiswa
        listMenu = new DaftarMenu ().Menu;

        //recycler view
        //recyclerView = findViewById(R.id.recycler_view_mahasiswa);
        adapter = new RecyclerViewAdapter(MenuMakananActivity.this, listMenu );
        activityMenuMakananBinding.setMahasiswa(adapter);
        //activityMainBinding.setMahasiswa(adapter);
        //mLayoutManager = new LinearLayoutManager(getApplicationContext());
        //recyclerView.setLayoutManager(mLayoutManager);
        //recyclerView.setItemAnimator(new DefaultItemAnimator());
        //recyclerView.setAdapter(adapter);
    }
}