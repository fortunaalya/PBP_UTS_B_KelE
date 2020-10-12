package com.tubes.pbp_uts_b_kelompoke;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

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

        listMenu = new DaftarMenu ().Menu;

        adapter = new RecyclerViewAdapter(MenuMakananActivity.this, listMenu );
        activityMenuMakananBinding.setMahasiswa(adapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        startActivity(new Intent(getApplicationContext(), MenuActivity.class));
    }
}