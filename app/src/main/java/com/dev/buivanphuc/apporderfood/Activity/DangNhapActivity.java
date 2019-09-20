package com.dev.buivanphuc.apporderfood.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dev.buivanphuc.apporderfood.DAO.NhanVienDAO;
import com.dev.buivanphuc.apporderfood.R;

public class DangNhapActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnDongYDN, btnDangKyDN;
    EditText edtTenDangNhap_DN, edtMatKhauDangNhap;
    NhanVienDAO nhanVienDAO;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dangnhap);

        btnDongYDN = findViewById(R.id.btnDangNhap);
        btnDangKyDN = findViewById(R.id.btnDangKiTK);
        edtTenDangNhap_DN = findViewById(R.id.edtTenDangNhap_DN);
        edtMatKhauDangNhap = findViewById(R.id.edtMatKhauDangNhap);

        nhanVienDAO = new NhanVienDAO(this);
        HienThiButton();
        btnDangKyDN.setOnClickListener(this);
        btnDongYDN.setOnClickListener(this);
    }

    private void HienThiButton() {
        boolean kiemtra = nhanVienDAO.KiemTraNhanVien();
        if (kiemtra) {
            btnDangKyDN.setVisibility(View.GONE);
            btnDongYDN.setVisibility(View.VISIBLE);
        } else {
            btnDongYDN.setVisibility(View.GONE);
            btnDangKyDN.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        HienThiButton();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btnDangKiTK:
                Intent iDangKy = new Intent(DangNhapActivity.this, DangKyActivity.class);
                startActivity(iDangKy);
                break;
            case R.id.btnDangNhap:
                XuLyDangNhap();
                break;
        }
    }

    private void XuLyDangNhap() {
        String tenDangNhap = edtTenDangNhap_DN.getText().toString().trim();
        String matKhau = edtMatKhauDangNhap.getText().toString().trim();
        int manv = nhanVienDAO.KiemTraDangNhap(tenDangNhap, matKhau);
        if (manv!=0) {
            Toast.makeText(DangNhapActivity.this, "đăng nhập thành công", Toast.LENGTH_SHORT).show();
            Intent iTrangChu = new Intent(DangNhapActivity.this, TrangChuActivity.class);
            iTrangChu.putExtra("tendangnhap",edtTenDangNhap_DN.getText().toString());
            iTrangChu.putExtra("manv",manv);
            startActivity(iTrangChu);
        } else {
            Toast.makeText(DangNhapActivity.this, "đăng nhập thất bại", Toast.LENGTH_SHORT).show();
        }
    }
}
