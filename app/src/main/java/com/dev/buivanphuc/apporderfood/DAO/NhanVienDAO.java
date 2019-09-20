package com.dev.buivanphuc.apporderfood.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.dev.buivanphuc.apporderfood.DTO.NhanVienDTO;
import com.dev.buivanphuc.apporderfood.Database.CreateDatabase;

import java.util.ArrayList;
import java.util.List;

public class NhanVienDAO {

    SQLiteDatabase database;

    public NhanVienDAO(Context context) {
        CreateDatabase createDatabase = new CreateDatabase(context);
        database = createDatabase.open();
    }

    public long ThemNhanVien(NhanVienDTO nhanVienDTO) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(CreateDatabase.TB_NHANVIEN_TENNHANVIEN, nhanVienDTO.getTENDN());
        contentValues.put(CreateDatabase.TB_NHANVIEN_CMTNHANVIEN, nhanVienDTO.getCMND());
        contentValues.put(CreateDatabase.TB_NHANVIEN_GIOITINHNHANVIEN, nhanVienDTO.getGIOITINH());
        contentValues.put(CreateDatabase.TB_NHANVIEN_NGAYSINHNHANVIEN, nhanVienDTO.getNGAYSINH());
        contentValues.put(CreateDatabase.TB_NHANVIEN_MATKHAUNHANVIEN, nhanVienDTO.getMATKHAU());

        long kiemTra = database.insert(CreateDatabase.TB_NHANVIEN, null, contentValues);

        return kiemTra;
    }

    public boolean KiemTraNhanVien() {
        String truyvan = "select * from " + CreateDatabase.TB_NHANVIEN;
        Cursor cursor = database.rawQuery(truyvan, null);

        if (cursor.getCount() != 0) {
            return true;
        } else {
            return false;
        }
    }

    public int KiemTraDangNhap(String tendangnhap, String matkhau) {
        int manv = 0;
        tendangnhap = "'" + tendangnhap + "'";
        matkhau = "'" + matkhau + "'";
        String truyvan = "select * from " + CreateDatabase.TB_NHANVIEN + " where " + CreateDatabase.TB_NHANVIEN_TENNHANVIEN + " = " + tendangnhap
                + " and " + CreateDatabase.TB_NHANVIEN_MATKHAUNHANVIEN + " = " + matkhau;
        Cursor cursor = database.rawQuery(truyvan, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            manv = cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_NHANVIEN_MANHANVIEN));
            cursor.moveToNext();
        }
        return manv;
    }

    public List<NhanVienDTO> LayDanhSachNhanVien() {
        List<NhanVienDTO> nhanVienDTOList = new ArrayList<>();
        String truyvan = "select * from " + CreateDatabase.TB_NHANVIEN;
        Cursor cursor = database.rawQuery(truyvan, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            NhanVienDTO nhanVienDTO = new NhanVienDTO();

            nhanVienDTO.setGIOITINH(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_NHANVIEN_GIOITINHNHANVIEN)));
            nhanVienDTO.setCMND(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_NHANVIEN_CMTNHANVIEN)));
            nhanVienDTO.setMANV(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_NHANVIEN_MANHANVIEN)));
            nhanVienDTO.setMATKHAU(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_NHANVIEN_MATKHAUNHANVIEN)));
            nhanVienDTO.setNGAYSINH(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_NHANVIEN_NGAYSINHNHANVIEN)));
            nhanVienDTO.setTENDN(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_NHANVIEN_TENNHANVIEN)));

            nhanVienDTOList.add(nhanVienDTO);

            cursor.moveToNext();
        }
        return nhanVienDTOList;
    }

    public boolean XoaNhanVien(int manv) {
        long kiemtra = database.delete(CreateDatabase.TB_NHANVIEN, CreateDatabase.TB_NHANVIEN_MANHANVIEN + " = " + manv, null);
        if (kiemtra != 0) {
            return true;
        } else {
            return false;
        }
    }
}
