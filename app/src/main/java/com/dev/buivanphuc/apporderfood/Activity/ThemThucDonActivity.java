package com.dev.buivanphuc.apporderfood.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dev.buivanphuc.apporderfood.Adapter.AdapterHienThiLoaiMonAn;
import com.dev.buivanphuc.apporderfood.DAO.LoaiMonAnDAO;
import com.dev.buivanphuc.apporderfood.DAO.MonAnDAO;
import com.dev.buivanphuc.apporderfood.DTO.LoaiMonAnDTO;
import com.dev.buivanphuc.apporderfood.DTO.MonAnDTO;
import com.dev.buivanphuc.apporderfood.R;

import java.util.ArrayList;
import java.util.List;

public class ThemThucDonActivity extends AppCompatActivity implements View.OnClickListener {

    public static int REQUEST_CODE_THEMLOAITHUCDON = 113;
    public static int REQUEST_CODE_MOHINH = 15;
    ImageButton btnThemLoaiThucDon;
    ImageView imgHinhThucDon;
    Button btnDongYThemThucDon, btnThoatThemThucDon;
    EditText edtTenMonAn, edtGiaTien;
    Spinner spnLoaiThucDon;
    LoaiMonAnDAO loaiMonAnDAO;
    List<LoaiMonAnDTO> loaiMonAnDTOList;
    AdapterHienThiLoaiMonAn adapterHienThiLoaiMonAn;

    String duongDanHinh = "";
    MonAnDAO monAnDAO;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_themthucdon);
        btnThemLoaiThucDon = findViewById(R.id.btnThemLoaiThucDon);
        spnLoaiThucDon = findViewById(R.id.spnLoaiThucDon);
        imgHinhThucDon = findViewById(R.id.imgHinhThucDon);
        btnDongYThemThucDon = findViewById(R.id.btnDongYThemThucDon);
        btnThoatThemThucDon = findViewById(R.id.btnThoatThemThucDon);
        edtTenMonAn = findViewById(R.id.edtTenMonAn);
        edtGiaTien = findViewById(R.id.edtGiaTien);

        btnThemLoaiThucDon.setOnClickListener(this);
        imgHinhThucDon.setOnClickListener(this);
        btnDongYThemThucDon.setOnClickListener(this);
        btnThoatThemThucDon.setOnClickListener(this);
        loaiMonAnDAO = new LoaiMonAnDAO(this);
        monAnDAO = new MonAnDAO(this);
        HienThiLoaiMonAn();
    }

    private void HienThiLoaiMonAn() {
        loaiMonAnDTOList = loaiMonAnDAO.LayDanhSachLoaiMonAn();
        adapterHienThiLoaiMonAn = new AdapterHienThiLoaiMonAn(ThemThucDonActivity.this, R.layout.custom_layout_spinnerloaimonan, loaiMonAnDTOList);
        spnLoaiThucDon.setAdapter(adapterHienThiLoaiMonAn);
        adapterHienThiLoaiMonAn.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btnThemLoaiThucDon:
                Intent iThemLoaiMonAn = new Intent(ThemThucDonActivity.this, ThemLoaiThucDonActivity.class);
                startActivityForResult(iThemLoaiMonAn, REQUEST_CODE_THEMLOAITHUCDON);
                break;
            case R.id.imgHinhThucDon:
                Intent iMoHinh = new Intent();
                iMoHinh.setType("image/*");
                iMoHinh.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(iMoHinh, "Chọn hình thực đơn"), REQUEST_CODE_MOHINH);
                break;
            case R.id.btnDongYThemThucDon:
                int vitri = spnLoaiThucDon.getSelectedItemPosition();
                int maloai = loaiMonAnDTOList.get(vitri).getMaLoai();
                String tenMonAn = edtTenMonAn.getText().toString();
                String giaTien = edtGiaTien.getText().toString();

                if (!tenMonAn.isEmpty() || !giaTien.isEmpty()) {
                    MonAnDTO monAnDTO = new MonAnDTO();
                    monAnDTO.setTenMonAN(tenMonAn);
                    monAnDTO.setGiaTien(giaTien);
                    monAnDTO.setMaLoai(maloai);
                    monAnDTO.setHinhAnh(duongDanHinh);
                    boolean kiemtra= monAnDAO.ThemMonAn(monAnDTO);
                    if(kiemtra)
                    {
                        Toast.makeText(ThemThucDonActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(ThemThucDonActivity.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ThemThucDonActivity.this, "Kiểm tra lại thông tin", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.btnThoatThemThucDon:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_THEMLOAITHUCDON) {
            if (resultCode == RESULT_OK) {
                Intent dulieu = data;
                boolean kiemtra = dulieu.getBooleanExtra("kiemtraloaithucdon", false);
                if (kiemtra) {
                    Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    HienThiLoaiMonAn();
                } else {
                    Toast.makeText(this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        } else if (requestCode == REQUEST_CODE_MOHINH && data != null) {
            if (resultCode == RESULT_OK) {

                duongDanHinh = data.getData().toString();
                Glide.with(ThemThucDonActivity.this).load(data.getData()).into(imgHinhThucDon);
            }
        }
    }
}
