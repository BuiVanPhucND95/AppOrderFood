package com.dev.buivanphuc.apporderfood.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.dev.buivanphuc.apporderfood.DTO.LoaiMonAnDTO;
import com.dev.buivanphuc.apporderfood.Database.CreateDatabase;

import java.util.ArrayList;
import java.util.List;

public class LoaiMonAnDAO {

    SQLiteDatabase database;

    public LoaiMonAnDAO(Context context) {
        CreateDatabase createDatabase = new CreateDatabase(context);
        database = createDatabase.open();
    }

    public boolean ThemLoaiMonAn(String tenLoai) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_LOAIMONAN_TENLOAIMONAN, tenLoai);

        long kiemtra = database.insert(CreateDatabase.TB_LOAIMONAN, null, contentValues);
        if (kiemtra != 0) {
            return true;
        } else {
            return false;
        }
    }

    public List<LoaiMonAnDTO> LayDanhSachLoaiMonAn() {
        List<LoaiMonAnDTO> loaiMonAnDTOList = new ArrayList<>();
        String truyvan = "select * from " + CreateDatabase.TB_LOAIMONAN;
        Cursor cursor = database.rawQuery(truyvan, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            LoaiMonAnDTO loaiMonAnDTO = new LoaiMonAnDTO();
            loaiMonAnDTO.setTenLoai(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_LOAIMONAN_TENLOAIMONAN)));
            loaiMonAnDTO.setMaLoai(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_LOAIMONAN_MALOAIMONAN)));

            loaiMonAnDTOList.add(loaiMonAnDTO);
            cursor.moveToNext();
        }
        return loaiMonAnDTOList;
    }

    public String LayLinkHinhAnhLoaiMonAn(int maLoaiMonAn) {
        String linkHA = "";
        String truyVan = "SELECT * FROM " + CreateDatabase.TB_MONAN + " WHERE " + CreateDatabase.TB_MONAN_MALOAIMONAN + " = ' " + maLoaiMonAn + " ' "
                + " AND " + CreateDatabase.TB_MONAN_HINHANHMONAN + " != '' ORDER BY " + CreateDatabase.TB_MONAN_MAMONAN + " LIMIT 1";
        Cursor cursor = database.rawQuery(truyVan, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            linkHA = cursor.getString(cursor.getColumnIndexOrThrow(CreateDatabase.TB_MONAN_HINHANHMONAN));

            cursor.moveToNext();
        }
        return linkHA;
    }

    public boolean CapNhatLoaiMonAn(int maloai, String tenloai) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_LOAIMONAN_TENLOAIMONAN, tenloai);
        long kiemtra = database.update(CreateDatabase.TB_LOAIMONAN, contentValues, CreateDatabase.TB_LOAIMONAN_MALOAIMONAN + " = " + maloai, null);

        if (kiemtra != 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean XoaLoaiMonAn(int maloai) {
        long kiemtra = database.delete(CreateDatabase.TB_LOAIMONAN, CreateDatabase.TB_LOAIMONAN_MALOAIMONAN + " = " + maloai, null);
        if (kiemtra != 0) {
            return true;
        } else {
            return false;
        }
    }
}
