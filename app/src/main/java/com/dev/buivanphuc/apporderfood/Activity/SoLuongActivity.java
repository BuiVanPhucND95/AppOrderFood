package com.dev.buivanphuc.apporderfood.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dev.buivanphuc.apporderfood.DAO.GoiMonDAO;
import com.dev.buivanphuc.apporderfood.DTO.ChiTietGoiMonDTO;
import com.dev.buivanphuc.apporderfood.R;

public class SoLuongActivity extends AppCompatActivity {
    EditText edtThemSoLuongMonAn;
    Button btnDongYThemSoLuongMonAn;

    int maban, mamonan;
    GoiMonDAO goiMonDAO;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_themsoluongmonan);
        setTitle("Số lượng");
        btnDongYThemSoLuongMonAn = findViewById(R.id.btnDongYThemSoLuongMonAn);
        edtThemSoLuongMonAn = findViewById(R.id.edtThemSoLuongMonAn);

        maban = getIntent().getIntExtra("maban", 0);
        mamonan = getIntent().getIntExtra("mamonan", 0);

        goiMonDAO = new GoiMonDAO(this);
        btnDongYThemSoLuongMonAn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                XuLyThemSoLuong();
            }
        });

    }

    private void XuLyThemSoLuong() {
        int magoimon = (int) goiMonDAO.LayMaGoiMonTheoMaBan(maban, "false");
        boolean kiemtra = goiMonDAO.KiemTraMonAnDaTonTai(magoimon, mamonan);
        if (kiemtra) {
            // Cập nhật món ăn đã tồn tại
            int soLuongCu = goiMonDAO.LaySoLuongMonAnTheoMaGoiMon(magoimon, mamonan);
            int soLuongMoi = Integer.parseInt(edtThemSoLuongMonAn.getText().toString());

            int TongSoLuong = soLuongCu + soLuongMoi;
            ChiTietGoiMonDTO chiTietGoiMonDTO = new ChiTietGoiMonDTO();
            chiTietGoiMonDTO.setMaGoiMon(magoimon);
            chiTietGoiMonDTO.setMaMonAn(mamonan);
            chiTietGoiMonDTO.setSoLuong(TongSoLuong);
            boolean ktCapNhat = goiMonDAO.CapNhatSoLuong(chiTietGoiMonDTO);

            if (ktCapNhat) {
                // Thêm thành công
                Toast.makeText(SoLuongActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
            } else {
                // Thêm thất bại
                Toast.makeText(SoLuongActivity.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Thêm mới món ăn
            int soLuongGoiMon = Integer.parseInt(edtThemSoLuongMonAn.getText().toString());
            ChiTietGoiMonDTO chiTietGoiMonDTO = new ChiTietGoiMonDTO();
            chiTietGoiMonDTO.setMaGoiMon(magoimon);
            chiTietGoiMonDTO.setMaMonAn(mamonan);
            chiTietGoiMonDTO.setSoLuong(soLuongGoiMon);
            boolean ktCapNhat = goiMonDAO.ThemChiTietGoiMon(chiTietGoiMonDTO);

            if (ktCapNhat) {
                // Thêm thành công
                Toast.makeText(SoLuongActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
            } else {
                // Thêm thất bại
                Toast.makeText(SoLuongActivity.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
            }
        }
        finish();
    }
}
