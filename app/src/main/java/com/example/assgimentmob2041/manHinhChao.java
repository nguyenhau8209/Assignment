package com.example.assgimentmob2041;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

public class manHinhChao extends AppCompatActivity {
ImageView imgview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_chao);
        imgview = findViewById(R.id.img_view);
        imgview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(manHinhChao.this, Login.class));
                finish();
            }
        });
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                    startActivity(new Intent(manHinhChao.this , Login.class));
//                    finish();
//            }
//        },2000);
    }
}