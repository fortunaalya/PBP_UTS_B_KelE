package com.tubes.pbp_uts_b_kelompoke;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.tubes.pbp_uts_b_kelompoke.databinding.AdapterRecyclerViewBinding;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private Context context;
    private List<Menu> result;

    public RecyclerViewAdapter(){}

    public RecyclerViewAdapter(Context context, List<Menu> result){
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdapterRecyclerViewBinding adapterRecyclerViewBinding = DataBindingUtil.inflate( LayoutInflater.from(parent.getContext()),
                R.layout.adapter_recycler_view, parent, false);
        //View v = LayoutInflater.from(context).inflate(R.layout.adapter_recycler_view, parent, false);
        //final MyViewHolder holder = new MyViewHolder(v);

        return new MyViewHolder(adapterRecyclerViewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final Menu mhs = result.get(position);
        holder.adapterRecyclerViewBinding.setMhs(mhs);
//        holder.npm.setText(mhs.getNpm());
//        holder.nama.setText(mhs.getNama());
//        holder.fakultas.setText(mhs.getFakultas());
//        holder.jurusan.setText(mhs.getJurusan());
//        holder.ipk.setText(String.valueOf(mhs.getIpk()));
//        holder.hobi.setText(mhs.getHobi());
//        Glide.with(context).load(mhs.imgURL).into(holder.foto_profil);
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        //private TextView npm, nama, fakultas, jurusan, ipk, hobi;
        //private ImageView foto_profil;
        //private CardView parent;
        private AdapterRecyclerViewBinding adapterRecyclerViewBinding;

        public MyViewHolder(@NonNull AdapterRecyclerViewBinding itemView){
            super(itemView.getRoot());
//            npm = itemView.findViewById(R.id.tvNpm);
//            nama = itemView.findViewById(R.id.tvNama);
//            fakultas = itemView.findViewById(R.id.tvFakultas);
//            jurusan = itemView.findViewById(R.id.tvJurusan);
//            ipk = itemView.findViewById(R.id.tvIpk);
//            hobi = itemView.findViewById(R.id.tvHobi);
//            foto_profil = itemView.findViewById(R.id.ivFotoProfil);
//            parent = itemView.findViewById(R.id.ParentAdapter);
            this.adapterRecyclerViewBinding = itemView;
        }
        public void onClick(View view) {
            Toast.makeText(context, "You touch me?", Toast.LENGTH_SHORT).show();
        }
    }
}