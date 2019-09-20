package com.dev.buivanphuc.apporderfood.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dev.buivanphuc.apporderfood.DAO.MonAnDAO;
import com.dev.buivanphuc.apporderfood.R;

public class SuaMonAnActivity extends AppCompatActivity {
    EditText edtTenMonAn, edtGiaTien;
    Button btnDongY;
    int mamonan;
    MonAnDAO monAnDAO;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_suamonan);
        setTitle("Sửa món ăn");
        edtTenMonAn = findViewById(R.id.edtSuaTenMonAn);
        edtGiaTien = findViewById(R.id.edtSuaGiaTienMonAn);
        btnDongY = findViewById(R.id.btnDongYSuaMonAn);
        monAnDAO = new MonAnDAO(this);
        mamonan = getIntent().getIntExtra("mamonan", 0);

        btnDongY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SuaMonAn();
            }
        });

    }

    private void SuaMonAn() {
       try {
           String tenMonAn = edtTenMonAn.getText().toString();
           String giaTien = edtGiaTien.getText().toString();
           if (!tenMonAn.isEmpty() || !giaTien.isEmpty()) {
               boolean kiemtra = monAnDAO.SuaMonAn(mamonan, tenMonAn, Integer.parseInt(giaTien));
               if (kiemtra) {
                   Toast.makeText(SuaMonAnActivity.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                   finish();
               } else {
                   Toast.makeText(SuaMonAnActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
               }
           }else {
               Toast.makeText(SuaMonAnActivity.this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
           }
       }catch (Exception x)
       {
           Toast.makeText(SuaMonAnActivity.this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
       }
    }
}
