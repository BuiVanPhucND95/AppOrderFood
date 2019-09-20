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

import com.dev.buivanphuc.apporderfood.DAO.BanAnDAO;
import com.dev.buivanphuc.apporderfood.R;

public class ThemBanAnActivity extends AppCompatActivity {
    EditText edtThemBanAn;
    Button btnDongYThemBanAn;

    BanAnDAO banAnDAO;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_thembanan);
        setTitle("Thêm bàn ăn");

        edtThemBanAn = findViewById(R.id.edtThemBanAn);
        btnDongYThemBanAn = findViewById(R.id.btnDongYThemBanAn);

        banAnDAO = new BanAnDAO(this);
        btnDongYThemBanAn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ThemBanAn();
            }
        });
    }

    private void ThemBanAn() {
        String sTenBanAn = edtThemBanAn.getText().toString().trim();
        if (!sTenBanAn.isEmpty()) {
            Log.d("TenBanAn", sTenBanAn);
            boolean kiemtra = banAnDAO.ThemBanAn(sTenBanAn);
            Intent intent = new Intent();
            intent.putExtra("ketquathem", kiemtra);
            setResult(RESULT_OK, intent);
            finish();
        } else {
            Toast.makeText(this, "không được để trống", Toast.LENGTH_SHORT).show();
        }
    }
}
