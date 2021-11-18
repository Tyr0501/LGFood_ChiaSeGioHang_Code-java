package com.example.lgfood_duan1.Activity;import androidx.annotation.NonNull;import androidx.annotation.Nullable;import androidx.appcompat.app.ActionBarDrawerToggle;import androidx.appcompat.app.AppCompatActivity;import androidx.core.view.GravityCompat;import androidx.drawerlayout.widget.DrawerLayout;import androidx.recyclerview.widget.DefaultItemAnimator;import androidx.recyclerview.widget.GridLayoutManager;import androidx.recyclerview.widget.LinearLayoutManager;import androidx.recyclerview.widget.RecyclerView;import android.app.Dialog;import android.content.Intent;import android.graphics.Color;import android.graphics.drawable.ColorDrawable;import android.os.Bundle;import android.util.Log;import android.view.Gravity;import android.view.Menu;import android.view.MenuItem;import android.view.View;import android.view.ViewGroup;import android.view.Window;import android.view.animation.Animation;import android.view.animation.AnimationUtils;import android.widget.Button;import android.widget.FrameLayout;import android.widget.ImageView;import android.widget.LinearLayout;import android.widget.TextView;import android.widget.Toast;import android.widget.Toolbar;import com.bumptech.glide.Glide;import com.example.lgfood_duan1.Adapter.trangChu_showDoc_adapter;import com.example.lgfood_duan1.Adapter.trangChu_showNgang_adapter;import com.example.lgfood_duan1.Model.model_Account;import com.example.lgfood_duan1.Model.model_Cart;import com.example.lgfood_duan1.Model.model_SanPham;import com.example.lgfood_duan1.R;import com.google.android.material.navigation.NavigationView;import com.google.firebase.database.ChildEventListener;import com.google.firebase.database.DataSnapshot;import com.google.firebase.database.DatabaseError;import com.google.firebase.database.DatabaseReference;import com.google.firebase.database.FirebaseDatabase;import com.google.firebase.database.ValueEventListener;import java.util.ArrayList;import java.util.UUID;public class trangChu_SanPham_Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {    private DrawerLayout            TrangChuSanPham_drawerllout_main;    private NavigationView            TrangChuSanPham_nav_drawer;    private ImageView            TrangChuSanPham_img_showMenu,            TrangChuSanPham_img_btn_thongBao,            TrangChuSanPham_img_btn_kieuLoaiSanPham,            TrangChuSanPham_img_showLoaiCoffee,            TrangChuSanPham_img_showLoaiThaoDuoc,            TrangChuSanPham_img_showLoaiHat,            TrangChuSanPham_img_showLoaiMuc,            TrangChuSanPham_img_showLoaiTra,            DatNhanh_img_showAnhSanPham,            DatNhanh_img_btn_giamSoLuongSanPham,            DatNhanh_img_btn_tangSoLuongSanPham;    private TextView            TrangChuSanPham_tv_soLuongThongBao,            TrangChuSanPham_tv_showTatCaLoaiSanpham,            TrangChuSanPham_tv_showLoai,            DatNhanh_tv_xuatXuSanPham,            DatNhanh_tv_showTenSanPham,            DatNhanh_tv_giaSanPham,            DatNhanh_tv_giamGiaSanPham,            DatNhanh_tv_ngaySanXuat,            DatNhanh_tv_soLuongSanPhamTrongKho,            DatNhanh_tv_moTaSanPham,            DatNhanh_tv_SoLuongSanpham,            DatNhanh_tv_soLuongSanPhamYeuThichDaMua;    private RecyclerView            TrangChuSanPham_rscV_showSanPhamNgang,            TrangChuSanPham_rscV_showSanPhamDoc;    private FrameLayout            DatNhanh_FmLt_showChiTietSanPham;    private Button            DatNhanh_btn_themSanPhamVaoGioHang;    //Firebase    private DatabaseReference dataSanPhamRef;    private FirebaseDatabase dataSanPham;    //    Model    ArrayList<model_SanPham> arrListSanPham;    model_SanPham arrSanPham;    // adapter    trangChu_showDoc_adapter TrangChu_showDoc_adapter;    trangChu_showNgang_adapter TrangChu_showNgang_adapter;//biến số lượng và id giỏ hàng    int i=1;    String idGioHang;    @Override    protected void onCreate(Bundle savedInstanceState) {        super.onCreate(savedInstanceState);        setContentView(R.layout.activity_trang_chu_san_pham);        anhXa();        NavigationDrawer();        getDataFirebase();        showListProduc_Vartical();        showListProduc_Horizoltal();    }//    Trung Show sản phẩm Adapter    /********************Show thông tin ra kiểu ngang**********************/    private void showListProduc_Horizoltal() {        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(trangChu_SanPham_Activity.this, LinearLayoutManager.HORIZONTAL, false);        TrangChuSanPham_rscV_showSanPhamNgang.setLayoutManager(linearLayoutManager);        TrangChuSanPham_rscV_showSanPhamNgang.setItemAnimator(new DefaultItemAnimator());//        Initilize        TrangChu_showNgang_adapter = new trangChu_showNgang_adapter(arrListSanPham, trangChu_SanPham_Activity.this);        TrangChuSanPham_rscV_showSanPhamNgang.setAdapter(TrangChu_showNgang_adapter);    }    /********************Show thông tin ra kiểu dọc**********************/    private void showListProduc_Vartical() {        TrangChuSanPham_rscV_showSanPhamDoc.setLayoutManager(new GridLayoutManager(this, 2));        TrangChuSanPham_rscV_showSanPhamDoc.setItemAnimator(new DefaultItemAnimator());        //        Initilize        TrangChu_showDoc_adapter = new trangChu_showDoc_adapter(arrListSanPham, trangChu_SanPham_Activity.this, new trangChu_showDoc_adapter.IClickListener() {           //thai:onCLickItemShowChiTietSanPham            @Override            public void onClickShowItem(model_SanPham sanPham) {                showItemChiTietSanPham(sanPham);            }        });        TrangChuSanPham_rscV_showSanPhamDoc.setAdapter(TrangChu_showDoc_adapter);    }//thai:onClickItemSanPham    private void showItemChiTietSanPham(model_SanPham sanPham) {        final Dialog dialog = new Dialog(this);        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);        dialog.setContentView(R.layout.layout_dat_nhanh);        LinearLayout datNhanh_linear_turnOffDiaLog=dialog.findViewById(R.id.datNhanh_linear_turnOffDiaLog);       //thong tin san pham        ImageView datNhanh_img_showAnhSanPham=dialog.findViewById(R.id.datNhanh_img_showAnhSanPham);        TextView datNhanh_tv_xuatXuSanPham=dialog.findViewById(R.id.datNhanh_tv_xuatXuSanPham);        TextView datNhanh_tv_showTenSanPham=dialog.findViewById(R.id.datNhanh_tv_showTenSanPham);        TextView datNhanh_tv_giaSanPham=dialog.findViewById(R.id.datNhanh_tv_giaSanPham);        TextView datNhanh_tv_giamGiaSanPham=dialog.findViewById(R.id.datNhanh_tv_giamGiaSanPham);        TextView datNhanh_tv_ngaySanXuat=dialog.findViewById(R.id.datNhanh_tv_ngaySanXuat);        TextView datNhanh_tv_soLuongSanPhamTrongKho=dialog.findViewById(R.id.datNhanh_tv_soLuongSanPhamTrongKho);        TextView datNhanh_tv_soLuongSanPhamYeuThichDaMua=dialog.findViewById(R.id.datNhanh_tv_soLuongSanPhamYeuThichDaMua);        TextView datNhanh_tv_moTaSanPham=dialog.findViewById(R.id.datNhanh_tv_moTaSanPham);       //tang giam so luong sp        ImageView datNhanh_img_btn_giamSoLuongSanPham=dialog.findViewById(R.id.datNhanh_img_btn_giamSoLuongSanPham);        TextView datNhanh_tv_SoLuongSanpham=dialog.findViewById(R.id.datNhanh_tv_SoLuongSanpham);        ImageView datNhanh_img_btn_tangSoLuongSanPham=dialog.findViewById(R.id.datNhanh_img_btn_tangSoLuongSanPham);        Button datNhanh_btn_themSanPhamVaoGioHang=dialog.findViewById(R.id.datNhanh_btn_themSanPhamVaoGioHang);        Glide.with(trangChu_SanPham_Activity.this)                .load(sanPham.getAnhSanPham())                .into(datNhanh_img_showAnhSanPham);        datNhanh_tv_xuatXuSanPham.setText(sanPham.getXuatXuSanPham());        datNhanh_tv_showTenSanPham.setText(sanPham.getTenSanPham());        datNhanh_tv_giaSanPham.setText(sanPham.getGiaBanSanPham()+"00đ");        datNhanh_tv_giamGiaSanPham.setText("   -"+sanPham.getGiamGiaSanPham());        datNhanh_tv_ngaySanXuat.setText(sanPham.getNgaySanXuatSanPham());        datNhanh_tv_soLuongSanPhamTrongKho.setText(String.valueOf(sanPham.getSoLuongSanPham()));//        datNhanh_tv_soLuongSanPhamYeuThichDaMua.setText(Integer.valueOf(sanPham.getAnhSanPham()));        datNhanh_tv_moTaSanPham.setText(sanPham.getMoTaSanPham());        //giam so luong san pham        datNhanh_img_btn_giamSoLuongSanPham.setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View v) {                i--;                if (i<=1){                    i=1;                    datNhanh_tv_SoLuongSanpham.setText(String.valueOf(i));                    datNhanh_btn_themSanPhamVaoGioHang.setText(String.valueOf(i*sanPham.getGiaBanSanPham()));return;                }else {                    datNhanh_tv_SoLuongSanpham.setText(String.valueOf(i));                    datNhanh_btn_themSanPhamVaoGioHang.setText(String.valueOf(i*sanPham.getGiaBanSanPham()));                }            }        });        //tang so luong san pham        datNhanh_img_btn_tangSoLuongSanPham.setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View v) {                i++;                datNhanh_tv_SoLuongSanpham.setText(String.valueOf(i));                datNhanh_btn_themSanPhamVaoGioHang.setText(String.valueOf(i*sanPham.getGiaBanSanPham()));            }        });        //turn off dialog        datNhanh_linear_turnOffDiaLog.setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View v) {                dialog.dismiss();                i=1;            }        });        //add to cart        datNhanh_btn_themSanPhamVaoGioHang.setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View v) {                String idProduct=sanPham.getIdSanPham();                dataSanPhamRef = FirebaseDatabase.getInstance("https://duan1-lgfood-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("Accounts");                dataSanPhamRef.addValueEventListener(new ValueEventListener() {                    @Override                    public void onDataChange(@NonNull DataSnapshot snapshot) {                        for (DataSnapshot dataSnapshot:snapshot.getChildren()){                            model_Account account=dataSnapshot.getValue(model_Account.class);                            idGioHang=String.valueOf(account.getIdGioHang());                        }                        UUID uuid=UUID.randomUUID();                        String idChiTietSanPham=String.valueOf(uuid);                        model_Cart cart=new model_Cart(idChiTietSanPham,idProduct,i+"");                        dataSanPhamRef=dataSanPham.getReference("GioHangs");                        dataSanPhamRef.child(idGioHang).child(idChiTietSanPham).setValue(cart);                    }                    @Override                    public void onCancelled(@NonNull DatabaseError error) {                    }                });            }        });        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));        dialog.getWindow().getAttributes().windowAnimations=R.style.DialogAnimation;        dialog.getWindow().setGravity(Gravity.BOTTOM);        dialog.show();    }    //Trung: lấy dữ liệu sản phẩm trên firebase về    private void getDataFirebase() {        dataSanPhamRef = dataSanPham.getReference().child("khoHang");        dataSanPhamRef.addValueEventListener(new ValueEventListener() {            @Override            public void onDataChange(@NonNull DataSnapshot snapshot) {                arrListSanPham.clear();//                arrListSanPham.clear();//                Chạy vòng lặp kiểm tra dữ liệu                for (DataSnapshot child : snapshot.getChildren()) {                    arrSanPham = child.getValue(model_SanPham.class);                    arrListSanPham.add(arrSanPham);                }                TrangChu_showDoc_adapter.notifyDataSetChanged();                TrangChu_showNgang_adapter.notifyDataSetChanged();            }            @Override            public void onCancelled(DatabaseError error) {            }        });    }    //    Toàn: show menu Drawer    private void NavigationDrawer() {        /*           NavigationView Drawer Menu           */        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, TrangChuSanPham_drawerllout_main, R.string.navigation_drawer_open, R.string.navigation_drawer_close);        TrangChuSanPham_drawerllout_main.addDrawerListener(toggle);        toggle.syncState();        TrangChuSanPham_nav_drawer.setNavigationItemSelectedListener(this);        TrangChuSanPham_nav_drawer.bringToFront();        TrangChuSanPham_nav_drawer.setCheckedItem(R.id.drawer_nav_login);        /*           Phần quyền đăng nhập           *///        Menu menu = TrangChuSanPham_nav_drawer.getMenu();//        menu.findItem(R.id.drawer_nav_logout).setVisible(false);//        menu.findItem(R.id.drawer_nav_profile).setVisible(false);        TrangChuSanPham_img_showMenu.setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View v) {                if (TrangChuSanPham_drawerllout_main.isDrawerVisible(GravityCompat.START))                    TrangChuSanPham_drawerllout_main.closeDrawer(GravityCompat.START);                else TrangChuSanPham_drawerllout_main.openDrawer(GravityCompat.START);            }        });    }    @Override    public void onBackPressed() {        if (TrangChuSanPham_drawerllout_main.isDrawerVisible(GravityCompat.START)) {            TrangChuSanPham_drawerllout_main.closeDrawer(GravityCompat.START);        } else {            super.onBackPressed();        }    }    //    Bắt sự kiện buttom    private void batSuKien() {    }    //     Ánh xạ    private void anhXa() {//        model        arrListSanPham = new ArrayList<model_SanPham>();        //Firebase        dataSanPham = FirebaseDatabase.getInstance("https://duan1-lgfood-default-rtdb.asia-southeast1.firebasedatabase.app");        //      ImageView        TrangChuSanPham_img_showMenu = findViewById(R.id.trangChuSanPham_img_showMenu);        TrangChuSanPham_img_btn_thongBao = findViewById(R.id.trangChuSanPham_img_btn_thongBao);        TrangChuSanPham_img_btn_kieuLoaiSanPham = findViewById(R.id.trangChuSanPham_img_btn_kieuLoaiSanPham);        TrangChuSanPham_img_showLoaiCoffee = findViewById(R.id.trangChuSanPham_img_showLoaiCoffee);        TrangChuSanPham_img_showLoaiThaoDuoc = findViewById(R.id.trangChuSanPham_img_showLoaiThaoDuoc);        TrangChuSanPham_img_showLoaiHat = findViewById(R.id.trangChuSanPham_img_showLoaiHat);        TrangChuSanPham_img_showLoaiMuc = findViewById(R.id.trangChuSanPham_img_showLoaiMuc);        TrangChuSanPham_img_showLoaiTra = findViewById(R.id.trangChuSanPham_img_showLoaiTra);        //      TextView        TrangChuSanPham_tv_soLuongThongBao = findViewById(R.id.trangChuSanPham_tv_soLuongThongBao);        TrangChuSanPham_tv_showTatCaLoaiSanpham = findViewById(R.id.trangChuSanPham_tv_showTatCaLoaiSanpham);        TrangChuSanPham_tv_showLoai = findViewById(R.id.trangChuSanPham_tv_showLoai);        //      ecyclerView        TrangChuSanPham_rscV_showSanPhamNgang = findViewById(R.id.trangChuSanPham_rscV_showSanPhamNgang);        TrangChuSanPham_rscV_showSanPhamDoc = findViewById(R.id.trangChuSanPham_rscV_showSanPhamDoc);        //        DrawerLayout        TrangChuSanPham_drawerllout_main = findViewById(R.id.trangChuSanPham_drawerllout_main);        //        NavigationView        TrangChuSanPham_nav_drawer = findViewById(R.id.trangChuSanPham_nav_drawer);        //       layout datnhanh        //        ImageView        DatNhanh_img_showAnhSanPham = findViewById(R.id.datNhanh_img_showAnhSanPham);        DatNhanh_img_btn_giamSoLuongSanPham = findViewById(R.id.datNhanh_img_btn_giamSoLuongSanPham);        DatNhanh_img_btn_tangSoLuongSanPham = findViewById(R.id.datNhanh_img_btn_tangSoLuongSanPham);        //        TextView        DatNhanh_tv_showTenSanPham = findViewById(R.id.datNhanh_tv_showTenSanPham);        DatNhanh_tv_giaSanPham = findViewById(R.id.datNhanh_tv_giaSanPham);        DatNhanh_tv_giamGiaSanPham = findViewById(R.id.datNhanh_tv_giamGiaSanPham);        DatNhanh_tv_ngaySanXuat = findViewById(R.id.datNhanh_tv_ngaySanXuat);        DatNhanh_tv_soLuongSanPhamTrongKho = findViewById(R.id.datNhanh_tv_soLuongSanPhamTrongKho);        DatNhanh_tv_moTaSanPham = findViewById(R.id.datNhanh_tv_moTaSanPham);        DatNhanh_tv_SoLuongSanpham = findViewById(R.id.datNhanh_tv_SoLuongSanpham);        DatNhanh_tv_soLuongSanPhamYeuThichDaMua = findViewById(R.id.datNhanh_tv_soLuongSanPhamYeuThichDaMua);//        FrameLayout        DatNhanh_FmLt_showChiTietSanPham = findViewById(R.id.datNhanh_FmLt_showChiTietSanPham);//        Button        DatNhanh_btn_themSanPhamVaoGioHang = findViewById(R.id.datNhanh_btn_themSanPhamVaoGioHang);    }    @Override    public boolean onNavigationItemSelected(@NonNull MenuItem item) {        switch (item.getItemId()) {            case R.id.drawer_nav_login:                break;            case R.id.drawer_nav_logout:                break;            case R.id.drawer_nav_profile:                break;            case R.id.drawer_nav_rate:                break;            case R.id.drawer_nav_share:                break;        }        TrangChuSanPham_drawerllout_main.closeDrawer(GravityCompat.START);        return true;    }}