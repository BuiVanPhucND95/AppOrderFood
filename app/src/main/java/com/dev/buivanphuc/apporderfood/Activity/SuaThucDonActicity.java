package com.dev.buivanphuc.apporderfood.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dev.buivanphuc.apporderfood.DAO.LoaiMonAnDAO;
import com.dev.buivanphuc.apporderfood.R;

public class SuaThucDonActicity extends AppCompatActivity {
    EditText edtTenLoaiMonAn;
    Button btnDongY;
    int maloai;
    LoaiMonAnDAO loaiMonAnDAO;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_suathucdon);
        setTitle("Sửa loại món ănl");
        edtTenLoaiMonAn = findViewById(R.id.edtSuaLoaiMonAn);
        btnDongY = findViewById(R.id.btnDongYSuaLoaiMonAn);
        loaiMonAnDAO = new LoaiMonAnDAO(this);
        maloai = getIntent().getIntExtra("maloai", 0);
        btnDongY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                XuLySuaMonAn();
            }
        });

    }

    private void XuLySuaMonAn() {
        String tenLoaiMonAn = edtTenLoaiMonAn.getText().toString();
        if (!tenLoaiMonAn.isEmpty()) {
            boolean kiemtra = loaiMonAnDAO.CapNhatLoaiMonAn(maloai, tenLoaiMonAn);
            if (kiemtra) {
                Toast.makeText(SuaThucDonActicity.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(SuaThucDonActicity.this, "Sửa thất bại", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(SuaThucDonActicity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        }
    }
}
