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
import androidx.recyclerview.widget.RecyclerView;

import com.example.assgimentmob2041.DAO.ThuThuDAO;
import com.example.assgimentmob2041.Modole.ItemClickThuTHu;
import com.example.assgimentmob2041.Modole.ThuThu;
import com.example.assgimentmob2041.R;
import com.example.assgimentmob2041.fragment.FrmAddUser;

import java.util.List;

public class AdapterAddUser extends RecyclerView.Adapter<AdapterAddUser.ViewHolder> {
    private Context context;
    List<ThuThu> listThuThu;
    ItemClickThuTHu itemClickThuTHu;
    FrmAddUser frmAddUser = new FrmAddUser();
    public AdapterAddUser(Context context, List<ThuThu> listThuThu, ItemClickThuTHu itemClickThuTHu) {
        this.context = context;
        this.listThuThu = listThuThu;
        this.itemClickThuTHu = itemClickThuTHu;
    }

    @Override
    public AdapterAddUser.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_add_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterAddUser.ViewHolder holder, int position) {
        holder.tvMa.setText("Tài Khoản: " + listThuThu.get(position).getMaTT());
        holder.tvTen.setText("Tên Người Dùng: " + listThuThu.get(position).getTen());
        holder.tvPass.setText("Mật Khẩu: " + listThuThu.get(position).getMatKhau());

        holder.imdDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                frmAddUser.delete(String.valueOf(listThuThu.get(position).getMaTT()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return listThuThu.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTen, tvMa, tvPass;
        ThuThuDAO thuThuDAO = new ThuThuDAO(context);
        ImageView imdDel;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMa = itemView.findViewById(R.id.txtUser);
            tvTen = itemView.findViewById(R.id.txtName);
            tvPass = itemView.findViewById(R.id.txtPass);
            imdDel = itemView.findViewById(R.id.btnXoaUser);


        }
    }
}
