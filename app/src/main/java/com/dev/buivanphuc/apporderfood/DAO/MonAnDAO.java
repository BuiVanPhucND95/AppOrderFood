package com.dev.buivanphuc.apporderfood.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.dev.buivanphuc.apporderfood.DTO.MonAnDTO;
import com.dev.buivanphuc.apporderfood.Database.CreateDatabase;

import java.util.ArrayList;
import java.util.List;

public class MonAnDAO {

    SQLiteDatabase database;

    public MonAnDAO(Context context) {
        CreateDatabase createDatabase = new CreateDatabase(context);
        database = createDatabase.open();
    }

    public boolean ThemMonAn(MonAnDTO monAnDTO) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_MONAN_TENMONAN, monAnDTO.getTenMonAN());
        contentValues.put(CreateDatabase.TB_MONAN_GIAMONAN, monAnDTO.getGiaTien());
        contentValues.put(CreateDatabase.TB_MONAN_MALOAIMONAN, monAnDTO.getMaLoai());
        contentValues.put(CreateDatabase.TB_MONAN_HINHANHMONAN, monAnDTO.getHinhAnh());

        long kiemtra = database.insert(CreateDatabase.TB_MONAN, null, contentValues);
        if (kiemtra != 0) {
            return true;
        } else {
            return false;
        }
    }

    public List<MonAnDTO> LayDanhSachMonAnTheoLoai(int maLoai) {
        List<MonAnDTO> monAnDTOs = new ArrayList<MonAnDTO>();
        String truyVan = "SELECT * FROM " + CreateDatabase.TB_MONAN + " WHERE " + CreateDatabase.TB_MONAN_MALOAIMONAN + " = '" + maLoai + "' ";
        Cursor cursor = database.rawQuery(truyVan, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            MonAnDTO monAnDTO = new MonAnDTO();

            monAnDTO.setHinhAnh(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_MONAN_HINHANHMONAN)));
            monAnDTO.setMaMonAn(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_MONAN_MAMONAN)));
            monAnDTO.setGiaTien(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_MONAN_GIAMONAN)));
            monAnDTO.setTenMonAN(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_MONAN_TENMONAN)));
            monAnDTO.setMaLoai(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_MONAN_MALOAIMONAN)));


            monAnDTOs.add(monAnDTO);
            cursor.moveToNext();
        }
        return monAnDTOs;
    }

    public boolean SuaMonAn(int mamonan, String tenmonan, int giatien) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_MONAN_GIAMONAN, giatien);
        contentValues.put(CreateDatabase.TB_MONAN_TENMONAN, tenmonan);

        long kiemtra = database.update(CreateDatabase.TB_MONAN, contentValues, CreateDatabase.TB_MONAN_MAMONAN + " = " + mamonan, null);
        if (kiemtra != 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean XoaMonAn(int mamonan) {
        long kiemtra = database.delete(CreateDatabase.TB_MONAN, CreateDatabase.TB_MONAN_MAMONAN + " = " + mamonan, null);
        if (kiemtra != 0) {
            return true;
        } else {
            return false;
        }
    }

}
