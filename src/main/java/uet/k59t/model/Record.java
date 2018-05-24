package uet.k59t.model;

/**
 * Created by Long laptop on 4/20/2018.
 */

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Long laptop on 4/20/2018.
 */
@Entity
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long recordId;
    private String maGiaoDich;
    private String thoiGian;
    private String loaiGiaoDich;
    private String maDonHang;
    private String tenEmail;
    private String kieu;
    private String soTien;
    private String soTienChuyen;
    private String trangThai;
    private String mota;

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public Record() {
    }

    public Record(String maGiaoDich, String thoiGian, String loaiGiaoDich, String maDonHang, String tenEmail, String kieu, String soTien, String soTienChuyen, String trangThai) {
        this.maGiaoDich = maGiaoDich;
        this.thoiGian = thoiGian;
        this.loaiGiaoDich = loaiGiaoDich;
        this.maDonHang = maDonHang;
        this.tenEmail = tenEmail;
        this.kieu = kieu;
        this.soTien = soTien;
        this.soTienChuyen = soTienChuyen;
        this.trangThai = trangThai;
    }

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public String getMaGiaoDich() {
        return maGiaoDich;
    }

    public void setMaGiaoDich(String maGiaoDich) {
        this.maGiaoDich = maGiaoDich;
    }

    public String getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
    }

    public String getLoaiGiaoDich() {
        return loaiGiaoDich;
    }

    public void setLoaiGiaoDich(String loaiGiaoDich) {
        this.loaiGiaoDich = loaiGiaoDich;
    }

    public String getMaDonHang() {
        return maDonHang;
    }

    public void setMaDonHang(String maDonHang) {
        this.maDonHang = maDonHang;
    }

    public String getTenEmail() {
        return tenEmail;
    }

    public void setTenEmail(String tenEmail) {
        this.tenEmail = tenEmail;
    }

    public String getKieu() {
        return kieu;
    }

    public void setKieu(String kieu) {
        this.kieu = kieu;
    }

    public String getSoTien() {
        return soTien;
    }

    public void setSoTien(String soTien) {
        this.soTien = soTien;
    }

    public String getSoTienChuyen() {
        return soTienChuyen;
    }

    public void setSoTienChuyen(String soTienChuyen) {
        this.soTienChuyen = soTienChuyen;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}

