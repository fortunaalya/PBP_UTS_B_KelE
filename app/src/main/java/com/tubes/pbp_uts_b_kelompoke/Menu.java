package com.tubes.pbp_uts_b_kelompoke;

import android.widget.ImageView;

import androidx.databinding.BaseObservable;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

public class Menu extends BaseObservable {
    public String nama;
    public int harga;
    public String imgURL;

    public Menu() {
    }

    @BindingAdapter({"load_image"})
    public static void loadImage(ImageView view, String imgURL){
        Glide.with(view).load(imgURL).into(view);
        if ((!imgURL.isEmpty())){
            Glide.with(view.getContext())
                    .load(imgURL)
                    .into(view);
        }
        else {
            view.setImageDrawable(view.getContext().getDrawable(R.drawable.ic_broken_image));
        }
    }


    public Menu(String nama, int harga, String imgURL) {
        this.nama = nama;
        this.harga = harga;
        this.imgURL = imgURL;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public String getStringHarga(){ return String.valueOf( harga ); }

    public void setStringHarga(String harga){
        if(!harga.isEmpty()){
            this.harga = Integer.parseInt(harga);
        }else{
            this.harga = 0;
        }
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }
}
