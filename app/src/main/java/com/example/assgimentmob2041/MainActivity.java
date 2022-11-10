package com.example.assgimentmob2041;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assgimentmob2041.DAO.ThuThuDAO;
import com.example.assgimentmob2041.Demo.Demo01;
import com.example.assgimentmob2041.Modole.ThuThu;
import com.example.assgimentmob2041.fragment.ChangePassFragment;
import com.example.assgimentmob2041.fragment.FrgThanhVien;
import com.example.assgimentmob2041.fragment.FrmAddUser;
import com.example.assgimentmob2041.fragment.FrmLoaiSach;
import com.example.assgimentmob2041.fragment.FrmSach;
import com.example.assgimentmob2041.fragment.FrmThongKe;
import com.example.assgimentmob2041.fragment.FrmTop;
import com.example.assgimentmob2041.fragment.QLPhieuMuonFra;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    View view123;
    TextView textView;
    ThuThuDAO thuThuDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.DrawerLayout);
        toolbar = findViewById(R.id.toolbar1);
        thuThuDAO = new ThuThuDAO(this);
        //set toolbar thay thế cho action bar
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);
        actionBar.setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = findViewById(R.id.nvView);
        //hiển thị user trên header
        view123 = navigationView.getHeaderView(0);
        textView = view123.findViewById(R.id.tvUser);

        String userName = getIntent().getStringExtra("user");
//        View headerView = navigationView.getHeaderView(0);
        ThuThu thuThu = thuThuDAO.getID(userName);
        textView.setText("Welcome to " + thuThu.getTen());
        if (!userName.equals("admin")) {
            navigationView.getMenu().findItem(R.id.sub_nguoiDung).setVisible(false);
        } else {
            navigationView.getMenu().findItem(R.id.sub_nguoiDung).setVisible(true);
        }


//        navigationView.getMenu().findItem(R.id.sub_nguoiDung).setVisible(false);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager manager = getSupportFragmentManager();

                switch (item.getItemId()) {
                    case R.id.nav_phieuMuon:
                        setTitle("Quản Lý Phiếu mượn");
                        Fragment fragment = new QLPhieuMuonFra();
                        manager.beginTransaction().replace(R.id.frameLayout, fragment).commit();
                        break;
                    case R.id.nav_loaiSach:
                        setTitle("Quản Lý Loại Sách");
                        Fragment fragmentLoaiSach = new FrmLoaiSach();
                        manager.beginTransaction().replace(R.id.frameLayout, fragmentLoaiSach).commit();
                        break;
                    case R.id.nav_Sach:
                        setTitle("Quản Lý Sách");
                        Fragment frmSach = new FrmSach();
                        manager.beginTransaction().replace(R.id.frameLayout, frmSach).commit();
                        break;
                    case R.id.nav_thanhVien:
                        setTitle("Quản Lý Thành Viên");
                        Fragment fragment12 = new FrgThanhVien();
                        manager.beginTransaction().replace(R.id.frameLayout, fragment12).commit();
                        break;
                    case R.id.sub_top:
                        setTitle("Top 10 ");
                        Fragment fragmentTop = new FrmTop();
                        manager.beginTransaction().replace(R.id.frameLayout , fragmentTop).commit();
                        break;
                    case R.id.sub_doanhThu:
                        setTitle("Quản Lý Doanh Thu");
                        Fragment fragemtDoanhThu = new FrmThongKe();
                        manager.beginTransaction().replace(R.id.frameLayout , fragemtDoanhThu).commit();
                        break;
                    case R.id.sub_nguoiDung:
                        setTitle("Thêm Người Dùng");
                        Fragment fragmentAddUser = new FrmAddUser();
                        manager.beginTransaction().replace(R.id.frameLayout , fragmentAddUser).commit();
                        break;
                    case R.id.sub_matKhau:
                        setTitle("Đổi Mật Khẩu");
                        Fragment fragment1 = new ChangePassFragment();
                        manager.beginTransaction().replace(R.id.frameLayout, fragment1).commit();
                        break;
                    case R.id.sub_dangXuat:
                        setTitle("Đăng Xuất   ");
                        Intent inLogin = new Intent(MainActivity.this, Login.class);
                        startActivity(inLogin);
                        finish();
                        break;
                }


                drawerLayout.closeDrawers();
                return false;
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }
}