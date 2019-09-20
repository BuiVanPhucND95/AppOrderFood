package com.dev.buivanphuc.apporderfood.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dev.buivanphuc.apporderfood.DAO.BanAnDAO;
import com.dev.buivanphuc.apporderfood.R;

public class SuaBanAnActivity extends AppCompatActivity {
    BanAnDAO banAnDAO;
    EditText edtTenBanAn;
    Button btnDongY;
    int maban;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_suabanan);
        setTitle("Sửa bàn ăn");
        edtTenBanAn = findViewById(R.id.edtSuaBanAn);
        btnDongY = findViewById(R.id.btnDongYSuaBanAn);
        maban = getIntent().getIntExtra("maban", 0);

        banAnDAO = new BanAnDAO(this);

        btnDongY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                XuLySuaBanAn();
            }
        });
    }

    private void XuLySuaBanAn() {
        String tenBan = edtTenBanAn.getText().toString().trim();
        if (!tenBan.isEmpty()) {
            boolean kiemtra = banAnDAO.CapNhatTenBan(maban, tenBan);
            if (kiemtra) {
                Toast.makeText(SuaBanAnActivity.this, "Thành công", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(SuaBanAnActivity.this, "Thất bại", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(SuaBanAnActivity.this, "Vui lòng nhập dữ liệu", Toast.LENGTH_SHORT).show();
        }

    }
}
