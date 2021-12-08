package com.example.lgfood_duan1.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.lgfood_duan1.Activity.trangChu_SanPham_Activity;
import com.example.lgfood_duan1.Model.model_SanPham;
import com.example.lgfood_duan1.Model.model_yeuThich;
import com.example.lgfood_duan1.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

// Trung
public class trangChu_showDoc_adapter extends RecyclerView.Adapter<trangChu_showDoc_adapter.ViewHolder> {
    private ArrayList<model_SanPham> arrListSanPham;
    private trangChu_SanPham_Activity context;
    private DatabaseReference dataRef;
    private FirebaseDatabase database;
    private SharedPreferences sharedPreferences;
    private ArrayList<model_yeuThich> yeuThichArrayList;
    //thai: onClickItem
    private IClickListener mIClickListener;

    public trangChu_showDoc_adapter(ArrayList<model_SanPham> arrListSanPham, trangChu_SanPham_Activity context, IClickListener mIClickListener) {
        this.arrListSanPham = arrListSanPham;
        this.context = context;
        this.mIClickListener = mIClickListener;

    }

    public interface IClickListener{
        void onClickShowItem(model_SanPham sanPham);

        void onClickHeart(model_SanPham sanPham);
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cuttom_sanpham_trangchu_doc, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(trangChu_showDoc_adapter.ViewHolder holder, int position) {
        model_SanPham sanPham=arrListSanPham.get(position);
        Glide.with(context)
                .load(arrListSanPham.get(position).getAnhSanPham())
                .into(holder.ItemCuttomTrangChu_doc_imgShowAnhSanPham);



        holder.ItemCuttomTrangChu_doc_tv_xuatXu.setText(arrListSanPham.get(position).getXuatXuSanPham());
        holder.ItemCuttomTrangChu_doc_tv_tenSanPham.setText(arrListSanPham.get(position).getTenSanPham());
        holder.ItemCuttomTrangChu_doc_tv_giaSanPham.setText(Double.parseDouble(arrListSanPham.get(position).getGiaBanSanPham() + "") + "00đ");
        holder.ItemCuttomTrangChu_doc_tv_soLuongSanPhamMuaYeuThich.setText("0");
        dataRef=database.getReference("danhSachSanPhamYeuThich").child(sharedPreferences.getString("IDDANHSACHYEUTHICH",""));
        dataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for (DataSnapshot ds:snapshot.getChildren()){
                    Toast.makeText(context, "chucmung3", Toast.LENGTH_SHORT).show();

                    model_yeuThich yeuThich=ds.getValue(model_yeuThich.class);
                    yeuThichArrayList.add(yeuThich);
                    for (int i=0;i<yeuThichArrayList.size();i++){
                        Toast.makeText(context, "chucmung2", Toast.LENGTH_SHORT).show();

                        if (sanPham.getIdSanPham().equals(yeuThichArrayList.get(i).getIdSanPham())){
                                    holder.ItemCuttomTrangChu_doc_img_btn_chonYeuThich.setImageResource(R.drawable.heart);

                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
//     Thêm sản phẩm vào yêu thích sản phẩm
        holder.ItemCuttomTrangChu_doc_img_btn_chonYeuThich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIClickListener.onClickHeart(sanPham);

            }
        });

//      Show chi tiết sản phẩm
        holder.ItemCuttomTrangChu_doc_llout_btn_showChiTietSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIClickListener.onClickShowItem(sanPham);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (arrListSanPham != null) {
            return arrListSanPham.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //        gán kiểu dữ liệu
         ImageView
                ItemCuttomTrangChu_doc_img_btn_chonYeuThich,
                ItemCuttomTrangChu_doc_imgShowAnhSanPham;
        LinearLayout ItemCuttomTrangChu_doc_llout_btn_showChiTietSanPham;
        TextView
                ItemCuttomTrangChu_doc_tv_soLuongSanPhamMuaYeuThich,
                ItemCuttomTrangChu_doc_tv_xuatXu,
                ItemCuttomTrangChu_doc_tv_tenSanPham,
                ItemCuttomTrangChu_doc_tv_giaSanPham;

        //
        public ViewHolder(View itemView) {
            super(itemView);
//            ánh xạ\
//            ImgeView
            database = FirebaseDatabase.getInstance("https://duan-lgfood1-default-rtdb.asia-southeast1.firebasedatabase.app/");
            sharedPreferences=itemView.getContext().getSharedPreferences("USER_FILE",Context.MODE_PRIVATE);
            yeuThichArrayList=new ArrayList<>();
            ItemCuttomTrangChu_doc_imgShowAnhSanPham = itemView.findViewById(R.id.itemCuttomTrangChu_doc_imgShowAnhSanPham);
            ItemCuttomTrangChu_doc_llout_btn_showChiTietSanPham = itemView.findViewById(R.id.itemCuttomTrangChu_doc_llout_btn_showChiTietSanPham);
            ItemCuttomTrangChu_doc_img_btn_chonYeuThich = itemView.findViewById(R.id.itemCuttomTrangChu_doc_img_btn_chonYeuThich);
            //            Textview
            ItemCuttomTrangChu_doc_tv_xuatXu = itemView.findViewById(R.id.itemCuttomTrangChu_doc_tv_xuatXu);
            ItemCuttomTrangChu_doc_tv_tenSanPham = itemView.findViewById(R.id.itemCuttomTrangChu_doc_tv_tenSanPham);
            ItemCuttomTrangChu_doc_tv_giaSanPham = itemView.findViewById(R.id.itemCuttomTrangChu_doc_tv_giaSanPham);
            ItemCuttomTrangChu_doc_tv_soLuongSanPhamMuaYeuThich = itemView.findViewById(R.id.itemCuttomTrangChu_doc_tv_soLuongSanPhamMuaYeuThich);
        }
    }
}
