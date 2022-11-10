package com.example.assgimentmob2041.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.assgimentmob2041.Modole.Top;
import com.example.assgimentmob2041.R;
import com.example.assgimentmob2041.fragment.FrmTop;

import java.util.ArrayList;

public class AdapterTop extends ArrayAdapter<Top> {
   private Context context;
   FrmTop frmTop;
   ArrayList<Top> listtop;
   TextView tvSach , tvSoLuong;
   ImageView imgView;

    public AdapterTop(@NonNull Context context, FrmTop frmTop, ArrayList<Top> listtop) {
        super(context, 0 , listtop);
        this.context = context;
        this.frmTop = frmTop;
        this.listtop = listtop;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_top , null);

        }
        final Top item = listtop.get(position);
        if(item != null){
            tvSach = view.findViewById(R.id.tvSachTop);
            tvSach.setText("Sách: " + item.getTenSach());
            tvSoLuong = view.findViewById(R.id.tvSoLuongTop);
            tvSoLuong.setText("Số lượng: " + item.getSoLuong());
        }

        return view;
    }
}
