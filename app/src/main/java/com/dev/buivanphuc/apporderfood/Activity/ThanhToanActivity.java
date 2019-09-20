package com.dev.buivanphuc.apporderfood.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.dev.buivanphuc.apporderfood.Adapter.AdapterHienThiThanhToan;
import com.dev.buivanphuc.apporderfood.DAO.BanAnDAO;
import com.dev.buivanphuc.apporderfood.DAO.GoiMonDAO;
import com.dev.buivanphuc.apporderfood.DTO.ThanhToanDTO;
import com.dev.buivanphuc.apporderfood.Fragment.HienThiBanAnFragment;
import com.dev.buivanphuc.apporderfood.R;

import java.text.Format;
import java.util.List;

public class ThanhToanActivity extends AppCompatActivity implements View.OnClickListener {
    GridView gvDanhSachThanhToan;
    TextView txtTongTien;
    Button btnThanhTOan, btnThoatThanhToan;
    GoiMonDAO goiMonDAO;
    List<ThanhToanDTO> thanhToanDTOList;
    AdapterHienThiThanhToan adapter;
    long tongTien = 0;
    BanAnDAO banAnDAO;
    int maban = 0;
    FragmentManager faFragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_thanhtoan);

        btnThoatThanhToan = findViewById(R.id.btnThoatThanhToan);
        btnThanhTOan = findViewById(R.id.btnThanhTOan);
        gvDanhSachThanhToan = findViewById(R.id.gvDanhSachThanhToan);
        txtTongTien = findViewById(R.id.txtTongTien);
        goiMonDAO = new GoiMonDAO(this);
        banAnDAO = new BanAnDAO(this);

        faFragmentManager = getSupportFragmentManager();

        maban = getIntent().getIntExtra("maban", 0);

        if (maban != 0) {
            HienThiThanhToan();
            for (int i = 0; i < thanhToanDTOList.size(); i++) {
                int soLuong = thanhToanDTOList.get(i).getSoLuong();
                int giaTien = thanhToanDTOList.get(i).getGiaTien();

                tongTien += (soLuong * giaTien);
            }

            txtTongTien.setText(tongTien + "");

        }
        btnThanhTOan.setOnClickListener(this);
        btnThoatThanhToan.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btnThanhTOan:
                boolean kiemtra = banAnDAO.CapNhatTinhTrangBan(maban, "false");
                boolean kiemtraGoiMon = goiMonDAO.CapNhatTrangThaiGoiMonTheoMaBan(maban, "true");
                if (kiemtra && kiemtraGoiMon) {
                    Toast.makeText(ThanhToanActivity.this, "Thanh toán thành công", Toast.LENGTH_SHORT).show();
                    HienThiThanhToan();
                } else {
                    Toast.makeText(ThanhToanActivity.this, "Thanh toán thất bại", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnThoatThanhToan:
               finish();
                break;
        }
    }

    private void HienThiThanhToan() {
        int magoimon = (int) goiMonDAO.LayMaGoiMonTheoMaBan(maban, "false");
        thanhToanDTOList = goiMonDAO.LayDanhSachMonAnTheoMaGoiMon(magoimon);

        adapter = new AdapterHienThiThanhToan(this, R.layout.custom_layout_thanhtoan, thanhToanDTOList);
        gvDanhSachThanhToan.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
