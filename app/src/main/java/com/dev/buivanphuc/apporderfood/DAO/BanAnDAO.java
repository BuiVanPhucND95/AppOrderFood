package com.dev.buivanphuc.apporderfood.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.dev.buivanphuc.apporderfood.DTO.BanAnDTO;
import com.dev.buivanphuc.apporderfood.Database.CreateDatabase;

import java.util.ArrayList;
import java.util.List;

public class BanAnDAO {

    SQLiteDatabase database;

    public BanAnDAO(Context context) {
        CreateDatabase createDatabase = new CreateDatabase(context);
        database = createDatabase.open();
    }

    public boolean ThemBanAn(String tenBanAn) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_BANAN_TENBANAN, tenBanAn);
        contentValues.put(CreateDatabase.TB_BANAN_TINHTRANGBANAN, "false");
        long kiemtra = database.insert(CreateDatabase.TB_BANAN, null, contentValues);

        if (kiemtra != 0) {
            return true;
        } else {
            return false;
        }
    }

    public List<BanAnDTO> LayTatCaBanAn() {
        List<BanAnDTO> banAnDTOList = new ArrayList<>();
        String truyvan = " select * from " + CreateDatabase.TB_BANAN;
        Cursor cursor = database.rawQuery(truyvan, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            BanAnDTO banAnDTO = new BanAnDTO();
            banAnDTO.setMaBan(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_BANAN_MABANAN)));
            banAnDTO.setTenBan(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_BANAN_TENBANAN)));
            banAnDTOList.add(banAnDTO);
            cursor.moveToNext();
        }
        return banAnDTOList;
    }

    public String LayTinhTrangBanTheoMa(int maBan) {
        String tinhTrang = "";
        String truyvan = "select * from " + CreateDatabase.TB_BANAN + " where " +
                CreateDatabase.TB_BANAN_MABANAN + " = " + maBan;
        Cursor cursor = database.rawQuery(truyvan, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            tinhTrang = cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_BANAN_TINHTRANGBANAN));
            cursor.moveToNext();
        }
        return tinhTrang;
    }

    public boolean CapNhatTinhTrangBan(int maBan, String tinhTrang) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_BANAN_TINHTRANGBANAN, tinhTrang);
        long kiemtra = database.update(CreateDatabase.TB_BANAN, contentValues, CreateDatabase.TB_BANAN_MABANAN + " = '" + maBan + "'", null);
        if (kiemtra != 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean XoaBanAnTheoMa(int maban) {
        long kiemtra = database.delete(CreateDatabase.TB_BANAN, CreateDatabase.TB_BANAN_MABANAN + " = " + maban, null);
        if (kiemtra != 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean CapNhatTenBan(int maban, String tenBan) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_BANAN_TENBANAN, tenBan);

        long kiemtra = database.update(CreateDatabase.TB_BANAN, contentValues, CreateDatabase.TB_BANAN_MABANAN + " = " + maban, null);

        if (kiemtra != 0) {
            return true;
        } else {
            return false;
        }
    }
}
