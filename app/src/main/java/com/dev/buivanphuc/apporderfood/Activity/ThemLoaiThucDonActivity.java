package com.dev.buivanphuc.apporderfood.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.dev.buivanphuc.apporderfood.DAO.LoaiMonAnDAO;
import com.dev.buivanphuc.apporderfood.R;

public class ThemLoaiThucDonActivity extends AppCompatActivity {
    EditText edtThemLoaiThucDon;

    Button btnDongYThemLoaiThucDon;
    LoaiMonAnDAO loaiMonAnDAO;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_themloaithucdon);
        setTitle("Thêm loại món ăn");
        btnDongYThemLoaiThucDon = findViewById(R.id.btnDongYThemLoaiThucDon);
        edtThemLoaiThucDon = findViewById(R.id.edtThemLoaiThucDon);

        loaiMonAnDAO = new LoaiMonAnDAO(this);
        btnDongYThemLoaiThucDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ThemLoaiThucDon();
            }
        });
    }

    private void ThemLoaiThucDon() {
        String sTenLoaiThucDon = edtThemLoaiThucDon.getText().toString().trim();
        if (sTenLoaiThucDon.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập dữ liệu", Toast.LENGTH_SHORT).show();
        } else {
            boolean kiemtra = loaiMonAnDAO.ThemLoaiMonAn(sTenLoaiThucDon);
            Intent iDuLieu = new Intent();

            iDuLieu.putExtra("kiemtraloaithucdon", kiemtra);
            setResult(RESULT_OK, iDuLieu);
            finish();

        }
    }
}
