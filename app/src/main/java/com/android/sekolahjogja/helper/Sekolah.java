package com.android.sekolahjogja.helper;

/**
 * Class Buat Data Sementara dari JSON di storing ke sini
 */
public class Sekolah {
    public int id_sekolah, id_kategori;
    public String nama_sekolah, alamat_sekolah, info_sekolah, gambar, nama_kategori;
    public Double lati, longi;

    public Sekolah() {
    }

    public Sekolah(int id_sch, int id_cat, String nama_sch, String address, String detail, String urlpic, String nama_cat, Double lati, Double longi) {
        this.id_sekolah = id_sch;
        this.id_kategori = id_cat;
        this.nama_sekolah= nama_sch;
        this.alamat_sekolah= address;
        this.info_sekolah = detail;
        this.gambar = urlpic;
        this.nama_kategori = nama_cat;
        this.lati = lati;
        this.longi = longi;
    }

    public int getId_sekolah() {
        return id_sekolah;
    }

    public void setId_sekolah(int id_sekolah) {
        this.id_sekolah = id_sekolah;
    }

    public int getId_kategori() {
        return id_kategori;
    }

    public void setId_kategori(int id_kategori) {
        this.id_kategori = id_kategori;
    }

    public String getNama_sekolah() {
        return nama_sekolah;
    }

    public void setNama_sekolah(String nama_sekolah) {
        this.nama_sekolah = nama_sekolah;
    }

    public String getAlamat_sekolah() {
        return alamat_sekolah;
    }

    public void setAlamat_sekolah(String alamat_sekolah) {
        this.alamat_sekolah = alamat_sekolah;
    }

    public String getInfo_sekolah() {
        return info_sekolah;
    }

    public void setInfo_sekolah(String info_sekolah) {
        this.info_sekolah = info_sekolah;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getNama_kategori() {
        return nama_kategori;
    }

    public void setNama_kategori(String nama_kategori) {
        this.nama_kategori = nama_kategori;
    }

    public Double getLati() {
        return lati;
    }

    public void setLati(Double lati) {
        this.lati = lati;
    }

    public Double getLongi() {
        return longi;
    }

    public void setLongi(Double longi) {
        this.longi = longi;
    }
}
