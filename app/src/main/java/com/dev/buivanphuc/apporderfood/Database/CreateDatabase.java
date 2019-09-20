package com.dev.buivanphuc.apporderfood.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CreateDatabase extends SQLiteOpenHelper {

    public static final String TB_NHANVIEN = "NHANVIEN";
    public static final String TB_NHANVIEN_MANHANVIEN = "MANHANVIEN";
    public static final String TB_NHANVIEN_TENNHANVIEN = "TENNHANVIEN";
    public static final String TB_NHANVIEN_MATKHAUNHANVIEN = "MATKHAUNHANVIEN";
    public static final String TB_NHANVIEN_GIOITINHNHANVIEN = "GIOITINHNHANVIEN";
    public static final String TB_NHANVIEN_NGAYSINHNHANVIEN = "NGAYSINHNHANVIEN";
    public static final String TB_NHANVIEN_CMTNHANVIEN = "CMTNHANVIEN";
    public static final String TB_NHANVIEN_MAQUYENTRUYCAP = "MAQUYEN";

    public static final String TB_QUYENTRUYCAP = "QUYENTRUYCAP";
    public static final String TB_QUYENTRUYCAP_MAQUYEN = "MAQUYEN";
    public static final String TB_QUYENTRUYCAP_TENQUYEN = "TENQUYEN";

    public static final String TB_MONAN = "MONAN";
    public static final String TB_MONAN_MAMONAN = "MAMONAN";
    public static final String TB_MONAN_TENMONAN = "TENMONAN";
    public static final String TB_MONAN_GIAMONAN = "GIAMONAN";
    public static final String TB_MONAN_MALOAIMONAN = "MALOAIMONAN";
    public static final String TB_MONAN_HINHANHMONAN = "HINHANHMONAN";

    public static final String TB_LOAIMONAN = "LOAIMONAN";
    public static final String TB_LOAIMONAN_MALOAIMONAN = "MALOAIMONAN";
    public static final String TB_LOAIMONAN_TENLOAIMONAN = "TENLOAIMONAN";

    public static final String TB_BANAN = "BANAN";
    public static final String TB_BANAN_MABANAN = "MABANAN";
    public static final String TB_BANAN_TENBANAN = "TENBANAN";
    public static final String TB_BANAN_TINHTRANGBANAN = "TINHTRANGBANAN";

    public static final String TB_GOIMON = "GOIMON";
    public static final String TB_GOIMON_MAGOIMON = "MAGOIMON";
    public static final String TB_GOIMON_MANHANVIENGOIMON = "MANHANVIENGOIMON";
    public static final String TB_GOIMON_MABANGOIMON = "MABANGOIMON";
    public static final String TB_GOIMON_NGAYGOIMON = "NGAYGOIMON";
    public static final String TB_GOIMON_TINHTRANGGOIMON = "TINHTRANGGOIMON";


    public static final String TB_CHITIETGOIMON = "CHITIETGOIMON";
    public static final String TB_CHITIETGOIMON_MAGOIMONCHITIET = "MAGOIMONCHITIET";
    public static final String TB_CHITIETGOIMON_MAMONANCHIIET = "MAMONANCHIIET";
    public static final String TB_CHITIETGOIMON_SOLUONGMONANCHITIET = "SOLUONGMONANCHITIET";

    public CreateDatabase(Context context) {
        super(context, "OrderFood", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String tbNhanVien = "CREATE TABLE " + TB_NHANVIEN + " ( " + TB_NHANVIEN_MANHANVIEN + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_NHANVIEN_TENNHANVIEN + " TEXT, " + TB_NHANVIEN_MATKHAUNHANVIEN + " TEXT, " + TB_NHANVIEN_GIOITINHNHANVIEN + " TEXT, "
                + TB_NHANVIEN_NGAYSINHNHANVIEN + " TEXT, " + TB_NHANVIEN_CMTNHANVIEN + " INTERGER, " + TB_NHANVIEN_MAQUYENTRUYCAP + " INTERGER )";

        String tbQuyenTruyCap = "CREATE TABLE " + TB_QUYENTRUYCAP + " ( " + TB_QUYENTRUYCAP_MAQUYEN + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_QUYENTRUYCAP_TENQUYEN + " TEXT )";

        String tbMonAn = "CREATE TABLE " + TB_MONAN + " ( " + TB_MONAN_MAMONAN + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_MONAN_TENMONAN + " TEXT, " + TB_MONAN_GIAMONAN + " TEXT, " + TB_MONAN_MALOAIMONAN + " INTEGER, "
                + TB_MONAN_HINHANHMONAN + " TEXT )";

        String tbLoaiMonAn = "CREATE TABLE " + TB_LOAIMONAN + " ( " + TB_LOAIMONAN_MALOAIMONAN + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_LOAIMONAN_TENLOAIMONAN + " TEXT )";

        String tbBanAn = "CREATE TABLE " + TB_BANAN + " ( " + TB_BANAN_MABANAN + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_BANAN_TENBANAN + " TEXT, " + TB_BANAN_TINHTRANGBANAN + " TEXT )";

        String tbGoiMon = "CREATE TABLE " + TB_GOIMON + " ( " + TB_GOIMON_MAGOIMON + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_GOIMON_MABANGOIMON + " INTEGER, " + TB_GOIMON_MANHANVIENGOIMON + " INTEGER, " + TB_GOIMON_TINHTRANGGOIMON
                + " TEXT, " + TB_GOIMON_NGAYGOIMON + " TEXT )";

        String tbChiTietGoiMon = "CREATE TABLE " + TB_CHITIETGOIMON + " ( " + TB_CHITIETGOIMON_MAGOIMONCHITIET + " INTEGER, "
                + TB_CHITIETGOIMON_MAMONANCHIIET + " INTEGER, " + TB_CHITIETGOIMON_SOLUONGMONANCHITIET + " INTEGER, PRIMARY KEY ( "
                + TB_CHITIETGOIMON_MAGOIMONCHITIET + "," + TB_CHITIETGOIMON_MAMONANCHIIET + " ))";

        sqLiteDatabase.execSQL(tbNhanVien);
        sqLiteDatabase.execSQL(tbMonAn);
        sqLiteDatabase.execSQL(tbLoaiMonAn);
        sqLiteDatabase.execSQL(tbBanAn);
        sqLiteDatabase.execSQL(tbGoiMon);
        sqLiteDatabase.execSQL(tbChiTietGoiMon);
        sqLiteDatabase.execSQL(tbQuyenTruyCap);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

   public SQLiteDatabase open()
   {
       return this.getWritableDatabase();
   }
}
