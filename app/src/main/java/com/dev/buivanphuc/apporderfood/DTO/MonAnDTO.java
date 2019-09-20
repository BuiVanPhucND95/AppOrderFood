package com.dev.buivanphuc.apporderfood.DTO;

public class MonAnDTO {
    int MaMonAn, MaLoai;
    String TenMonAN, GiaTien,HinhAnh;

    public int getMaMonAn() {
        return MaMonAn;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
    }

    public void setMaMonAn(int maMonAn) {
        MaMonAn = maMonAn;
    }

    public int getMaLoai() {
        return MaLoai;
    }

    public void setMaLoai(int maLoai) {
        MaLoai = maLoai;
    }

    public String getTenMonAN() {
        return TenMonAN;
    }

    public void setTenMonAN(String tenMonAN) {
        TenMonAN = tenMonAN;
    }

    public String getGiaTien() {
        return GiaTien;
    }

    public void setGiaTien(String giaTien) {
        GiaTien = giaTien;
    }
}
