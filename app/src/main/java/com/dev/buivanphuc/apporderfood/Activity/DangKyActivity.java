package com.dev.buivanphuc.apporderfood.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.dev.buivanphuc.apporderfood.DAO.NhanVienDAO;
import com.dev.buivanphuc.apporderfood.DTO.NhanVienDTO;
import com.dev.buivanphuc.apporderfood.Database.CreateDatabase;
import com.dev.buivanphuc.apporderfood.Fragment.DatePickerFragment;
import com.dev.buivanphuc.apporderfood.R;

public class DangKyActivity extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener {

    EditText edtTenDangNhap, edMatKhau, edNgaSinh, edCMND;
    Button btnDongY, btnThoat;
    RadioGroup rgGioiTinh;

    String sGioiTinh = "";
    NhanVienDAO nhanVienDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dangky);
        addControls();
        CreateDatabase createDatabase = new CreateDatabase(this);
        createDatabase.open();
        nhanVienDAO = new NhanVienDAO(this);
    }

    private void addControls() {
        edtTenDangNhap = findViewById(R.id.etTenDangNhap);
        edMatKhau = findViewById(R.id.etMatKhau);
        edNgaSinh = findViewById(R.id.etNgaySinh);
        edCMND = findViewById(R.id.etCMT);
        rgGioiTinh = findViewById(R.id.rgGioiTinh);

        btnDongY = findViewById(R.id.btnDangKy);
        btnThoat = findViewById(R.id.btnThoat);

        btnDongY.setOnClickListener(this);
        btnThoat.setOnClickListener(this);
        edNgaSinh.setOnFocusChangeListener(this);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btnDangKy:
                String tenDangNhap = edtTenDangNhap.getText().toString().trim();
                String matKhau = edMatKhau.getText().toString().trim();

                switch (rgGioiTinh.getCheckedRadioButtonId()) {
                    case R.id.rbNam:
                        sGioiTinh = "Nam";
                        break;
                    case R.id.rbNu:
                        sGioiTinh = "Nữ";
                        break;
                }

                String sNgaySinh = edNgaSinh.getText().toString();
                String CMND = edCMND.getText().toString();
                if (tenDangNhap.isEmpty()) {
                    Toast.makeText(DangKyActivity.this, "Vui lòng nhập tên đăng nhập", Toast.LENGTH_SHORT).show();
                } else {
                    if (matKhau.isEmpty() || CMND.isEmpty() || sNgaySinh.isEmpty()) {
                        Toast.makeText(DangKyActivity.this, "Vui lòng kiểm tra lại thông tin", Toast.LENGTH_SHORT).show();
                    } else {

                        NhanVienDTO nhanVienDTO = new NhanVienDTO();
                        nhanVienDTO.setGIOITINH(sGioiTinh);
                        nhanVienDTO.setCMND(Integer.parseInt(CMND));
                        nhanVienDTO.setMATKHAU(matKhau);
                        nhanVienDTO.setTENDN(tenDangNhap);
                        nhanVienDTO.setNGAYSINH(sNgaySinh);

                        long kiemtra = nhanVienDAO.ThemNhanVien(nhanVienDTO);
                        if (kiemtra != 0) {
                            Toast.makeText(this, "Thêm thành công", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(this, "Thêm thất bại", Toast.LENGTH_LONG).show();
                        }
                    }
                }
                break;
            case R.id.btnThoat:
                finish();
                break;
        }
    }

    @Override
    public void onFocusChange(View view, boolean hasFocus) {
        int id = view.getId();
        switch (id) {

            case R.id.etNgaySinh:
                DatePickerFragment datePickerFragment11 = new DatePickerFragment();
                datePickerFragment11.show(getSupportFragmentManager(), "ngaysinh");
                break;
        }

    }
}
