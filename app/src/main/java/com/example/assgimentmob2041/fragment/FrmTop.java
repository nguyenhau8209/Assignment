package com.example.assgimentmob2041.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.assgimentmob2041.Adapter.AdapterTop;
import com.example.assgimentmob2041.DAO.PhieuMuonDAO;
import com.example.assgimentmob2041.Modole.Top;
import com.example.assgimentmob2041.R;

import java.util.ArrayList;


public class FrmTop extends Fragment {

    ListView listView;
    ArrayList<Top> listTop;
    AdapterTop adapterTop;

    public FrmTop() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_frm_top, container, false);
        listView = view.findViewById(R.id.lvTop);
        PhieuMuonDAO phieuMuonDAO = new PhieuMuonDAO(getActivity());
        listTop = (ArrayList<Top>)phieuMuonDAO.getTop();
        adapterTop = new AdapterTop(getActivity() , this , listTop);
        listView.setAdapter(adapterTop);
        return view;
    }
}