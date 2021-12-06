package com.example.lgfood_duan1.Activity;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.example.lgfood_duan1.Adapter.showSlider_adaper;
import com.example.lgfood_duan1.Adapter.trangChu_showDoc_adapter;
import com.example.lgfood_duan1.Adapter.trangChu_showNgang_adapter;
import com.example.lgfood_duan1.Model.model_Account;
import com.example.lgfood_duan1.Model.model_Cart;
import com.example.lgfood_duan1.Model.model_SanPham;
import com.example.lgfood_duan1.Model.model_addToCart;
import com.example.lgfood_duan1.Model.model_yeuThich;
import com.example.lgfood_duan1.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.UUID;

public class trangChu_SanPham_Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout
            TrangChuSanPham_drawerllout_main;
    private NavigationView
            TrangChuSanPham_nav_drawer;
    private ImageView
            TrangChuSanPham_img_showMenu,
            TrangChuSanPham_img_btn_thongBao,
            TrangChuSanPham_img_btn_kieuLoaiSanPham,
            TrangChuSanPham_img_showLoaiCoffee,
            TrangChuSanPham_img_showLoaiThaoDuoc,
            TrangChuSanPham_img_showLoaiHat,
            TrangChuSanPham_img_showLoaiMuc,
            TrangChuSanPham_img_showLoaiTra,
            DatNhanh_img_showAnhSanPham,
            DatNhanh_img_btn_giamSoLuongSanPham,
            DatNhanh_img_btn_tangSoLuongSanPham;
    private TextView
            TrangChuSanPham_tv_soLuongThongBao,
            TrangChuSanPham_tv_btn_showTatCaLoaiSanpham,
            TrangChuSanPham_tv_showLoai,
            DatNhanh_tv_xuatXuSanPham,
            DatNhanh_tv_showTenSanPham,
            DatNhanh_tv_giaSanPham,
            DatNhanh_tv_giamGiaSanPham,
            DatNhanh_tv_ngaySanXuat,
            DatNhanh_tv_soLuongSanPhamTrongKho,
            DatNhanh_tv_moTaSanPham,
            DatNhanh_tv_SoLuongSanpham,
            DatNhanh_tv_soLuongSanPhamYeuThichDaMua,
            TrangChuSanPham_tv_btn_timTen,
            TrangChuSanPham_tv_btn_timGia,
            TrangChuSanPham_tv_btn_timLoai,
            TrangChuSanPham_tv_loai;
    private LinearLayout
            TrangChuSanPham_llout_formChonLoai;
    private RecyclerView
            TrangChuSanPham_rscV_showSanPhamNgang,
            TrangChuSanPham_rscV_showSanPhamDoc;
    private FrameLayout
            DatNhanh_FmLt_showChiTietSanPham,
            SanPham_flou_search;
    private Button
            DatNhanh_btn_themSanPhamVaoGioHang;
    private EditText
            TrangChuSanPham_edt_timKiemSanPham;
    private SharedPreferences shareAcout;
    String idSharePre;
    String idGioHangShare;
    //    sp Slider\
    SliderView sliderView;
    int[] images_slider = {R.drawable.img_panner, R.drawable.img_panner, R.drawable.img_panner};

    //Firebase
    private DatabaseReference dataRef, dataAccoutRef;
    private FirebaseDatabase database;
    //    Model
    ArrayList<model_Cart> model_cartArrayList;
    ArrayList<model_SanPham> arrListSanPham;
    ArrayList<model_Cart> arrListCart;
    model_SanPham arrSanPham;
    model_Cart arrCart;
    // adapter
    trangChu_showDoc_adapter TrangChu_showDoc_adapter;
    trangChu_showNgang_adapter TrangChu_showNgang_adapter;

    int timkiem = 0;
    //biến số lượng và id giỏ hàng
    int i = 1;
    String idGioHang;


    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu_san_pham);

        anhXa();
        batSuKien();
        getDataFirebaseCart();
        NavigationDrawer();
        getDataFirebase();
        showListProduc_Horizoltal();
        timKiem();
        showSlider();

    }

    //    BT: showSlider
    private void showSlider() {
        sliderView = findViewById(R.id.linearLayout2);

        showSlider_adaper showSliderAdaper = new showSlider_adaper(images_slider);

        sliderView.setSliderAdapter(showSliderAdaper);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();


    }

    //Trung Tìm kiếm tên
    private void timKiemTen(ArrayList<model_SanPham> arrlSanPham, String value) {
        if (!value.equals("")) {
            ArrayList<model_SanPham> arrListSanPhamTimKiem = new ArrayList<>();
            for (int i = 0; i < arrlSanPham.size(); i = i + 1) {
                String valueArr = (arrlSanPham.get(i).getTenSanPham());
                String valueTimKiem = value;
                if (valueArr.contains(valueTimKiem)) {

                    arrListSanPhamTimKiem.add(arrlSanPham.get(i));
                }
            }
            showListProduc_Vartical(arrListSanPhamTimKiem);
            TrangChuSanPham_tv_loai.setText("/" + arrListSanPhamTimKiem.size() + "SP/TÊN");

        }

    }

    //Trung Tìm kiếm giâ
    private void timKiemGia(ArrayList<model_SanPham> arrlSanPham, String value) {

        if (!value.equals("")) {
            ArrayList<model_SanPham> arrListSanPhamTimKiem = new ArrayList<>();
            for (int i = 0; i < arrlSanPham.size(); i = i + 1) {
                String valueArr = String.valueOf(arrlSanPham.get(i).getGiaBanSanPham());
                String valueTimKiem = value.toUpperCase().toString();
                if (valueArr.contains(valueTimKiem)) {

                    arrListSanPhamTimKiem.add(arrlSanPham.get(i));
                }
            }
            showListProduc_Vartical(arrListSanPhamTimKiem);
            TrangChuSanPham_tv_loai.setText("/" + arrListSanPhamTimKiem.size() + "SP/GIÁ");

        }

    }

    //Trung Tìm kiếm loại
    private void timKiemLoai(ArrayList<model_SanPham> arrlSanPham, String value) {
        if (!value.equals("")) {
            ArrayList<model_SanPham> arrListSanPhamTimKiem = new ArrayList<>();
            for (int i = 0; i < arrlSanPham.size(); i = i + 1) {
                String valueArr = String.valueOf(arrlSanPham.get(i).getLoaiSanPham());
                String valueTimKiem = value.toUpperCase().toString();
                if (valueArr.contains(valueTimKiem)) {

                    arrListSanPhamTimKiem.add(arrlSanPham.get(i));
                }
            }
            TrangChuSanPham_tv_loai.setText("/" + arrListSanPhamTimKiem.size() + "SP/LOẠI");
            showListProduc_Vartical(arrListSanPhamTimKiem);
        }

    }

    //Trung Tìm kiếm sản phẩm
    private void timKiem() {

        //        Chon tìm loại tên
        if (timkiem == 0) {
            TrangChuSanPham_edt_timKiemSanPham.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View view, int i, KeyEvent keyEvent) {
                    String giaTriTimKiem = TrangChuSanPham_edt_timKiemSanPham.getText().toString();
                    if (giaTriTimKiem.isEmpty()) {
                        TrangChuSanPham_edt_timKiemSanPham.setText("");
                        showListProduc_Vartical(arrListSanPham);
                        TrangChuSanPham_tv_loai.setText("/" + arrListSanPham.size() + "SP/TÊN");

                    } else {
                        timKiemTen(arrListSanPham, giaTriTimKiem);
                    }

                    return false;
                }
            });
        } else if (timkiem == 1) {
            TrangChuSanPham_edt_timKiemSanPham.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View view, int i, KeyEvent keyEvent) {
                    String giaTriTimKiem = TrangChuSanPham_edt_timKiemSanPham.getText().toString();
                    if (giaTriTimKiem.isEmpty()) {
                        TrangChuSanPham_edt_timKiemSanPham.setText("");
                        showListProduc_Vartical(arrListSanPham);
                        TrangChuSanPham_tv_loai.setText("/" + arrListSanPham.size() + "SP/GIÁ");

                    } else {
                        timKiemGia(arrListSanPham, giaTriTimKiem);
                    }

                    return false;
                }
            });
        } else if (timkiem == 2) {
            TrangChuSanPham_edt_timKiemSanPham.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View view, int i, KeyEvent keyEvent) {
                    String giaTriTimKiem = TrangChuSanPham_edt_timKiemSanPham.getText().toString();
                    if (giaTriTimKiem.isEmpty()) {
                        TrangChuSanPham_edt_timKiemSanPham.setText("");
                        showListProduc_Vartical(arrListSanPham);
                        TrangChuSanPham_tv_loai.setText("/" + arrListSanPham.size() + "SP/LOẠI");

                    } else {
                        timKiemLoai(arrListSanPham, giaTriTimKiem);
                    }
                    return false;
                }
            });
        }
    }

    //    Bắt sự kiện thi thao tác
    private void batSuKien() {
//        bắt sự kiện chuyển trang navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.trangChuSanPham_bottomNavigation);

        bottomNavigationView.setSelectedItemId(R.id.Use);


        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.cart:
                        startActivity(new Intent(getApplicationContext(), trangChu_SanPham_Activity.class));
                        overridePendingTransition(0, 0);
                        return;
                    case R.id.Like:
                        startActivity(new Intent(getApplicationContext(), favorite_Activity.class));
                        overridePendingTransition(0, 0);
                        return;
                    case R.id.Home:
                        startActivity(new Intent(getApplicationContext(), trangChu_SanPham_Activity.class));
                        overridePendingTransition(0, 0);
                        return;
                    case R.id.Paid:
                        startActivity(new Intent(getApplicationContext(), gio_Hang_Activity.class));
                        overridePendingTransition(0, 0);

                        return;
                    case R.id.Use:
                        startActivity(new Intent(getApplicationContext(), thongTinTaiKhoan_Activity.class));
                        overridePendingTransition(0, 0);
                        return;
                }
            }
        });
//        bắt sự kiện tìm kiếm
        TrangChuSanPham_edt_timKiemSanPham.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                timKiem();
                return false;
            }
        });
        TrangChuSanPham_tv_btn_timTen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timkiem = 0;
                TrangChuSanPham_tv_loai.setText("/TÊN");
                TrangChuSanPham_edt_timKiemSanPham.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_TEXT); //for decimal numbers

                TrangChuSanPham_tv_btn_timTen.setTextColor(Color.parseColor("#F0A23C"));
                TrangChuSanPham_tv_btn_timGia.setTextColor(Color.parseColor("#BCBCBC"));
                TrangChuSanPham_tv_btn_timLoai.setTextColor(Color.parseColor("#BCBCBC"));
                TrangChuSanPham_llout_formChonLoai.setVisibility(View.INVISIBLE);
                TrangChuSanPham_tv_loai.setVisibility(View.VISIBLE);
                timKiem();
            }
        });
//        Chọn tìm loại giá
        TrangChuSanPham_tv_btn_timGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TrangChuSanPham_edt_timKiemSanPham.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL); //for decimal numbers

                timkiem = 1;
                TrangChuSanPham_tv_loai.setText("/GIÁ");
                TrangChuSanPham_tv_btn_timTen.setTextColor(Color.parseColor("#BCBCBC"));
                TrangChuSanPham_tv_btn_timGia.setTextColor(Color.parseColor("#F0A23C"));
                TrangChuSanPham_tv_btn_timLoai.setTextColor(Color.parseColor("#BCBCBC"));
                TrangChuSanPham_llout_formChonLoai.setVisibility(View.INVISIBLE);
                TrangChuSanPham_tv_loai.setVisibility(View.VISIBLE);

                timKiem();
            }
        });
//        Chọn tìm loại Loại
        TrangChuSanPham_tv_btn_timLoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TrangChuSanPham_edt_timKiemSanPham.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_TEXT); //for decimal numbers

                timkiem = 2;
                TrangChuSanPham_tv_loai.setText("/LOẠI");
                TrangChuSanPham_tv_btn_timTen.setTextColor(Color.parseColor("#BCBCBC"));
                TrangChuSanPham_tv_btn_timGia.setTextColor(Color.parseColor("#BCBCBC"));
                TrangChuSanPham_tv_btn_timLoai.setTextColor(Color.parseColor("#F0A23C"));
                TrangChuSanPham_llout_formChonLoai.setVisibility(View.INVISIBLE);
                TrangChuSanPham_tv_loai.setVisibility(View.VISIBLE);
                timKiem();


            }
        });
//   show form chọn loại sản phẩm
        TrangChuSanPham_img_btn_kieuLoaiSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TrangChuSanPham_llout_formChonLoai.getVisibility() == View.VISIBLE) {
//                    khi tắt
                    TrangChuSanPham_llout_formChonLoai.setVisibility(View.INVISIBLE);
                    TrangChuSanPham_tv_loai.setVisibility(View.VISIBLE);
                } else {
//                    khi mở lên
                    TrangChuSanPham_llout_formChonLoai.setVisibility(View.VISIBLE);
                    TrangChuSanPham_tv_loai.setVisibility(View.INVISIBLE);
                }


            }
        });
//   Loại sản phẩm
//        loại coffe
        TrangChuSanPham_img_showLoaiCoffee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TrangChuSanPham_tv_btn_showTatCaLoaiSanpham.setBackgroundResource(R.drawable.broder_radius_xanhduong_nhe);
                TrangChuSanPham_tv_btn_showTatCaLoaiSanpham.setTextColor(Color.parseColor("#F9B864"));

                TrangChuSanPham_img_showLoaiCoffee.setBackgroundResource(R.drawable.broder_radius_cam2_thuonghieu);
                TrangChuSanPham_img_showLoaiCoffee.setImageResource(R.drawable.ic_coffe_white);

                TrangChuSanPham_img_showLoaiThaoDuoc.setBackgroundResource(R.drawable.broder_radius_xanhduong_nhe);
                TrangChuSanPham_img_showLoaiThaoDuoc.setImageResource(R.drawable.ic_herb);

                TrangChuSanPham_img_showLoaiHat.setBackgroundResource(R.drawable.broder_radius_xanhduong_nhe);
                TrangChuSanPham_img_showLoaiHat.setImageResource(R.drawable.ic_seed);

                TrangChuSanPham_img_showLoaiMuc.setBackgroundResource(R.drawable.broder_radius_xanhduong_nhe);
                TrangChuSanPham_img_showLoaiMuc.setImageResource(R.drawable.ic_jam);

                TrangChuSanPham_img_showLoaiTra.setBackgroundResource(R.drawable.broder_radius_xanhduong_nhe);
                TrangChuSanPham_img_showLoaiTra.setImageResource(R.drawable.ic_tea);

                showListProduc_Vartical(kiemTraLoaiSanPham("Cà phê", arrListSanPham));
                TrangChuSanPham_tv_showLoai.setText("Loại: COFFE");
            }
        });
//        loại thảo dược
        TrangChuSanPham_img_showLoaiThaoDuoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TrangChuSanPham_tv_btn_showTatCaLoaiSanpham.setBackgroundResource(R.drawable.broder_radius_xanhduong_nhe);
                TrangChuSanPham_tv_btn_showTatCaLoaiSanpham.setTextColor(Color.parseColor("#F9B864"));

                TrangChuSanPham_img_showLoaiCoffee.setBackgroundResource(R.drawable.broder_radius_xanhduong_nhe);
                TrangChuSanPham_img_showLoaiCoffee.setImageResource(R.drawable.ic_coffee);

                TrangChuSanPham_img_showLoaiThaoDuoc.setBackgroundResource(R.drawable.broder_radius_cam2_thuonghieu);
                TrangChuSanPham_img_showLoaiThaoDuoc.setImageResource(R.drawable.ic_herb_white);

                TrangChuSanPham_img_showLoaiHat.setBackgroundResource(R.drawable.broder_radius_xanhduong_nhe);
                TrangChuSanPham_img_showLoaiHat.setImageResource(R.drawable.ic_seed);

                TrangChuSanPham_img_showLoaiMuc.setBackgroundResource(R.drawable.broder_radius_xanhduong_nhe);
                TrangChuSanPham_img_showLoaiMuc.setImageResource(R.drawable.ic_jam);

                TrangChuSanPham_img_showLoaiTra.setBackgroundResource(R.drawable.broder_radius_xanhduong_nhe);
                TrangChuSanPham_img_showLoaiTra.setImageResource(R.drawable.ic_tea);
                TrangChu_showDoc_adapter.notifyDataSetChanged();
                showListProduc_Vartical(kiemTraLoaiSanPham("Thảo mộc", arrListSanPham));
                TrangChuSanPham_tv_showLoai.setText("Loại: THẢO MỘC");

            }
        });
//        Loại hạt
        TrangChuSanPham_img_showLoaiHat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TrangChuSanPham_tv_btn_showTatCaLoaiSanpham.setBackgroundResource(R.drawable.broder_radius_xanhduong_nhe);
                TrangChuSanPham_tv_btn_showTatCaLoaiSanpham.setTextColor(Color.parseColor("#F9B864"));

                TrangChuSanPham_img_showLoaiCoffee.setBackgroundResource(R.drawable.broder_radius_xanhduong_nhe);
                TrangChuSanPham_img_showLoaiCoffee.setImageResource(R.drawable.ic_coffee);

                TrangChuSanPham_img_showLoaiThaoDuoc.setBackgroundResource(R.drawable.broder_radius_xanhduong_nhe);
                TrangChuSanPham_img_showLoaiThaoDuoc.setImageResource(R.drawable.ic_herb);

                TrangChuSanPham_img_showLoaiHat.setBackgroundResource(R.drawable.broder_radius_cam2_thuonghieu);
                TrangChuSanPham_img_showLoaiHat.setImageResource(R.drawable.ic_seeds_white);

                TrangChuSanPham_img_showLoaiMuc.setBackgroundResource(R.drawable.broder_radius_xanhduong_nhe);
                TrangChuSanPham_img_showLoaiMuc.setImageResource(R.drawable.ic_jam);

                TrangChuSanPham_img_showLoaiTra.setBackgroundResource(R.drawable.broder_radius_xanhduong_nhe);
                TrangChuSanPham_img_showLoaiTra.setImageResource(R.drawable.ic_tea);

                showListProduc_Vartical(kiemTraLoaiSanPham("Hạt đặc sản", arrListSanPham));
                TrangChuSanPham_tv_showLoai.setText("Loại: HẠT");
            }
        });
//        Loại mức
        TrangChuSanPham_img_showLoaiMuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TrangChuSanPham_tv_btn_showTatCaLoaiSanpham.setBackgroundResource(R.drawable.broder_radius_xanhduong_nhe);
                TrangChuSanPham_tv_btn_showTatCaLoaiSanpham.setTextColor(Color.parseColor("#F9B864"));

                TrangChuSanPham_img_showLoaiCoffee.setBackgroundResource(R.drawable.broder_radius_xanhduong_nhe);
                TrangChuSanPham_img_showLoaiCoffee.setImageResource(R.drawable.ic_coffee);

                TrangChuSanPham_img_showLoaiThaoDuoc.setBackgroundResource(R.drawable.broder_radius_xanhduong_nhe);
                TrangChuSanPham_img_showLoaiThaoDuoc.setImageResource(R.drawable.ic_herb);

                TrangChuSanPham_img_showLoaiHat.setBackgroundResource(R.drawable.broder_radius_xanhduong_nhe);
                TrangChuSanPham_img_showLoaiHat.setImageResource(R.drawable.ic_seed);

                TrangChuSanPham_img_showLoaiMuc.setBackgroundResource(R.drawable.broder_radius_cam2_thuonghieu);
                TrangChuSanPham_img_showLoaiMuc.setImageResource(R.drawable.ic_jam_white);

                TrangChuSanPham_img_showLoaiTra.setBackgroundResource(R.drawable.broder_radius_xanhduong_nhe);
                TrangChuSanPham_img_showLoaiTra.setImageResource(R.drawable.ic_tea);

                showListProduc_Vartical(kiemTraLoaiSanPham("Trái cây xấy dẻo", arrListSanPham));
                TrangChuSanPham_tv_showLoai.setText("Loại: MỨC");

            }
        });
//        Loại trà
        TrangChuSanPham_img_showLoaiTra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TrangChuSanPham_tv_btn_showTatCaLoaiSanpham.setBackgroundResource(R.drawable.broder_radius_xanhduong_nhe);
                TrangChuSanPham_tv_btn_showTatCaLoaiSanpham.setTextColor(Color.parseColor("#F9B864"));

                TrangChuSanPham_img_showLoaiCoffee.setBackgroundResource(R.drawable.broder_radius_xanhduong_nhe);
                TrangChuSanPham_img_showLoaiCoffee.setImageResource(R.drawable.ic_coffee);

                TrangChuSanPham_img_showLoaiThaoDuoc.setBackgroundResource(R.drawable.broder_radius_xanhduong_nhe);
                TrangChuSanPham_img_showLoaiThaoDuoc.setImageResource(R.drawable.ic_herb);

                TrangChuSanPham_img_showLoaiHat.setBackgroundResource(R.drawable.broder_radius_xanhduong_nhe);
                TrangChuSanPham_img_showLoaiHat.setImageResource(R.drawable.ic_seed);

                TrangChuSanPham_img_showLoaiMuc.setBackgroundResource(R.drawable.broder_radius_xanhduong_nhe);
                TrangChuSanPham_img_showLoaiMuc.setImageResource(R.drawable.ic_jam);

                TrangChuSanPham_img_showLoaiTra.setBackgroundResource(R.drawable.broder_radius_cam2_thuonghieu);
                TrangChuSanPham_img_showLoaiTra.setImageResource(R.drawable.ic_tea_white);

                showListProduc_Vartical(kiemTraLoaiSanPham("Trà túi lọc", arrListSanPham));
                TrangChuSanPham_tv_showLoai.setText("Loại: TEA");
            }
        });
//        tất cả sản phẩm
        TrangChuSanPham_tv_btn_showTatCaLoaiSanpham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TrangChuSanPham_tv_btn_showTatCaLoaiSanpham.setBackgroundResource(R.drawable.broder_radius_cam2_thuonghieu);
                TrangChuSanPham_tv_btn_showTatCaLoaiSanpham.setTextColor(Color.parseColor("#EEF5FF"));

                TrangChuSanPham_img_showLoaiCoffee.setBackgroundResource(R.drawable.broder_radius_xanhduong_nhe);
                TrangChuSanPham_img_showLoaiCoffee.setImageResource(R.drawable.ic_coffee);

                TrangChuSanPham_img_showLoaiThaoDuoc.setBackgroundResource(R.drawable.broder_radius_xanhduong_nhe);
                TrangChuSanPham_img_showLoaiThaoDuoc.setImageResource(R.drawable.ic_herb);

                TrangChuSanPham_img_showLoaiHat.setBackgroundResource(R.drawable.broder_radius_xanhduong_nhe);
                TrangChuSanPham_img_showLoaiHat.setImageResource(R.drawable.ic_seed);

                TrangChuSanPham_img_showLoaiMuc.setBackgroundResource(R.drawable.broder_radius_xanhduong_nhe);
                TrangChuSanPham_img_showLoaiMuc.setImageResource(R.drawable.ic_jam);

                TrangChuSanPham_img_showLoaiTra.setBackgroundResource(R.drawable.broder_radius_xanhduong_nhe);
                TrangChuSanPham_img_showLoaiTra.setImageResource(R.drawable.ic_tea);
                showListProduc_Vartical(arrListSanPham);
                TrangChuSanPham_tv_showLoai.setText("Sản phẩm: ALL");
            }
        });
    }

    //  Trung Kiểm tra loại sản phẩm
    private ArrayList<model_SanPham> kiemTraLoaiSanPham(String loaiSP, ArrayList<model_SanPham> arrListSP) {
        ArrayList<model_SanPham> arrListLoaiSanPham = new ArrayList<>();
        for (int i = 0; i < arrListSP.size(); i = i + 1) {
            if (loaiSP.equals(arrListSP.get(i).getLoaiSanPham())) {
                arrListLoaiSanPham.add(arrListSP.get(i));
            }
        }
        return arrListLoaiSanPham;
    }

//    Trung Show sản phẩm Adapter

    /********************Show thông tin ra kiểu ngang**********************/
    private void showListProduc_Horizoltal() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(trangChu_SanPham_Activity.this, LinearLayoutManager.HORIZONTAL, false);
        TrangChuSanPham_rscV_showSanPhamNgang.setLayoutManager(linearLayoutManager);
        TrangChuSanPham_rscV_showSanPhamNgang.setItemAnimator(new DefaultItemAnimator());
//        Initilize
        TrangChu_showNgang_adapter = new trangChu_showNgang_adapter(arrListSanPham, trangChu_SanPham_Activity.this, new trangChu_showNgang_adapter.IClickListener() {
            @Override
            public void onClickAdd(model_SanPham sanPham) {

//                onCLickAddToCart(sanPham);


            }
        });
        TrangChuSanPham_rscV_showSanPhamNgang.setAdapter(TrangChu_showNgang_adapter);
    }


    /********************Show thông tin ra kiểu dọc**********************/
    private void showListProduc_Vartical(ArrayList<model_SanPham> arrListSp) {
        TrangChuSanPham_rscV_showSanPhamDoc.setLayoutManager(new GridLayoutManager(this, 2));
        TrangChuSanPham_rscV_showSanPhamDoc.setItemAnimator(new DefaultItemAnimator());
        //        Initilize
        TrangChu_showDoc_adapter = new trangChu_showDoc_adapter(arrListSp, trangChu_SanPham_Activity.this, new trangChu_showDoc_adapter.IClickListener() {
            @Override
            public void onClickShowItem(model_SanPham sanPham) {
                showItemChiTietSanPham(sanPham);
            }

            @Override
            public void onClickHeart(model_SanPham sanPham) {
                onClickHeartItem(sanPham);
            }
        });
        TrangChuSanPham_rscV_showSanPhamDoc.setAdapter(TrangChu_showDoc_adapter);
    }
    private void onClickHeartItem(model_SanPham sanPham) {

        String idYeuThich;
        UUID uuid=UUID.randomUUID();
        idYeuThich=String.valueOf(uuid);

        model_yeuThich yeuThich=new model_yeuThich(sanPham.getIdSanPham(),idYeuThich);
        dataRef=database.getReference("danhSachSanPhamYeuThich");
        dataRef.child(sharedPreferences.getString("IDDANHSACHYEUTHICH","")).child(idYeuThich).setValue(yeuThich);

    }
    //Trung: lấy dữ liệu sản phẩm trên firebase về
    private void getDataFirebase() {
        Toast.makeText(trangChu_SanPham_Activity.this, arrListSanPham.size() + "", Toast.LENGTH_SHORT).show();

        dataRef = database.getReference().child("khoHang");
        dataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                arrListSanPham.clear();
//                Chạy vòng lặp kiểm tra dữ liệu
                for (DataSnapshot child : snapshot.getChildren()) {
                    arrSanPham = child.getValue(model_SanPham.class);
                    arrListSanPham.add(arrSanPham);
                }

                Toast.makeText(trangChu_SanPham_Activity.this, arrListSanPham.get(1).getTenSanPham() + "", Toast.LENGTH_SHORT).show();
                showListProduc_Vartical(arrListSanPham);
                TrangChu_showNgang_adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }

    //    BToàn: show menu Drawer
    private void NavigationDrawer() {
        /*           NavigationView Drawer Menu           */

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, TrangChuSanPham_drawerllout_main, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        TrangChuSanPham_drawerllout_main.addDrawerListener(toggle);
        toggle.syncState();

        TrangChuSanPham_nav_drawer.setNavigationItemSelectedListener(trangChu_SanPham_Activity.this);
        TrangChuSanPham_nav_drawer.bringToFront();
        TrangChuSanPham_nav_drawer.setCheckedItem(R.id.drawer_nav_login);
        /*           Phần quyền đăng nhập           */

//        Menu menu = TrangChuSanPham_nav_drawer.getMenu();
//        menu.findItem(R.id.drawer_nav_logout).setVisible(false);
//        menu.findItem(R.id.drawer_nav_profile).setVisible(false);


        TrangChuSanPham_img_showMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TrangChuSanPham_drawerllout_main.isDrawerVisible(GravityCompat.START))
                    TrangChuSanPham_drawerllout_main.closeDrawer(GravityCompat.START);
                else TrangChuSanPham_drawerllout_main.openDrawer(GravityCompat.START);
            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.drawer_nav_login:

                break;
            case R.id.drawer_nav_logout:
                SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.clear();
                editor.commit();
                Intent intent2 = new Intent(trangChu_SanPham_Activity.this, Login_Activity.class);
                startActivity(intent2);
                break;

            case R.id.drawer_nav_profile:
                Intent intent3 = new Intent(trangChu_SanPham_Activity.this, thongTinTaiKhoan_Activity.class);
                startActivity(intent3);
                break;

            case R.id.drawer_nav_rate:

                break;

            case R.id.drawer_nav_share:

                break;
        }
        TrangChuSanPham_drawerllout_main.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (TrangChuSanPham_drawerllout_main.isDrawerVisible(GravityCompat.START)) {
            TrangChuSanPham_drawerllout_main.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }


    //thai:onClickItemSanPham
    private void showItemChiTietSanPham(model_SanPham sanPham) {
        anhXa();
        getDataFirebaseCart();
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dat_nhanh);

        LinearLayout datNhanh_linear_turnOffDiaLog = dialog.findViewById(R.id.datNhanh_linear_turnOffDiaLog);
        //thong tin san pham
        ImageView datNhanh_img_showAnhSanPham = dialog.findViewById(R.id.datNhanh_img_showAnhSanPham);
        TextView datNhanh_tv_xuatXuSanPham = dialog.findViewById(R.id.datNhanh_tv_xuatXuSanPham);
        TextView datNhanh_tv_showTenSanPham = dialog.findViewById(R.id.datNhanh_tv_showTenSanPham);
        TextView datNhanh_tv_giaSanPham = dialog.findViewById(R.id.datNhanh_tv_giaSanPham);
        TextView datNhanh_tv_giamGiaSanPham = dialog.findViewById(R.id.datNhanh_tv_giamGiaSanPham);
        TextView datNhanh_tv_ngaySanXuat = dialog.findViewById(R.id.datNhanh_tv_ngaySanXuat);
        TextView datNhanh_tv_soLuongSanPhamTrongKho = dialog.findViewById(R.id.datNhanh_tv_soLuongSanPhamTrongKho);
        TextView datNhanh_tv_soLuongSanPhamYeuThichDaMua = dialog.findViewById(R.id.datNhanh_tv_soLuongSanPhamYeuThichDaMua);
        TextView datNhanh_tv_moTaSanPham = dialog.findViewById(R.id.datNhanh_tv_moTaSanPham);

        //tang giam so luong sp
        ImageView datNhanh_img_btn_giamSoLuongSanPham = dialog.findViewById(R.id.datNhanh_img_btn_giamSoLuongSanPham);
        TextView datNhanh_tv_SoLuongSanpham = dialog.findViewById(R.id.datNhanh_tv_SoLuongSanpham);
        ImageView datNhanh_img_btn_tangSoLuongSanPham = dialog.findViewById(R.id.datNhanh_img_btn_tangSoLuongSanPham);

        Button datNhanh_btn_themSanPhamVaoGioHang = dialog.findViewById(R.id.datNhanh_btn_themSanPhamVaoGioHang);

        Glide.with(trangChu_SanPham_Activity.this)
                .load(sanPham.getAnhSanPham())
                .into(datNhanh_img_showAnhSanPham);
        datNhanh_tv_xuatXuSanPham.setText(sanPham.getXuatXuSanPham());
        datNhanh_tv_showTenSanPham.setText(sanPham.getTenSanPham());
        datNhanh_tv_giaSanPham.setText(sanPham.getGiaBanSanPham() + "00đ");
        datNhanh_tv_giamGiaSanPham.setText("   -" + sanPham.getGiamGiaSanPham());
        datNhanh_tv_ngaySanXuat.setText(sanPham.getNgaySanXuatSanPham());
        datNhanh_tv_soLuongSanPhamTrongKho.setText(String.valueOf(sanPham.getSoLuongSanPham()));
//        datNhanh_tv_soLuongSanPhamYeuThichDaMua.setText(Integer.valueOf(sanPham.getAnhSanPham()));
        datNhanh_tv_moTaSanPham.setText(sanPham.getMoTaSanPham());

        //giam so luong san pham
        datNhanh_img_btn_giamSoLuongSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i--;
                if (i <= 1) {
                    i = 1;
                    datNhanh_tv_SoLuongSanpham.setText(String.valueOf(i));
                    datNhanh_btn_themSanPhamVaoGioHang.setText(String.valueOf(i * sanPham.getGiaBanSanPham()));
                    return;
                } else {
                    datNhanh_tv_SoLuongSanpham.setText(String.valueOf(i));
                    datNhanh_btn_themSanPhamVaoGioHang.setText(String.valueOf(i * sanPham.getGiaBanSanPham()));
                }


            }
        });
        //tang so luong san pham
        datNhanh_img_btn_tangSoLuongSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;
                datNhanh_tv_SoLuongSanpham.setText(String.valueOf(i));
                datNhanh_btn_themSanPhamVaoGioHang.setText(String.valueOf(i * sanPham.getGiaBanSanPham()));
            }
        });
        //turn off dialog
        datNhanh_linear_turnOffDiaLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                i = 1;
            }
        });

        //add to cart
        datNhanh_btn_themSanPhamVaoGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idProduct = sanPham.getIdSanPham();
                Log.d("eee", "idprduc" + idProduct);
                dataRef = database.getReference("Accounts").child(idSharePre);
                dataRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        boolean check = false;
                        String idCart = "";
                        int viTri = 0;
                        dataRef = database.getReference("newCarts");
                        model_Account account = snapshot.getValue(model_Account.class);
                        idGioHang = String.valueOf(account.getIdGioHang());
                        UUID uuid = UUID.randomUUID();
                        String idProduct = sanPham.getIdSanPham();
                        String idChiTietSanPham = String.valueOf(uuid);
                        model_addToCart newCart = new model_addToCart(sanPham.getIdSanPham(), sanPham.getMoTaSanPham(), sanPham.getTenSanPham(), sanPham.getNgaySanXuatSanPham(), sanPham.getXuatXuSanPham(), sanPham.getLoaiSanPham(), sanPham.getTinhTrangSanPham(), sanPham.getAnhSanPham(), sanPham.getNgayNhapSanPham(), sanPham.getSoLuongSanPham(), sanPham.getGiamGiaSanPham(), sanPham.getGiaNhapSanPham(), sanPham.getGiaBanSanPham());
                        dataRef.child(idGioHang).child(sanPham.getIdSanPham()).setValue(newCart);

//                       Trung  kiem tra trong gioHangs đã có sản phẩm chưa nếu có chỉ tăng số lượng không tăng cart mới

                         dataRef = database.getReference("GioHangs").child(idGioHang);
                        for (int j = 0; j < arrListCart.size(); j++) {
                            if (arrListCart.get(j).getIdSanPham().equals(idProduct)) {
                                check = true;
                                idCart = arrListCart.get(j).getIdGioHang();
                                viTri = j;
                            }

                        }


                        if (check == true) {
                            int tong = i + Integer.parseInt(arrListCart.get(viTri).getSoLuong());
                            dataRef.child(idCart).child("soLuong").setValue(tong + "");

                        } else {
                            model_Cart cart = new model_Cart(UUID.randomUUID().toString(), idProduct, i + "");
                            dataRef.child(cart.getIdGioHang()).setValue(cart);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.show();

    }

    private void getDataFirebaseCart() {
        dataRef = database.getReference("GioHangs").child(sharedPreferences.getString("IDGIOHANG",""));
        dataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                arrListCart.clear();
                for (DataSnapshot child : snapshot.getChildren()) {
                    arrCart = child.getValue(model_Cart.class);
                    arrListCart.add(arrCart);
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }

    //     Ánh xạ
    private void anhXa() {
//        SharedPreferences
        sharedPreferences = getSharedPreferences("USER_FILE", MODE_PRIVATE);
//        model
        arrListSanPham = new ArrayList<model_SanPham>();
        arrListCart = new ArrayList<model_Cart>();
        //Firebase
        database = FirebaseDatabase.getInstance("https://duan-lgfood1-default-rtdb.asia-southeast1.firebasedatabase.app/");
        //      ImageView
        TrangChuSanPham_img_showMenu = findViewById(R.id.trangChuSanPham_img_showMenu);
        TrangChuSanPham_img_btn_thongBao = findViewById(R.id.trangChuSanPham_img_btn_thongBao);
        TrangChuSanPham_img_btn_kieuLoaiSanPham = findViewById(R.id.trangChuSanPham_img_btn_kieuLoaiSanPham);
        TrangChuSanPham_img_showLoaiCoffee = findViewById(R.id.trangChuSanPham_img_showLoaiCoffee);
        TrangChuSanPham_img_showLoaiThaoDuoc = findViewById(R.id.trangChuSanPham_img_showLoaiThaoDuoc);
        TrangChuSanPham_img_showLoaiHat = findViewById(R.id.trangChuSanPham_img_showLoaiHat);
        TrangChuSanPham_img_showLoaiMuc = findViewById(R.id.trangChuSanPham_img_showLoaiMuc);
        TrangChuSanPham_img_showLoaiTra = findViewById(R.id.trangChuSanPham_img_showLoaiTra);
//FrameLayout
//        SanPham_flou_search = findViewById(R.id.sanPham_flou_search);
        //      TextView
        TrangChuSanPham_tv_soLuongThongBao = findViewById(R.id.trangChuSanPham_tv_soLuongThongBao);
        TrangChuSanPham_tv_btn_showTatCaLoaiSanpham = findViewById(R.id.trangChuSanPham_tv_btn_showTatCaLoaiSanpham);
        TrangChuSanPham_tv_showLoai = findViewById(R.id.trangChuSanPham_tv_showLoai);
        TrangChuSanPham_tv_btn_timTen = findViewById(R.id.trangChuSanPham_tv_btn_timTen);
        TrangChuSanPham_tv_btn_timGia = findViewById(R.id.trangChuSanPham_tv_btn_timGia);
        TrangChuSanPham_tv_btn_timLoai = findViewById(R.id.trangChuSanPham_tv_btn_timLoai);
        TrangChuSanPham_tv_loai = findViewById(R.id.trangChuSanPham_tv_loai);

        //      ecyclerView
        TrangChuSanPham_rscV_showSanPhamNgang = findViewById(R.id.trangChuSanPham_rscV_showSanPhamNgang);
        TrangChuSanPham_rscV_showSanPhamDoc = findViewById(R.id.trangChuSanPham_rscV_showSanPhamDoc);
        //        DrawerLayout
        TrangChuSanPham_drawerllout_main = findViewById(R.id.trangChuSanPham_drawerllout_main);
        //        LinearLayout
        TrangChuSanPham_llout_formChonLoai = findViewById(R.id.trangChuSanPham_llout_formChonLoai);
        //        NavigationView
        TrangChuSanPham_nav_drawer = findViewById(R.id.trangChuSanPham_nav_drawer);
        //  EditText
        TrangChuSanPham_edt_timKiemSanPham = findViewById(R.id.trangChuSanPham_edt_timKiemSanPham);
        //       layout datnhanh
        //        ImageView
        DatNhanh_img_showAnhSanPham = findViewById(R.id.datNhanh_img_showAnhSanPham);
        DatNhanh_img_btn_giamSoLuongSanPham = findViewById(R.id.datNhanh_img_btn_giamSoLuongSanPham);
        DatNhanh_img_btn_tangSoLuongSanPham = findViewById(R.id.datNhanh_img_btn_tangSoLuongSanPham);


        //        TextView
        DatNhanh_tv_showTenSanPham = findViewById(R.id.datNhanh_tv_showTenSanPham);
        DatNhanh_tv_giaSanPham = findViewById(R.id.datNhanh_tv_giaSanPham);
        DatNhanh_tv_giamGiaSanPham = findViewById(R.id.datNhanh_tv_giamGiaSanPham);
        DatNhanh_tv_ngaySanXuat = findViewById(R.id.datNhanh_tv_ngaySanXuat);
        DatNhanh_tv_soLuongSanPhamTrongKho = findViewById(R.id.datNhanh_tv_soLuongSanPhamTrongKho);
        DatNhanh_tv_moTaSanPham = findViewById(R.id.datNhanh_tv_moTaSanPham);
        DatNhanh_tv_SoLuongSanpham = findViewById(R.id.datNhanh_tv_SoLuongSanpham);
        DatNhanh_tv_soLuongSanPhamYeuThichDaMua = findViewById(R.id.datNhanh_tv_soLuongSanPhamYeuThichDaMua);
        //        FrameLayout
        DatNhanh_FmLt_showChiTietSanPham = findViewById(R.id.datNhanh_FmLt_showChiTietSanPham);
        //        Button
        DatNhanh_btn_themSanPhamVaoGioHang = findViewById(R.id.datNhanh_btn_themSanPhamVaoGioHang);

        shareAcout = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        idSharePre = shareAcout.getString("IDUSRE", "");
        idGioHangShare = shareAcout.getString("IDGIOHANG", "");
        model_cartArrayList = new ArrayList<>();

    }

}

