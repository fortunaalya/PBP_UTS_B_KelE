package com.tubes.pbp_uts_b_kelompoke;

import java.util.ArrayList;

public class DaftarMenu {
    public ArrayList<Menu> Menu;

    public DaftarMenu(){
        Menu = new ArrayList ();
        Menu.add( AYAM );
        Menu.add( MIE );
        Menu.add( NASI );
        Menu.add( TEH );
        Menu.add( JUS );
    }

public static final Menu AYAM = new Menu ("Ayam Goreng",20000,
        "https://akcdn.detik.net.id/visual/2019/04/04/dc2fdf24-1756-4fd8-807a-dd68b201656c_169.jpeg?w=360&q=90");

public static final Menu MIE = new Menu ("Mie Goreng", 15000,
        "https://www.chilibeli.com/uploads/blog/resep-olahan-cara-membuat-mie-goreng.jpg");

public static final Menu NASI = new Menu ("Nasi Goreng", 10000,
        "https://blue.kumparan.com/image/upload/fl_progressive,fl_lossy,c_fill,q_auto:best,w_640/v1515557737/asxtrr2ga1os4abfmuoe.jpg");

public static final Menu TEH = new Menu ("Teh Manis", 4000,
        "https://pulalohome.com/tm_images/article/8a6b145f4b5ed32b.jpg");

public static final Menu JUS = new Menu ("Jus Jeruk", 8000,
        "https://img.okezone.com/content/2016/11/10/481/1537676/9-manfaat-minum-jus-jeruk-setiap-pagi-untuk-kesehatan-A77easiQz2.jpg");
}
