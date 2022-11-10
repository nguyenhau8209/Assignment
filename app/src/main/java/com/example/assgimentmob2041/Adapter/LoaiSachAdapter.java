package com.example.assgimentmob2041.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assgimentmob2041.DAO.LoaiSachDAO;
import com.example.assgimentmob2041.Modole.ItemClick;
import com.example.assgimentmob2041.Modole.LoaiSach;
import com.example.assgimentmob2041.R;

import java.util.ArrayList;

public class LoaiSachAdapter extends RecyclerView.Adapter<LoaiSachAdapter.ViewHolder> {
    Context context;
    ArrayList<LoaiSach> listLoaiSach;
    private ItemClick itemClick;

    public LoaiSachAdapter(Context context, ArrayList<LoaiSach> listLoaiSach , ItemClick itemClick) {
        this.context = context;
        this.listLoaiSach = listLoaiSach;
        this.itemClick = itemClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_loai_sach, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvTen.setText("Mã Loại: " + listLoaiSach.get(position).getTenLoai());
        holder.tvMa.setText("Tên Loại: " + listLoaiSach.get(position).getMaLoai() + "");

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoaiSachDAO loaiSachDAO = new LoaiSachDAO(context);
                int check = loaiSachDAO.delete(listLoaiSach.get(holder.getAdapterPosition()).getMaLoai());
                switch (check) {
                    case 1:
                        listLoaiSach.clear();
                        listLoaiSach = loaiSachDAO.getAll();
                        notifyDataSetChanged();
                        Toast.makeText(context, "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                        break;
                    case -1:
                        Toast.makeText(context, "Không thể xóa vì đã có sách", Toast.LENGTH_SHORT).show();
                    case 0:
                        Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();

                }
            }
        });
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoaiSach loaiSach = listLoaiSach.get(holder.getAdapterPosition());
                itemClick.onClickLoaiSach(loaiSach);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listLoaiSach.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvMa, tvTen;
        ImageView btnDelete;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMa = itemView.findViewById(R.id.tvMaLoaiSach);
            tvTen = itemView.findViewById(R.id.tvTenLoaiSach);
            btnDelete = itemView.findViewById(R.id.btnDeleteLoaiSach);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
