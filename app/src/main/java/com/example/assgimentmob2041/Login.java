package com.example.assgimentmob2041;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.assgimentmob2041.DAO.ThuThuDAO;
import com.google.android.material.textfield.TextInputEditText;

public class Login extends AppCompatActivity {
    TextInputEditText edUser, edPass;
    Button btnLogin, btnCancle;
    CheckBox chk;
    String User, Pass;
    ThuThuDAO thuThuDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Đăng Nhập");
        edUser = findViewById(R.id.edUser);
        edPass = findViewById(R.id.edPass);
        btnLogin = findViewById(R.id.btnLogin);
        btnCancle = findViewById(R.id.btnHuy);
        chk = findViewById(R.id.chk);
        thuThuDAO = new ThuThuDAO(this);
        //đọc user and pass trong sharedpreferences
        SharedPreferences sharedPreferences = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        String user = sharedPreferences.getString("USERNAME", "");
        String pass = sharedPreferences.getString("PASSWORD", "");
        Boolean rem = sharedPreferences.getBoolean("REMEMBER", false);
        //
        edUser.setText(user);
        edPass.setText(pass);
        chk.setChecked(rem);

    btnCancle.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            edUser.setText("");
            edPass.setText("");
        }
    });
    btnLogin.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            checkLogin();
        }
    });
    }
    //hàm ghi nhớ u and p
    public void rememberUser(String u, String p, boolean status) {
        SharedPreferences sharedPreferences = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        if(!status){
            //nết k check vào chk
            edit.clear();
        }else{
            //check r thì lưu data
            edit.putString("USERNAME" , u);
            edit.putString("PASSWORD" , p);
            edit.putBoolean("REMEMBER",status);
        }
        //lưu data
        edit.commit();
    }
    public void checkLogin(){
        User = edUser.getText().toString();
        Pass = edPass.getText().toString();
        if(User.isEmpty() || Pass.isEmpty()){
            Toast.makeText(this, "Dữ Liệu trống", Toast.LENGTH_SHORT).show();
        }else{
            if(thuThuDAO.checkLogin(User , Pass) > 0){
                Toast.makeText(this, "Login Thành Công", Toast.LENGTH_SHORT).show();
                rememberUser(User , Pass , chk.isChecked());
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("user",User);
                startActivity(intent);
                finish();
            }else{
                Toast.makeText(this, "Login Không Thành Công", Toast.LENGTH_SHORT).show();
            }
        }
    }
}