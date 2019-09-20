package com.dev.buivanphuc.apporderfood.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.dev.buivanphuc.apporderfood.DTO.ChiTietGoiMonDTO;
import com.dev.buivanphuc.apporderfood.DTO.GoiMonDTO;
import com.dev.buivanphuc.apporderfood.DTO.ThanhToanDTO;
import com.dev.buivanphuc.apporderfood.Database.CreateDatabase;

import java.util.ArrayList;
import java.util.List;

public class GoiMonDAO {

    SQLiteDatabase database;

    public GoiMonDAO(Context context) {
        CreateDatabase createDatabase = new CreateDatabase(context);
        database = createDatabase.open();
    }

    public long ThemGoiMon(GoiMonDTO goiMonDTO) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(CreateDatabase.TB_GOIMON_MABANGOIMON, goiMonDTO.getMaBan());
        contentValues.put(CreateDatabase.TB_GOIMON_NGAYGOIMON, goiMonDTO.getNgayGoi());
        contentValues.put(CreateDatabase.TB_GOIMON_MANHANVIENGOIMON, goiMonDTO.getMaNV());
        //    contentValues.put(CreateDatabase.TB_GOIMON_MAGOIMON,goiMonDTO.getMaGoiMon());
        contentValues.put(CreateDatabase.TB_GOIMON_TINHTRANGGOIMON, goiMonDTO.getTinhTrang());

        long magoimon = database.insert(CreateDatabase.TB_GOIMON, null, contentValues);
        return magoimon;
    }

    public long LayMaGoiMonTheoMaBan(int MaBan, String TinhTrang) {
        String truyvan = "select * from " + CreateDatabase.TB_GOIMON + " where " + CreateDatabase.TB_GOIMON_MABANGOIMON + " = '" + MaBan + "' and "
                + CreateDatabase.TB_GOIMON_TINHTRANGGOIMON + " = '" + TinhTrang + "' ";

        long magoimon = 0;
        Cursor cursor = database.rawQuery(truyvan, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            magoimon = cursor.getLong(cursor.getColumnIndex(CreateDatabase.TB_GOIMON_MAGOIMON));
            cursor.moveToNext();
        }
        return magoimon;
    }

    public boolean KiemTraMonAnDaTonTai(int maGoiMonChiTiet, int maMonAnChiTiet) {
        String truyVan = "SELECT * FROM " + CreateDatabase.TB_CHITIETGOIMON + " WHERE " + CreateDatabase.TB_CHITIETGOIMON_MAMONANCHIIET + " = '"
                + maMonAnChiTiet + "' AND " + CreateDatabase.TB_CHITIETGOIMON_MAGOIMONCHITIET + " = '" + maGoiMonChiTiet + "'";
        Cursor cursor = database.rawQuery(truyVan, null);
        if (cursor.getCount() != 0) {
            return true;
        } else {
            return false;
        }
    }

    public int LaySoLuongMonAnTheoMaGoiMon(int maGoiMon, int maMonAn) {
        int soLuong = 0;
        String truyVan = "SELECT * FROM " + CreateDatabase.TB_CHITIETGOIMON + " WHERE " + CreateDatabase.TB_CHITIETGOIMON_MAGOIMONCHITIET + " = '"
                + maGoiMon + "' AND " + CreateDatabase.TB_CHITIETGOIMON_MAMONANCHIIET + " = '" + maMonAn + "'";
        Cursor cursor = database.rawQuery(truyVan, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            soLuong = cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_CHITIETGOIMON_SOLUONGMONANCHITIET));
            cursor.moveToNext();
        }
        return soLuong;
    }

    public boolean CapNhatSoLuong(ChiTietGoiMonDTO chiTietGoiMonDTO) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_CHITIETGOIMON_SOLUONGMONANCHITIET, chiTietGoiMonDTO.getSoLuong());

        int kt = database.update(CreateDatabase.TB_CHITIETGOIMON, contentValues, CreateDatabase.TB_CHITIETGOIMON_MAGOIMONCHITIET
                + " = " + chiTietGoiMonDTO.getMaGoiMon() + " AND " + CreateDatabase.TB_CHITIETGOIMON_MAMONANCHIIET + " = " + chiTietGoiMonDTO.getMaMonAn(), null);
        if (kt != 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean ThemChiTietGoiMon(ChiTietGoiMonDTO chiTietGoiMonDTO) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_CHITIETGOIMON_MAGOIMONCHITIET, chiTietGoiMonDTO.getMaGoiMon());
        contentValues.put(CreateDatabase.TB_CHITIETGOIMON_MAMONANCHIIET, chiTietGoiMonDTO.getMaMonAn());
        contentValues.put(CreateDatabase.TB_CHITIETGOIMON_SOLUONGMONANCHITIET, chiTietGoiMonDTO.getSoLuong());
        long kt = database.insert(CreateDatabase.TB_CHITIETGOIMON, null, contentValues);
        if (kt != 0) {
            return true;
        } else {
            return false;
        }
    }

    public List<ThanhToanDTO> LayDanhSachMonAnTheoMaGoiMon(int magoimon) {

        List<ThanhToanDTO> thanhToanDTOList = new ArrayList<>();

        String truyvan = " select * from " + CreateDatabase.TB_CHITIETGOIMON + " , " + CreateDatabase.TB_MONAN + " where " + CreateDatabase.TB_CHITIETGOIMON +
                "." + CreateDatabase.TB_CHITIETGOIMON_MAMONANCHIIET + " = " + CreateDatabase.TB_MONAN + "." + CreateDatabase.TB_MONAN_MAMONAN
                + " and " + CreateDatabase.TB_CHITIETGOIMON_MAGOIMONCHITIET + " = " + magoimon;
        Cursor cursor = database.rawQuery(truyvan, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            ThanhToanDTO thanhToanDTO = new ThanhToanDTO();

            thanhToanDTO.setSoLuong(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_CHITIETGOIMON_SOLUONGMONANCHITIET)));
            thanhToanDTO.setGiaTien(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_MONAN_GIAMONAN)));
            thanhToanDTO.setTenMonAn(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_MONAN_TENMONAN)));

            thanhToanDTOList.add(thanhToanDTO);

            cursor.moveToNext();
        }
        return thanhToanDTOList;
    }

    public boolean CapNhatTrangThaiGoiMonTheoMaBan(int maBan,String tinhtrang) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_GOIMON_TINHTRANGGOIMON, tinhtrang);

       long kiemtra= database.update(CreateDatabase.TB_GOIMON, contentValues, CreateDatabase.TB_GOIMON_MABANGOIMON + " = '" + maBan + "'", null);
       if (kiemtra!=0)
       {
           return true;
       }else {
           return false;
       }
    }
}
