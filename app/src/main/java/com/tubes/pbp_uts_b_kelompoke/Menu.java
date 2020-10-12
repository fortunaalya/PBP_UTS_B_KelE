package com.tubes.pbp_uts_b_kelompoke;

import android.widget.ImageView;

import androidx.databinding.BaseObservable;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

public class Menu extends BaseObservable {
    //public String npm;
    public String nama;
    //public String fakultas;
    //public String jurusan;
    public int harga;
    //public String hobi;
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
        //.npm = npm;
        this.nama = nama;
        //this.fakultas = fakultas;
        //this.jurusan = jurusan;
        this.harga = harga;
        //this.hobi = hobi;
        this.imgURL = imgURL;
    }

//    public String getNpm() {
//        return npm;
//    }
//
//    public void setNpm(String npm) {
//        this.npm = npm;
//    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

//    public String getFakultas() {
//        return fakultas;
//    }
//
//    public void setFakultas(String fakultas) {
//        this.fakultas = fakultas;
//    }
//
//    public String getJurusan() {
//        return jurusan;
//    }
//
//    public void setJurusan(String jurusan) {
//        this.jurusan = jurusan;
//    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

//    public String getHobi() {
//        return hobi;
//    }

    public String getStringHarga(){ return String.valueOf( harga ); }

    public void setStringHarga(String harga){
        if(!harga.isEmpty()){
            this.harga = Integer.parseInt(harga);
        }else{
            this.harga = 0;
        }
    }

//    public void setHobi(String hobi) {
//        this.hobi = hobi;
//    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }
}
